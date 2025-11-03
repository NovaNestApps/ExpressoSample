📄 Espresso-Based XML UI Testing Framework 

This custom UI test framework is built on top of Espresso (with limited Compose support for mixed flows) to provide a modular, scalable, and DSL-style approach to UI testing in Android.

It abstracts boilerplate using:
	•	✅ Robots for screen logic
	•	✅ Custom matchers for styling/props
	•	✅ Reusable actions/assertions
	•	✅ ViewBuilder for composable selectors
	•	✅ TestLaunchers for DI + stub setup

🔧 Goals:
	•	Support XML-based UI testing across multi-module, multi-brand Android apps.
	•	Ensure reusability, maintainability, and readability.
	•	Make tests brand-aware and configurable via stubs.

⸻

🔹 2. Framework Architecture

🧱 2.1 Robot Pattern

Robots are Kotlin objects or classes that encapsulate UI interactions and assertions per screen or component.

Example: AccordionViewRobot.kt

accordionScreen {
    clickAccordion()
    assertAccordionText("Hello World")
}

Each robot uses:
	•	DefaultAssertions for visibility/state
	•	ClickActions, TextActions for interactions
	•	Fluent DSL functions

Robots:
	•	AccordionViewRobot
	•	SwitchViewRobot
	•	CheckboxViewRobot
	•	WelcomeScreenRobot
	•	LoginScreenRobot

⸻

🧩 2.2 Custom Matchers

Located in espresso.matcher, they extend Espresso’s Matcher<View> interface.

Key Matchers:

Matcher	Purpose
withTextColor	Match text color from resource
withDrawable	Match drawable of ImageView
withBackgroundColor	Match background tint or color
withText / withTextPattern	Match static or regex text
isChecked, isNotChecked	Match checkbox/switch states
isEnabled, isDisabled	Match enable state

Example:

onView(withId(R.id.editText)).check(matches(withTextColor(R.color.red)))


⸻

🛠️ 2.3 Actions

Located in espresso.actions package. These wrap Espresso ViewActions to avoid boilerplate.

Key Actions:

Action	Description
click()	Clicks view by ID or matcher
clickChildViewWithId()	Clicks a nested child inside a parent
scrollToText()	Scroll to specific text
replaceText()	Clears and replaces text
pressImeActionButton()	Simulates keyboard action
swipeUp(), swipeDown()	Swiping interactions


⸻

✅ 2.4 Assertions

Located in DefaultAssertions.kt and implements BaseAssertions.kt. Centralized way to validate view state or props.

Examples:

isDisplayed(R.id.view)
isNotDisplayed(withText("Error"))
isEnabled(R.id.submitBtn)
doesNotExist(withText("404"))

Assertion Methods:
	•	isDisplayed(), isNotDisplayed()
	•	isCompletelyDisplayed()
	•	isEnabled(), isDisabled()
	•	isChecked(), isNotChecked()
	•	hasTag(), doesNotExist()

⸻

🧰 2.5 ViewBuilder (Core Abstraction)

The ViewBuilder enables dynamic and composable view selection. Allows you to chain view selection logic.

Key Methods:

withId(R.id.myView)
withIndex(2)
withText("Submit")
isEnabled()
containsText("Hello")

Use Case:

onView(
    ViewBuilder()
        .withId(R.id.recyclerItem)
        .withIndex(1)
        .isEnabled()
        .build()
)

This approach makes view targeting in complex UIs like RecyclerView more expressive and readable.

⸻

🧪 2.6 DSL Syntax for Tests

Example:

accordionScreen {
    visible()
    clickAccordion()
    assertAccordionText("Expanded content")
}

Benefits:
	•	Improved readability
	•	Robot encapsulates implementation
	•	Test focuses on behavior, not mechanics

⸻

🔹 3. Usage in NGA (Multi-Brand Testable App)

⸻

🚀 3.1 Test Entry Point: QASplashScreenActivityTest

This test validates end-to-end flows starting from SplashActivity using Compose and XML views.

@Test
fun test_navigate_from_welcome_to_login_screen() {
    goToLoginScreen()
    performLogin()
    enterMIDigits()
    verifyComposeSuccessScreen()
    verifyFinalSaveScreen()
}

	•	Uses composeTestRule for Compose nodes
	•	Espresso + Compose in one test class
	•	Validates flow: Splash → Welcome → Login → MiDigits → Compose Success

⸻

🧠 3.2 Dependency Injection Setup

TestApp.kt
	•	Overrides real modules with test versions using Dagger
	•	Mocks include:
	•	MockFeatureFlagRepository
	•	MockFeatureConfig
	•	TestFraudSettings
	•	Used only in UI tests (declared in AndroidManifest.xml)

<application
    android:name="com.mobile.app.TestApp"
    tools:replace="android:name">


⸻

🧾 3.3 TestLauncherActivity.kt
	•	Declared as LAUNCHER for test variant
	•	Reads intent extras:
	•	Stub scenario
	•	Switch statuses
	•	Feature flags
	•	Calls AppBootStrapper.apply(...)
	•	Launches the real activity (QaSplashActivity) post DI setup

startActivity(QaSplashActivity::class.java, stubConfig)


⸻

🧪 3.4 AppBootStrapper

Called by TestLauncherActivity to inject dynamic test setup.

fun apply(stubScenario, switches, featureFlags) {
    resetAppComponent(...)
    localFlags.clearAndPut(...)
}

Purpose:
	•	Simulate stubs like happy_path, otp_failure
	•	Enable/disable switches or flags at runtime

⸻

📲 3.5 QA-Specific Manifest Configuration

<activity
    android:name=".quicklaunch.activity.TestLauncherActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

Also uses:
	•	@TestHarness.Config to auto-inject test rules
	•	composeTestRule for Compose interaction

⸻

🔹 4. Summary

This Espresso XML UI Test Framework provides:

Feature	Value
Robot Pattern	Screen-scoped encapsulated test logic
Custom Matchers	Styling, state, drawable matchers
Custom Actions	Reusable high-level ViewActions
Assertions Layer	Consistent reusable visibility/state checks
ViewBuilder	Fluent view targeting abstraction
DSL-style Tests	Highly readable, reusable syntax
Test DI Setup	Full test-only TestApp with injectable stubs
NGA Integration	End-to-end UI tests from splash to login + Compose

This framework is ideal for teams working in multi-module, multi-brand apps, where UI testing must be:
	•	Readable
	•	Fast to write
	•	Easy to maintain
	•	Configurable per brand/stub/flag