Got it 👍 Right now your draft is a dump of notes — it repeats sections, mixes “what” and “how”, and isn’t easy for a teammate to skim.

Here’s a cleaned-up, structured README you can drop into Confluence or your repo:

⸻

NGA UI Test Framework

This repository contains the UI test framework used in NGA.
It provides a test harness, DI overrides, robots, and helpers to run Espresso and Compose UI tests across brands and modules.

⸻

1. What We’re Testing

We support two main styles of UI tests:
	1.	End-to-End (App Harness)
	•	Boot a minimal test app that injects stubs and feature flags.
	•	Launch complete flows (e.g. sign-in → onboarding → home).
	2.	Screen / Feature Tests
	•	Jump directly to a specific Fragment/Activity/Compose host.
	•	DI and feature flags are already satisfied so you can test the screen in isolation.

👉 Location:
	•	End-to-End → app/src/androidTestQa/...
	•	Feature-specific → ui/<feature>/src/androidTestRetail/... (or similar)

⸻

2. Key Building Blocks

2.1 Test App (Harness)
	•	A minimal application, used only for instrumentation tests.
	•	Lets us:
	•	Register stub data & feature flags.
	•	Swap DI bindings for fakes.
	•	Route to the screen under test.

Where:
app/src/androidTestQa/java/.../TestApp.kt

Manifest override:
app/src/androidTest/AndroidManifest.xml

<application
    android:name=".TestApp"
    tools:replace="android:name, android:theme"
    android:theme="@style/LloydsTheme"/>


⸻

2.2 TestHarness
	•	Entry point for configuring the test run.
	•	Accepts:
	•	Stubs
	•	Feature flags
	•	Switches
	•	Optional DI overrides
	•	Produces an Intent for TestLauncherActivity.

val harnessConfig = TestHarness.Config(
    stub = "stubs/login_success.json",
    switches = listOf(Switch.ENABLE_SUPPORT_HUB_MVT to true),
    flags = listOf(
        FeatureConfigFlag("redesign_onboarding", false)
    )
)

val intent = TestHarness.intent(harnessConfig)


⸻

2.3 TestLauncherActivity
	•	A host Activity used only in tests.
	•	Responsibilities:
	•	Receives TestHarness config.
	•	Calls AppBootstrapper.bootstrap(config) to reset the app component.
	•	Decides whether to:
	•	Start a full flow (e.g. Splash → Login → Home), or
	•	Attach a specific Fragment/Screen for testing.

val intent = TestLauncherActivity.getIntent(
    context,
    fragmentClass = PushNotificationSettingsOptionsFragment::class.java,
    args = bundleOf(),
    themeResId = R.style.LloydsAppTheme
)
ActivityScenario.launch<TestLauncherActivity>(intent)


⸻

2.4 AppBootstrapper
	•	Responsible for setting up DI for tests.
	•	Runs when TestLauncherActivity starts, not in TestApp.
	•	Inside bootstrap(config):
	•	Calls resetAppComponent() to rebuild the Dagger graph.
	•	Registers feature flags, switches, and stubs.
	•	Ensures each test starts with a clean state.

object AppBootstrapper {
    fun bootstrap(config: TestHarness.Config) {
        resetAppComponent()
        FeatureFlagSeeder.seed(config.flags)
        StubServer.seed(config.stub)
        SwitchSeeder.seed(config.switches)
    }
}


⸻

2.5 resetAppComponent()
	•	Clears cached singletons.
	•	Rebuilds the Dagger app component with test overrides.

fun resetAppComponent(app: Application) {
    appComponent = DaggerQaComponent.builder()
        .appModule(AppModule(app))
        .uiTestOverridesModule(UITestOverridesModule())
        .build()
}


⸻

2.6 Feature Flags
	•	Passed via TestHarness.Config.flags.
	•	Injected into FeatureConfig so the UI behaves deterministically.

val flags = listOf(
    FeatureConfigFlag("enable_new_dashboard", true),
    FeatureConfigFlag("show_beta_banner", false)
)


⸻

2.7 Stubs
	•	Fake server responses, stored under app/src/androidTest/assets/stubs/.
	•	Selected per test through TestHarness.Config.stub.

val harnessConfig = TestHarness.Config(stub = "stubs/login_success.json")


⸻

3. Test Flow (Step by Step)
	1.	Test Setup
	•	Build a TestHarness.Config.
	•	Pass it to TestLauncherActivity via getIntent(...).
	2.	TestLauncherActivity
	•	Receives config.
	•	Calls AppBootstrapper.bootstrap(config).
	3.	AppBootstrapper
	•	Calls resetAppComponent().
	•	Seeds feature flags, stubs, switches.
	4.	Screen Launch
	•	Hosts either:
	•	A full app flow (Splash → Login → Home), or
	•	A fragment in isolation.
	5.	UI Test Runs
	•	Espresso / Compose + Robots drive the UI.
	•	Stubs & flags keep the flow predictable.

⸻

4. Running Tests

From Android Studio
	•	Pick brand + buildType (e.g. lloydsQaRetail).
	•	Right-click a test → Run.

From CLI

# All tests for a variant
./gradlew :app:connectedLloydsRetailQaAndroidTest

# Single test class
./gradlew :app:connectedLloydsRetailQaAndroidTest \
  -Pandroid.testInstrumentationRunnerArguments.class=com.mobile.ui.splash.activity.QASplashScreenActivityTest


⸻

5. Conventions
	•	End-to-End tests → app/src/androidTestQa/...
	•	Feature/Screen tests → ui/<feature>/src/androidTestRetail/... (if isolated)
	•	Use robots for multi-step UI flows.
	•	Use testTags in Compose for dynamic content.

⸻

6. Why This Design?
	•	Keeps TestApp lightweight.
	•	AppBootstrapper is only run when TestLauncherActivity starts → each test can have its own config.
	•	Guarantees deterministic startup per test (fresh DI graph, seeded flags & stubs).
	•	Supports multi-brand routing via UITestHosts.

