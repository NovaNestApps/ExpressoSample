// Stub for withContentDescription matcher
/**
 * Matches a View whose content description matches the provided string.
 *
 * @param expectedDescription The exact string the content description should match.
 */
fun withContentDescription(expectedDescription: String): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with content description: '$expectedDescription'")
        }

        override fun matchesSafely(item: View): Boolean {
            return item.contentDescription != null && item.contentDescription.toString() == expectedDescription
        }
    }