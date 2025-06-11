/**
 * Custom assertions that go beyond default Espresso checks.
 * Useful for checking parent view states and explicitly verifying non-display.
 */
class CustomAssertions {

    companion object {

        /**
         * Asserts that the view is not displayed on the screen.
         *
         * @return A [ViewAssertion] that throws an error if the view is displayed.
         * @throws AssertionError if the view is present and displayed.
         */
        fun isNotDisplayed(): ViewAssertion = ViewAssertion { view, _ ->
            if (view != null && isDisplayed().matches(view)) {
                throw AssertionError("View is present in the hierarchy and Displayed: " + HumanReadables.describe(view))
            }
        }

        /**
         * Asserts that the parent of the current view is enabled.
         *
         * @return A [ViewAssertion] that throws an error if the parent view is disabled.
         * @throws AssertionError if the parent is disabled.
         */
        fun isParentEnabled(): ViewAssertion = ViewAssertion { view, _ ->
            val parentView = view.parent
            if (parentView != null && not(isEnabled()).matches(parentView)) {
                throw AssertionError("View is present in the hierarchy and Disabled: " + HumanReadables.describe(view))
            }
        }

        /**
         * Asserts that the parent of the current view is disabled.
         *
         * @return A [ViewAssertion] that throws an error if the parent view is enabled.
         * @throws AssertionError if the parent is enabled.
         */
        fun isParentDisabled(): ViewAssertion = ViewAssertion { view, _ ->
            val parentView = view.parent
            if (parentView != null && isEnabled().matches(parentView)) {
                throw AssertionError("View is present in the hierarchy and Enabled: " + HumanReadables.describe(view))
            }
        }
    }
}