// Stub for isFocusable matcher
/**
 * Matches a View that is focusable.
 */
fun isFocusable(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("is focusable")
    }

    override fun matchesSafely(item: View): Boolean = item.isFocusable
}