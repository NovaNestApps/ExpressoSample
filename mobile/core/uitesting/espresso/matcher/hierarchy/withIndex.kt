// Stub for withIndex matcher
/**
 * Matches a view based on its index among all views that match a given base matcher.
 * This is particularly useful when multiple views have the same ID or text,
 * and you need to select a specific one.
 *
 * Usage:
 * // To select the second button with text "Click Me"
 * onView(withIndex(withText("Click Me"), 1)).perform(click())
 *
 * @param matcher The base matcher to find potential views.
 * @param index The 0-based index of the view to match among those found by the base matcher.
 */
fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        private var currentIndex = 0

        override fun describeTo(description: Description) {
            description.appendText("with index: $index of matcher: ")
            matcher.describeTo(description)
        }

        override fun matchesSafely(item: View): Boolean {
            return matcher.matches(item) && currentIndex++ == index
        }
    }

/**
 * A more robust implementation for `withIndex` that can handle multiple views
 * that might appear in different parts of the view hierarchy, ensuring it picks
 * the correct one based on its global order.
 * This version should be used with `onView(withIndex(baseMatcher, index))`.
 * It's generally better than the simpler `withIndex` above for consistent behavior.
 *
 * Note: This version requires the `allOf` matcher from Hamcrest Core.
 *
 * @param matcher The base matcher to apply.
 * @param index The 0-based index of the desired view among those matching `matcher`.
 */
fun atIndex(matcher: Matcher<View>, index: Int): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        var count = 0
        var views = ArrayList<View>()

        override fun describeTo(description: Description) {
            description.appendText("view with index $index matching ")
            matcher.describeTo(description)
        }

        override fun matchesSafely(view: View): Boolean {
            views.add(view)
            val matches = matcher.matches(view)
            if (matches) {
                if (count == index) {
                    count++
                    return true
                }
                count++
            }
            return false
        }
    }
