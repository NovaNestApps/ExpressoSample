// Stub for isEnabled matcher
/**
 * Matches a View that is enabled.
 */
fun isEnabled(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("is enabled")
    }

    override fun matchesSafely(item: View): Boolean = item.isEnabled
}