// Stub for withFontSize matcher
package com.mobile.core.uitesting.espresso.matcher.text

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches a TextView with the given font size (in sp).
 */
fun withFontSize(expectedSizeSp: Float): Matcher<View> = object : BoundedMatcher<View, TextView>(TextView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("TextView with font size: $expectedSizeSp")
    }

    override fun matchesSafely(item: TextView): Boolean {
        val scaledSize = item.textSize / item.resources.displayMetrics.scaledDensity
        return scaledSize == expectedSizeSp
    }
}
