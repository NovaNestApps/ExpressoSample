// Stub for withCompoundDrawable matcher
package com.mobile.core.uitesting.espresso.matcher.drawable

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches a TextView that has a compound drawable on a specific side.
 * @param drawableResId - Resource ID of the expected drawable
 * @param position - 0=left/start, 1=top, 2=right/end, 3=bottom
 */
fun withCompoundDrawables(
    @DrawableRes leftDrawableResId: Int? = null,
    @DrawableRes topDrawableResId: Int? = null,
    @DrawableRes rightDrawableResId: Int? = null,
    @DrawableRes bottomDrawableResId: Int? = null,
    @ColorRes tintColorInt: Int? = null
): Matcher<View> =
    object : BoundedMatcher<View, TextView>(TextView::class.java) {
        override fun describeTo(description: Description) {
            description.appendText("Compound drawables don't match")
        }

        override fun matchesSafely(item: TextView): Boolean {
            val leftActual = item.compoundDrawables[0]
            val topActual = item.compoundDrawables[1]
            val rightActual = item.compoundDrawables[2]
            val bottomActual = item.compoundDrawables[3]
            return compare(leftDrawableResId, tintColorInt, leftActual, item) &&
                    compare(topDrawableResId, tintColorInt, topActual, item) &&
                    compare(rightDrawableResId, tintColorInt, rightActual, item) &&
                    compare(bottomDrawableResId, tintColorInt, bottomActual, item)
        }

        private fun compare(
            @DrawableRes expectedResId: Int?,
            @ColorRes tintColorInt: Int?,
            actualDrawable: Drawable?,
            view: View
        ): Boolean {
            if (expectedResId == null) return true
            val expectedDrawable = ContextCompat.getDrawable(view.context, expectedResId)?.mutate()
            if (tintColorInt != null) {
                expectedDrawable?.setTint(ContextCompat.getColor(view.context, tintColorInt))
            }
            return areDrawablesIdentical(expectedDrawable, actualDrawable)
        }

    }
