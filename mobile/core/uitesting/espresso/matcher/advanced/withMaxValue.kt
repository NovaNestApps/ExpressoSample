// Stub for withMaxValue matcher
/**
 * Matches a ProgressBar (or subclass like SeekBar) whose maximum value is equal to `expectedMaxValue`.
 *
 * This matcher is useful for verifying the upper limit of a progress indicator or slider.
 *
 * @param expectedMaxValue The exact maximum value the ProgressBar should have.
 */
fun withMaxValue(expectedMaxValue: Int): Matcher<View> =
    object : TypeSafeMatcher<View>(ProgressBar::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("with max value: $expectedMaxValue")
        }

        override fun matchesSafely(item: View): Boolean {
            return (item as ProgressBar).max == expectedMaxValue
        }
    }