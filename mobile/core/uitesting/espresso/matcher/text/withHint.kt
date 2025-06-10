// Stub for withHint matcher
package com.mobile.core.uitesting.espresso.matcher.text

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches a TextView with the specified hint text.
 */
fun withHint(expectedHint: String): Matcher<View> = object : BoundedMatcher<View, TextView>(TextView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("TextView with hint: $expectedHint")
    }

    override fun matchesSafely(item: TextView): Boolean {
        return item.hint?.toString() == expectedHint
    }
}
