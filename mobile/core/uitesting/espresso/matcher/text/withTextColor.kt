package com.mobile.core.uitesting.espresso.matcher.text

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches a TextView with the specified text color resource.
 */
fun withTextColor(expectedColorResId: Int): Matcher<View> = object : BoundedMatcher<View, TextView>(TextView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("TextView with color resource ID: $expectedColorResId")
    }

    override fun matchesSafely(item: TextView): Boolean {
        val context = item.context
        val expectedColor = ContextCompat.getColor(context, expectedColorResId)
        return item.currentTextColor == expectedColor
    }
}
