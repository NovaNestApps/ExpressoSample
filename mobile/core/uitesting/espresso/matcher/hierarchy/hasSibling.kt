// Stub for hasSibling matcher
/**
 * Matches a View that has at least one sibling (another child of the same parent)
 * matching the provided [siblingMatcher].
 *
 * Usage:
 * onView(withId(R.id.my_view)).check(matches(hasSibling(withText("Sibling Text"))))
 *
 * @param siblingMatcher A matcher to apply to the siblings of the view being tested.
 */
fun hasSibling(siblingMatcher: Matcher<View>): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("has sibling matching: ")
            siblingMatcher.describeTo(description)
        }

        override fun matchesSafely(item: View): Boolean {
            val parent = item.parent
            if (parent !is ViewGroup) {
                return false // Views without a ViewGroup parent don't have siblings
            }

            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                // Ensure we don't match the item itself, only its siblings
                if (child !== item && siblingMatcher.matches(child)) {
                    return true
                }
            }
            return false
        }
    }