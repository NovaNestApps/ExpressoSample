package com.mobile.core.uitesting.espresso.matcher.text

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches a TextView with an input mask pattern (simple contains check).
 */
fun withInputMask(maskPattern: String): Matcher<View> = object : BoundedMatcher<View, TextView>(TextView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("TextView with input mask containing: $maskPattern")
    }

    override fun matchesSafely(item: TextView): Boolean {
        return item.text.contains(Regex(maskPattern))
    }
}
// Stub for withInputMask matcher
