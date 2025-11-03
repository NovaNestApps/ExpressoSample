
📘 UI Testing Framework (Espresso DSL Layer) – Overview

Module: ui-testing
Package: com.mobile.core.uitesting
Type: Shared reusable test automation framework for Android (Espresso-based)
Author(s): QA & Platform Frameworks Team
Last Updated: November 2025

⸻

🎯 Purpose

The ui-testing module provides a reusable abstraction layer over Espresso to standardize UI test writing across multiple product modules (e.g., NGA, Retail, Halifax).
It encapsulates low-level Espresso APIs into high-level, human-readable DSLs (Domain-Specific Language) for actions, matchers, assertions, and utilities.

This allows test engineers to:
	•	Write expressive and readable test cases.
	•	Reduce code duplication and boilerplate.
	•	Simplify test maintenance when UI or behavior changes.
	•	Support both XML-based and Compose-based UIs through modular separation.

⸻

🧩 Framework Architecture

com.mobile.core.uitesting
│
├── compose/                     # Future support for Compose testing
│
└── espresso/                    # Espresso core framework
    ├── actions/                 # User actions abstraction
    ├── assertions/              # Assertion helpers
    ├── builders/                # ViewBuilder for fluent match construction
    ├── config/                  # Timeout/Test configuration
    ├── extensions/              # Kotlin extensions for matchers/interactions
    ├── idling/                  # Idling resource utilities
    ├── matcher/                 # Custom Espresso matchers
    ├── rules/                   # JUnit rules for animations/retries
    └── utils/                   # System-level utilities


⸻

⚙️ Core Components

1️⃣ Actions (espresso/actions/)

Encapsulate user interactions in simple Kotlin functions.

Class	Purpose
ClickActions	Single, double, and long clicks by ID, text, or matcher
RecyclerViewActions	Click or scroll inside RecyclerView at specific position
ScrollActions	Scroll operations on views
SwipeActions	Swipe gestures (left, right, up, down)
TextActions	Text input, replacement, clearing
WaitActions	Wait for visibility, text, or delay

Example usage:

ClickActions.clickOn(R.id.login_button)
TextActions.writeTo(R.id.username_input, "Aniket")
ScrollActions.scrollToText("Continue")


⸻

2️⃣ Assertions (espresso/assertions/)

Provides clear validation helpers wrapping onView(...).check(...) with reusable matchers.

Class	Purpose
VisibilityAssertions	Assert visible, invisible, gone
TextAssertions	Assert text equals, contains, or matches regex
RecyclerViewAssertions	Assert RecyclerView item count or non-empty state
ViewAssertions	General-purpose assertions (enabled, displayed, etc.)
StateAssertions	Optional composable/assertion state verifiers

Example usage:

VisibilityAssertions.assertVisible(R.id.success_message)
TextAssertions.assertTextEquals(R.id.header_title, "Welcome")
RecyclerViewAssertions.assertRecyclerViewItemCount(R.id.list, 5)


⸻

3️⃣ Matchers (espresso/matcher/)

Contains custom matchers beyond built-in Espresso matchers.

Class	Purpose
ColorMatchers	Match view background or text color
TextMatchers	Match text with startsWith, endsWith, contains (case-insensitive)
ViewPropertyMatchers	Match alpha, visibility, and non-empty text
HierarchyMatchers	Match parent/child view relationships
LayoutMatchers	Match layout-level conditions
RecyclerMatchers	Match items inside RecyclerView
CompoundMatchers	Logical AND/OR combinations of matchers

Example:

onView(allOf(withId(R.id.banner), ViewPropertyMatchers.withAlpha(1.0f)))
    .check(matches(isDisplayed()))


⸻

4️⃣ ViewBuilder (espresso/builders/ViewBuilder.kt)

A fluent builder for composing complex view matchers:

ViewBuilder()
    .withId(R.id.submit_button)
    .withText("Submit")
    .isEnabled()
    .perform(ClickActions.click())

Internally accumulates matchers using:

matchers.add(ViewMatchers.withId(id))
matchers.add(ViewMatchers.withText(text))

Useful for reusable robot-style DSL syntax:

view { withText("Continue") }.click()


⸻

5️⃣ Extensions (espresso/extensions/)

Provides Kotlin extensions to simplify view interaction and matcher chaining.

File	Purpose
TestExtensions.kt	Retry, delay, and utility helpers for test stability
ViewInteractionExtensions.kt	Chainable functions on ViewInteraction
ViewMatcherExtensions.kt	Combine, negate, or join matchers logically

Example:

retryOnFailure(retryCount = 3) {
    onView(withId(R.id.retry_button)).perform(click())
}


⸻

6️⃣ Idling Resources (espresso/idling/)

Encapsulates synchronization with background work.

File	Description
IdlingResourceManager.kt	Central registry for custom idling resources
ViewGoneIdlingResource.kt	Waits until a view disappears
ViewPagerIdlingResource.kt	Waits for ViewPager animations
ViewStateIdlingResource.kt	Waits for specific view states

Ensures smooth Espresso execution for async UI or Rx-based transitions.

⸻

7️⃣ Rules (espresso/rules/)

Reusable JUnit rules for common test setups.

Rule	Purpose
DisableAnimationsRule	Temporarily disables system animations during tests
GrantPermissionsRule	Auto-grants dangerous permissions at runtime
RetryRule	Retries flaky tests automatically

Example:

@get:Rule val disableAnimations = DisableAnimationsRule()
@get:Rule val retryRule = RetryRule(retryCount = 3)


⸻

8️⃣ Utilities (espresso/utils/)

System-level utilities for device operations.

File	Description
DeviceUtils.kt	Press back, home, wake/sleep device
KeyboardUtils.kt	Hide, show, or wait for soft keyboard
PermissionUtils.kt	Manage permissions programmatically
OrientationUtils.kt	Change device orientation
ResourceUtils.kt	Access test resources programmatically

Example:

DeviceUtils.pressBack()
KeyboardUtils.closeKeyboard()
OrientationUtils.setLandscape()


⸻

🧠 Key Design Principles
	1.	Abstraction: Hides Espresso boilerplate via DSL-style functions.
	2.	Consistency: Unified naming and structure across all test modules.
	3.	Extensibility: Each layer (actions, assertions, matchers) can be independently extended.
	4.	Composability: Builders and matchers are chainable and modular.
	5.	Stability: Includes idling resources, retry rules, and synchronization mechanisms.

⸻

🧪 Example End-to-End Test Using Framework

@Test
fun testLoginFlow() {
    // Input credentials
    TextActions.writeTo(R.id.username_input, "user@example.com")
    TextActions.writeTo(R.id.password_input, "password123")

    // Click login
    ClickActions.clickOn(R.id.login_button)

    // Wait for home screen
    VisibilityAssertions.assertVisible(R.id.home_welcome_message)
    TextAssertions.assertTextContains(R.id.home_welcome_message, "Welcome")
}


⸻

🚀 Extending the Framework

You can extend functionality by adding:

Extension Type	Location	Example
New Action	espresso/actions	SwipeUpActions.kt
New Assertion	espresso/assertions	SnackbarAssertions.kt
New Matcher	espresso/matcher	DrawableMatchers.kt
Custom Rule	espresso/rules	NetworkRetryRule.kt

Follow naming conventions and always use static methods (@JvmStatic) for DSL-style accessibility.

