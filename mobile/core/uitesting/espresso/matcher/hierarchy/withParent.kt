// Stub for withParent matcher

/**
 * Matches a View whose parent matches the provided [parentMatcher].
 *
 * Usage:
 * onView(withText("Child Text")).check(matches(withParent(withId(R.id.parent_layout))))
 *
 * @param parentMatcher A matcher to apply to the parent of the view being tested.
 */
fun withParent(parentMatcher: Matcher<View>): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with parent matching: ")
            parentMatcher.describeTo(description)
        }

        override fun matchesSafely(item: View): Boolean {
            val parent = item.parent
            return parent is View && parentMatcher.matches(parent)
        }
    }
