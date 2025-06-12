package com.mobile.core.uitesting.espresso.matcher.text

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches a TextView with the specified text value.
 */
fun withText(expectedText: String): Matcher<View> = object : BoundedMatcher<View, TextView>(TextView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("TextView with text: $expectedText")
    }

    override fun matchesSafely(item: TextView): Boolean {
        return item.text.toString() == expectedText
    }
}
/**
 * Matches a TextView whose text matches the given Regex.
 */
fun withTextPattern(regex: Regex): Matcher<View> =
    object : BoundedMatcher<View, TextView>(TextView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("TextView with text matching regex: $regex")
        }
        override fun matchesSafely(item: TextView): Boolean {
            return regex.matches(item.text)
        }
    }
