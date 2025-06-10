// Stub for isSelected matcher
/**
 * Matches a View that is selected.
 */
fun isSelected(): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("is selected")
    }

    override fun matchesSafely(item: View): Boolean = item.isSelected
}