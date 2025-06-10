// Stub for withChildCount matcher
/**
 * Matches a ViewGroup that has an exact number of direct children.
 *
 * Usage:
 * onView(withId(R.id.my_linear_layout)).check(matches(withChildCount(3)))
 *
 * @param expectedChildCount The exact number of children the ViewGroup should have.
 */
fun withChildCount(expectedChildCount: Int): Matcher<View> =
    object : TypeSafeMatcher<View>(ViewGroup::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("with child count: $expectedChildCount")
        }

        override fun matchesSafely(item: View): Boolean {
            // We already declared ViewGroup.class in the constructor, so casting is safe
            return (item as ViewGroup).childCount == expectedChildCount
        }
    }