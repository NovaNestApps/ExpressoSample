package com.mobile.core.uitesting.espresso.matcher.drawable

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches an ImageView with a specific drawable, supporting resource ID, optional tint, and bitmap comparison.
 *
 * Usage:
 *   onView(withId(R.id.img)).check(matches(withDrawable(R.drawable.ic_check)))
 *   onView(withId(R.id.img)).check(matches(withDrawable(resId = R.drawable.ic_check, tintColor = R.color.accent)))
 */
fun withDrawable(
    @DrawableRes resId: Int = -1,
    @ColorRes tintColor: Int? = null,
    expectedBitmap: Bitmap? = null
): Matcher<View> = object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("with drawable id: $resId, tint: $tintColor, bitmap: $expectedBitmap")
    }

    override fun matchesSafely(imageView: ImageView): Boolean {
        val actualDrawable = imageView.drawable
        val expectedDrawable = if (resId != -1)
            ContextCompat.getDrawable(imageView.context, resId)?.mutate()
        else null

        if (tintColor != null && expectedDrawable != null) {
            expectedDrawable.setTint(ContextCompat.getColor(imageView.context, tintColor))
        }

        // Bitmap comparison if provided
        if (expectedBitmap != null) {
            val actualBitmap = (actualDrawable as? BitmapDrawable)?.bitmap
            return actualBitmap != null && expectedBitmap.sameAs(actualBitmap)
        }

        // Drawable comparison (using constantState or fall back to pixel-by-pixel)
        return areDrawablesIdentical(expectedDrawable, actualDrawable)
    }

    private fun areDrawablesIdentical(expected: Drawable?, actual: Drawable?): Boolean {
        // Fast path
        if (expected == null || actual == null) return false
        if (expected.constantState != null && expected.constantState == actual.constantState) return true
        // Fallback: bitmap pixel-by-pixel (robust, for vector drawables etc.)
        val expectedBitmap = (expected as? BitmapDrawable)?.bitmap
        val actualBitmap = (actual as? BitmapDrawable)?.bitmap
        return expectedBitmap != null && actualBitmap != null && expectedBitmap.sameAs(actualBitmap)
    }
}