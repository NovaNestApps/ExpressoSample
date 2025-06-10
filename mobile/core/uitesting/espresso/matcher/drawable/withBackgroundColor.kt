// Stub for withBackgroundColor matcher
package com.mobile.core.uitesting.espresso.matcher.drawable

import android.view.View
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches a view with the specified background color, either by resource or color hex code.
 *
 * Usage:
 *   onView(withId(R.id.view)).check(matches(withBackgroundColor(resId = R.color.red)))
 *   onView(withId(R.id.view)).check(matches(withBackgroundColor(colorCode = "#FF0000")))
 */
fun withBackgroundColor(
    @ColorRes resId: Int = -1,
    colorCode: String = ""
): Matcher<View> = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("with background color: $resId or $colorCode")
    }

    override fun matchesSafely(item: View): Boolean {
        val expectedColor = when {
            resId != -1 -> item.resources.getColor(resId, item.context.theme)
            colorCode.isNotEmpty() -> Color.parseColor(colorCode)
            else -> return false
        }
        val background = item.background
        return background is ColorDrawable && background.color == expectedColor
    }
}