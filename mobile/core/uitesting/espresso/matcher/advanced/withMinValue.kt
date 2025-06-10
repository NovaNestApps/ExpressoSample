// Stub for withMinValue matcher
/**
 * Matches a ProgressBar (or subclass like SeekBar) whose minimum value is equal to `expectedMinValue`.
 *
 * Note: The `min` property for ProgressBar was added in API level 26 (Android O).
 * This matcher will only work correctly on devices running API 26 or higher for ProgressBar.
 * For older API levels, the `min` value is effectively 0.
 *
 * @param expectedMinValue The exact minimum value the ProgressBar should have.
 */
fun withMinValue(expectedMinValue: Int): Matcher<View> =
    object : TypeSafeMatcher<View>(ProgressBar::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("with min value: $expectedMinValue")
        }

        override fun matchesSafely(item: View): Boolean {
            // Check for API level to safely access item.min
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return (item as ProgressBar).min == expectedMinValue
            } else {
                // Before API 26, min is implicitly 0 for ProgressBar, so check if expectedMinValue is 0.
                return expectedMinValue == 0
            }
        }
    }