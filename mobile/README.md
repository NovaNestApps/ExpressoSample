

UI Testing Framework (Espresso + Robot Pattern)

This module provides a reusable UI Testing Framework built on top of Espresso.
It standardises how we test XML-based UI components in the ui module of our app (Accordion, AlertDialog, Toggle, Checkbox, etc.).

The goal is to make tests:
	•	Readable: tests look like specs, not boilerplate Espresso code.
	•	Reusable: common actions and assertions live in one place.
	•	Stable: uses idling resources and wait rules to reduce flakiness.
	•	Composable: components are tested in isolation using the Robot Pattern.

⸻

📦 Project Structure

ui-testing/
└── src/main/kotlin/com/mobile/core/uitesting/espresso
    ├── actions/         # Reusable user actions (click, scroll, type)
    ├── assertions/      # Generic assertions (isDisplayed, isEnabled, etc.)
    ├── builders/        # ViewBuilder / RootBuilder to compose matchers
    ├── matcher/         # BaseMatchers + custom matchers (text, recyclerview, toolbar…)
    ├── rules/           # Idling resources and test rules
    └── ...

In the app module (ui/demo):

src/androidTest/java/com/mobile/ui/demo/robots/
    view/
      AccordionViewRobot.kt
      AlertDialogViewRobot.kt
      ToggleViewRobot.kt
      CheckboxViewRobot.kt
      ...
    base/TestJourneyStartHandler.kt
    MainRobot.kt
    ComponentListUITest.kt


⸻

🛠 Framework Building Blocks

1. Actions

Reusable Espresso wrappers for clicks, typing, scrolling, etc.

Actions.click()
Actions.longClick()
Actions.doubleClick()
Actions.type("Hello")
Actions.scrollTo()

2. Assertions

Standardised checks for visibility, enabled/disabled, checked state, etc.

DefaultAssertions.isDisplayed(R.id.title)
DefaultAssertions.isNotDisplayed(withText("Loading"))
DefaultAssertions.isEnabled(R.id.cta)

3. Matchers

Custom matchers for text, drawables, recyclerView items, hierarchy, etc.

withText("Continue")
withHint("Email")
withDrawable(R.drawable.ic_info)
withRecyclerViewItem { hasDescendant(withText("Notifications")) }

4. ViewBuilder

Fluent builder for composing multiple matchers:

ViewBuilder()
    .withId(R.id.container)
    .withText("Continue")
    .withParent(withId(R.id.root))
    .getViewMatcher()

5. Rules & Idling Resources
	•	AsyncTaskSchedulerRule – handles background tasks.
	•	ChildFragmentExistsIdlingResource – waits for fragment injection.
	•	WaitForViewMatchingResource – retries assertions within a timeout.

⸻

🤖 Robot Pattern

Each UI component has its own Robot exposing meaningful actions/assertions.
This keeps test code clean and business-focused.

Example: AccordionViewRobot

class AccordionViewRobot {
    fun visible() = isDisplayed(R.id.accordion_view_content)

    fun clickAccordion() = onView(
        allOf(
            withId(R.id.accordion_view_header_frame),
            isDescendantOfA(withId(R.id.accordion_1))
        )
    ).perform(Actions.click())

    fun assertAccordionExpanded(expanded: Boolean) = matches(
        viewId = R.id.accordion_1,
        matcher = withExpandedState(expanded)
    )
}

fun accordionScreen(block: AccordionViewRobot.() -> Unit) =
    AccordionViewRobot().apply(block)


⸻

🧪 Writing Component Tests in ui Module
	1.	Extend the base test handler to disable animations and launch the app:

class ComponentListUITest : TestJourneyStartHandler() {
    @Before
    fun setup() {
        launchApp()
        mainScreen {
            clickMaterialUIButton(composeTestRule) // enter XML component list
        }
    }

	2.	Use robots in tests:

Accordion Test

@Test
fun test_accordion_screen_displayed_in_xml() {
    List.open(item = ComponentItem.ACCORDION)

    accordionScreen {
        visible()
        assertAccordionHeaderIsDisplayed("Hello World")
        clickAccordion()
        assertAccordionExpanded(true)
    }
}

Alert Dialog Test

@Test
fun test_alert_dialog_screen_displayed_in_xml() {
    List.open(ComponentItem.ALERTDIALOG)

    alertDialogScreen {
        visible()
        openDialog("Dialog with x2 actions")
        assertDialogHeader("Dialog with x2 actions - stacked")
        assertDialogButtonVisible("Button 1")
        clickDialogButton("Done")
    }
}

Toggle Test

@Test
fun test_toggle_screen_displayed_in_xml() {
    List.open(ComponentItem.TOGGLE)

    toggleScreen {
        clickYes()
        assertYesIsSelected()
        clickNo()
        assertNoIsSelected()
        assertDisabledYesNotClickable()
    }
}


⸻

✅ Best Practices
	•	Use robots instead of direct Espresso calls in tests.
	•	Keep robots focused: only expose meaningful user actions/assertions.
	•	Use getResourceString(R.string.my_text) instead of hardcoding text.
	•	Prefer assertDisplayedEventually for async UI.
	•	One behaviour per test (test_checkbox_can_toggle(), not test_all_checkbox_behaviours).
	•	Don’t use Thread.sleep() – rely on idling resources.
	•	Add screenshots/logs on failure (helpful for CI).

⸻

🚀 Summary
	•	ui-testing module = core Espresso DSL (actions, assertions, matchers, builders, rules).
	•	Robots in ui/demo/robots = per-component wrappers.
	•	Component tests = simple, human-readable specs using robots.

Example final test flow:

@Test
fun user_can_expand_accordion() {
    List.open(ComponentItem.ACCORDION)
    accordionScreen {
        assertAccordionHeaderIsDisplayed("Welcome")
        clickAccordion()
        assertAccordionExpanded(true)
    }
}


⸻

