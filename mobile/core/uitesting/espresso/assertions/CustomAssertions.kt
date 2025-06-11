/**
 * Custom view assertions that go beyond standard Espresso matchers.
 */
class CustomAssertions {
    companion object {

        /**
         * Asserts that the view is NOT displayed.
         * Fails if view is present in the hierarchy and is visible.
         */
        fun isNotDisplayed(): ViewAssertion = ViewAssertion { view, _ ->
            if (view != null && isDisplayed().matches(view)) {
                throw AssertionError("View is present in the hierarchy and Displayed: " + HumanReadables.describe(view))
            }
        }

        /**
         * Asserts that the parent of the view is enabled.
         * Fails if parent is null or not enabled.
         */
        fun isParentEnabled(): ViewAssertion = ViewAssertion { view, _ ->
            val parentView = view.parent
            if (parentView != null && not(isEnabled()).matches(parentView)) {
                throw AssertionError("View is present in the hierarchy and Disabled: " + HumanReadables.describe(view))
            }
        }

        /**
         * Asserts that the parent of the view is disabled.
         * Fails if parent is enabled.
         */
        fun isParentDisabled(): ViewAssertion = ViewAssertion { view, _ ->
            val parentView = view.parent
            if (parentView != null && isEnabled().matches(parentView)) {
                throw AssertionError("View is present in the hierarchy and Enabled: " + HumanReadables.describe(view))
            }
        }
    }
}