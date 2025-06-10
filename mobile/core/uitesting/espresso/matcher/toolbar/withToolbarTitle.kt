// Stub for withToolbarTitle matcher
/**
 * Matches a Toolbar whose title matches the provided string.
 *
 * This matcher checks if the view is an instance of Toolbar and if its title
 * matches the `expectedTitle` exactly.
 *
 * @param expectedTitle The exact string the Toolbar's title should match.
 */
fun withToolbarTitle(expectedTitle: String): Matcher<View> =
    object : TypeSafeMatcher<View>(Toolbar::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("with Toolbar title: '$expectedTitle'")
        }

        override fun matchesSafely(item: View): Boolean {
            return (item as Toolbar).title != null && item.title.toString() == expectedTitle
        }
    }