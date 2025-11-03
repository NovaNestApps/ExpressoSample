🧭 Native UI Test Framework — End-to-End Documentation

Modules Covered:
	•	:ui-testing (Reusable framework library)
	•	:ui-demo (Component-level reusable XML tests)
	•	:nga-android (Feature & journey-level integration using TestHarness and Robots)

Maintainers: Mobile Platform QA / Frameworks Team
Last Updated: November 2025

⸻

🏁 Overview

This document describes the Native UI Test Framework built to standardize Espresso UI automation across Retail, Halifax, and NGA Android apps.
It provides a unified DSL, custom matchers/actions/assertions, robot pattern, and test harness to enable deterministic, readable, and reusable end-to-end tests.

⸻

🧱 1. Core Framework — ui-testing Module

📦 Module Location

ui-testing/
└── src/main/kotlin/com/mobile/core/uitesting
    ├── compose/
    └── espresso/
        ├── actions/
        ├── assertions/
        ├── builders/
        ├── config/
        ├── extensions/
        ├── idling/
        ├── matcher/
        ├── rules/
        └── utils/


⸻

🎯 Purpose

To provide a reusable abstraction over Espresso allowing teams to write tests like:

clickOn(R.id.loginButton)
typeText(R.id.usernameInput, "Aniket")
assertVisible(R.id.homeHeader)

…without touching low-level onView() or raw matchers.

⸻

⚙️ Key Components

Package	Responsibility
actions	Common user actions (click, type, swipe, scroll, wait)
assertions	Assertions for visibility, text, state, RecyclerView
matchers	Custom view matchers (color, alpha, hierarchy, layout)
builders	ViewBuilder fluent API for chained matchers
extensions	Kotlin extensions for chaining interactions & matchers
rules	JUnit rules (retry, disable animations, grant permissions)
idling	Custom idling resources for async or Rx/OkHttp flows
utils	Device, keyboard, orientation & resource utilities


⸻

💡 Example Usage

ClickActions.clickOn(R.id.submit_button)
TextActions.writeTo(R.id.username, "aniket@bank.com")
VisibilityAssertions.assertVisible(R.id.home_container)
RecyclerViewAssertions.assertRecyclerViewItemCount(R.id.transactions_list, 10)


⸻

🔨 DSL Design Principles
	1.	Abstraction: Hide Espresso boilerplate.
	2.	Consistency: Unified naming & flow.
	3.	Extensibility: Each layer independently extendable.
	4.	Composability: Combine builders + matchers for DSL readability.
	5.	Stability: Built-in retry, wait, and idling awareness.

⸻

🧩 Core Snippets

✅ Click Actions

object ClickActions {
    @JvmStatic fun clickOn(@IdRes viewId: Int) =
        onView(withId(viewId)).perform(ViewActions.click())
}

✅ Visibility Assertions

object VisibilityAssertions {
    fun assertVisible(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(isDisplayed()))
}

✅ ViewBuilder

ViewBuilder()
    .withId(R.id.buttonSubmit)
    .withText("Submit")
    .isEnabled()
    .perform(ClickActions.click())


⸻

🧱 Matchers

Matcher Class	Purpose
ColorMatchers	Compare background/text color
TextMatchers	Contains, startsWith, endsWith (case-insensitive)
ViewPropertyMatchers	Alpha, visibility, expanded state
HierarchyMatchers	Parent-child relationships
RecyclerMatchers	Items inside RecyclerView
CompoundMatchers	AND/OR logic between matchers


⸻

🧰 Utilities
	•	DeviceUtils → Press back/home/recent, wake/sleep.
	•	KeyboardUtils → Close or wait for soft keyboard.
	•	PermissionUtils → Auto-grant runtime permissions.
	•	ResourceUtils → Access test resources.

⸻

🔁 Rules

Rule	Purpose
DisableAnimationsRule	Disable system animations for test determinism
RetryRule	Re-run flaky tests up to N times
GrantPermissionsRule	Auto-grant dangerous permissions


⸻

🧩 2. Reusable XML Component Tests — ui-demo Module

🎯 Goal

Validate stand-alone XML components (Accordion, Checkbox, Dialog, etc.) in isolation using the ui-testing DSL and Robots.

⸻

🧩 Structure

app/src/androidTest/java/com/mobile/ui/demo
├── base/
│   ├── BaseComponentTest.kt
│   └── TestJourneyStartHandler.kt
├── robots/
│   ├── view/
│   │   ├── AccordionViewRobot.kt
│   │   ├── AlertDialogViewRobot.kt
│   │   ├── CallToActionViewRobot.kt
│   │   ├── CheckboxViewRobot.kt
│   │   ├── CountryPickerViewRobot.kt
│   │   ├── ErrorBannerViewRobot.kt
│   │   └── ... other components ...
│   ├── ComponentsRobot.kt
│   └── ComponentItem.kt
└── view/
    ├── AccordionViewTest.kt
    ├── AlertDialogViewTest.kt
    ├── CheckboxViewTest.kt
    ├── ... etc ...


⸻

⚙️ Base Layer

BaseComponentTest.kt

Sets up shared test config, launch handler, and component navigation.

TestJourneyStartHandler.kt

Launches MainActivity, disables animations, and manages consistent app state.

⸻

🧠 Robot Pattern

Each component robot encapsulates:
	•	All view IDs
	•	Actions (click, scroll)
	•	Assertions (visible, text equals)

Example: AccordionViewRobot.kt

class AccordionViewRobot {
    private val accordionViewContent = R.id.accordion_view_content

    fun visible() = apply { assertDisplayed(accordionViewContent) }

    fun clickAccordion() = apply {
        ClickActions.clickOn(R.id.accordion_view_header)
    }

    fun assertAccordionExpanded(isExpanded: Boolean) = apply {
        ViewAssertions.assertMatches(
            R.id.accordion_view,
            ViewPropertyMatchers.withExpandedState(isExpanded)
        )
    }
}


⸻

🧪 Example Test: AccordionViewTest.kt

@Test
fun test_accordion_expand_and_collapse() {
    navigateToAccordionView()
    accordionScreen {
        visible()
        clickAccordion()
        assertAccordionExpanded(true)
        clickAccordion()
        assertAccordionExpanded(false)
    }
}


⸻

📋 Components Covered

Component	Robot	Test	Behavior Validated
Accordion	AccordionViewRobot	AccordionViewTest	Expand/collapse, text content
Checkbox	CheckboxViewRobot	CheckboxViewTest	Checked/unchecked states
Dialog	AlertDialogViewRobot	AlertDialogViewTest	Header, message, buttons
ErrorBanner	ErrorBannerViewRobot	ErrorBannerViewTest	Text, close icon, visibility
CountryPicker	CountryPickerViewRobot	CountryPickerViewTest	Picker visibility, enable states
InputField	InputFieldViewRobot	InputFieldViewTest	Input & validation
RadioButton	RadioButtonViewRobot	RadioButtonViewTest	Group selection
Switch	SwitchViewRobot	SwitchViewTest	ON/OFF toggle behavior


⸻

🧩 Component Navigation

ComponentsRobot.kt

class ComponentsRobot {
    fun open(item: ComponentItem) {
        ClickActions.clickOnText(item.title)
    }
}

ComponentItem.kt

enum class ComponentItem(@StringRes val title: Int) {
    ACCORDION(R.string.accordion),
    CHECKBOX(R.string.checkbox),
    ERRORBANNER(R.string.error_banner)
}


⸻

🚀 Benefits

✅ Reusable: Robots can be reused in feature-level tests.
✅ Readable: DSL syntax mirrors user behavior.
✅ Consistent: Unified assertions from ui-testing module.
✅ Scalable: New components follow same structure.

⸻

🧭 3. NGA Integration Flow — nga-android Module

🎯 Objective

Integrate the ui-testing library into the NGA project for:
	•	Feature-level automation (Splash, Login, Enrollment)
	•	Journey-level E2E flows using controlled stubs & Dagger mocks
	•	Reusable robot classes built on top of shared DSLs

⸻

⚙️ Architecture

nga-android/
└── src/androidTest/java/com/mobile/app/
    ├── TestApp.kt
    ├── TestHarness.kt
    ├── idlingresources/AppIdling.kt
    ├── uitest/
    │   ├── TestLauncherActivity.kt
    │   ├── TestSettingHostActivity.kt
    │   ├── TestComponent.kt
    ├── splash/activity/QASplashScreenActivityTest.kt
    ├── settings/PushNotificationSettingOptionsFragmentTest.kt
    └── robots/
        ├── LoginScreenRobot.kt
        ├── EnrollmentScreenRobot.kt
        ├── MiScreenRobot.kt
        ├── SendNotificationScreenRobot.kt
        ├── WelcomeScreenRobot.kt
        └── ...


⸻

🧩 Flow Summary

Step	Layer	Description
1️⃣	TestHarness.kt	Build intent with stub, flags, and theme
2️⃣	TestApp.kt	Inject mocks via Dagger
3️⃣	MockFeatureConfig.kt	Return controlled feature toggles
4️⃣	TestLauncherActivity.kt	Apply config + launch target
5️⃣	AppBootstrapper.kt	Override switches & feature flags
6️⃣	Idling Resources	Register Rx/OkHttp idlers
7️⃣	Robots	Compose reusable test DSLs


⸻

🧩 Harness Configuration

val config = TestHarness.Config(
    stub = "success/enrolled",
    switches = emptyList(),
    flags = listOf(FeatureConfigStatus("IS_REDESIGN_ONBOARDING_COMMS_ENABLED", false))
)

@get:Rule(order = 0)
val activityRule = ActivityScenarioRule(TestHarness.intent(config))


⸻

🧰 TestApp & Mock Config

class TestApp : App() {
    override fun getBrandModule() = BrandModule {
        MockFeatureConfig(localFeatureConfigOverrider)
    }
}

MockFeatureConfig

override fun isRedesignOnboardingCommsEnabled() =
    localFeatureConfigOverrider.get("IS_REDESIGN_ONBOARDING_COMMS_ENABLED", true)


⸻

🧩 TestLauncherActivity

Bootstraps the environment and launches the test host.

class TestLauncherActivity : AppCompatActivity() {
    @Inject lateinit var bootstrapper: AppBootstrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bootstrapper.apply()
        startTargetFragmentOrActivity()
    }
}


⸻

🧩 AppBootstrapper

Applies switch & flag overrides from harness config.

class AppBootstrapper @Inject constructor(
    private val switchOverrider: SwitchOverrider,
    private val featureConfig: LocalFeatureConfigOverrider
) {
    fun apply() {
        switchOverrider.updateAll()
        featureConfig.clear()
        featureConfig.putAll(mapOf("FLAG_A" to true))
    }
}


⸻

🧩 Idling Resources

object AppIdling {
    fun registerIdlers() {
        IdlingRegistry.getInstance().register(AppIdling.nav)
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("io"))
    }
}


⸻

🧠 Robot Pattern Example

LoginScreenRobot.kt

class LoginScreenRobot {
    private val usernameInput = R.id.loginUsernameInput
    private val passwordInput = R.id.loginPasswordInput
    private val continueButton = R.id.loginContinueButton

    fun visible() = apply {
        ViewAssertions.assertDisplayed(usernameInput)
    }

    fun enterUserName(name: String) =
        TextActions.writeTo(usernameInput, name)

    fun enterPassword(pwd: String) =
        TextActions.writeTo(passwordInput, pwd)

    fun clickLoginButton() =
        ClickActions.clickOn(continueButton)
}


⸻

🧪 Example Journey Test

QASplashScreenActivityTest.kt

@Test
fun test_complete_user_journey_splash_to_home() {
    welcomeScreen {
        visible()
        tapPrimary()
    }

    loginScreen {
        visible()
        enterUserName("admin")
        enterPassword("password123")
        clickLoginButton()
    }

    homeScreen {
        assertHomeDisplayed()
    }
}


⸻

🧩 Summary Table

Layer	File	Purpose
Test Harness	TestHarness.kt	Launch config for controlled env
Dependency Injection	TestApp.kt / MockFeatureConfig.kt	Mocked modules
Bootstrap	AppBootstrapper.kt	Apply flags & switches
Launch	TestLauncherActivity.kt	Start fragment or activity
Idling	AppIdling.kt	Register idlers
Robots	LoginScreenRobot.kt etc.	Screen-specific actions/assertions
Tests	SplashActivityTest.kt, etc.	Full user journeys


⸻

🚀 Benefits of Integration

✅ Shared DSL layer: Single source of truth for UI interactions.
✅ Stable & deterministic tests: Controlled DI + idling.
✅ Composable architecture: Robots + Harness = scalable.
✅ Cross-module reuse: NGA, Retail, and Demo share same APIs.
✅ Brand-safe: The harness reads brand configs dynamically (via Dagger).

⸻

🧩 Future Roadmap

Area	Improvement
Compose Support	Add Compose DSL under ui-testing/compose
Reporting	Integrate unified HTML test reports
Snapshot Testing	Include Paparazzi for visual regression
Test Data Builders	Reusable fake data providers
Analytics Validation	Hook to MockTracker assertions


⸻

🧾 Appendix — Quick Start

✅ Add dependency

androidTestImplementation(project(":ui-testing"))

✅ Extend Base Harness

val config = TestHarness.Config(stub = "stub/success")
ActivityScenarioRule(TestHarness.intent(config))

✅ Use Robots

loginScreen {
    enterUserName("aniket")
    enterPassword("secret")
    clickLoginButton()
}

✅ Assert with DSL

VisibilityAssertions.assertVisible(R.id.homeView)


⸻

📚 References
	•	Android Espresso: developer.android.com/training/testing/espresso￼
	•	Compose Testing: developer.android.com/jetpack/compose/testing￼
	•	Lloyds Digital Frameworks: Mobile Testing Strategy 2025 (Confluence internal)

⸻

🧩 Summary:

The Native UI Test Framework provides a single test abstraction for both component-level and journey-level automation, blending DSL expressiveness with DI-controlled reliability.
It’s brand-agnostic, modular, and future-ready for Compose migration and screenshot testing.

⸻

