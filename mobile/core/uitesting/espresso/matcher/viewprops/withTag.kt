// Stub for withTag matcher
/**
 * Matches a View whose tag matches the provided object.
 *
 * This matcher assumes the tag is an exact object match. If you need to match by resource ID,
 * a different matcher (e.g., using `View.getTag(int key)`) would be required.
 *
 * @param expectedTag The object the view's tag should match. Can be null.
 */
fun withTag(expectedTag: Any?): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("with tag: $expectedTag")
        }

        override fun matchesSafely(item: View): Boolean {
            return item.tag == expectedTag
        }
    }