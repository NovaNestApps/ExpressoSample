package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import org.hamcrest.Matcher

/**
 * Layout assertions for text, overlaps, and multi-line checks.
 */
interface LayoutAssertions {
    /** Checks that a TextView does not ellipsize its text. */
    fun noEllipsizedText(@IdRes viewId: Int)
    fun noEllipsizedText(viewMatcher: Matcher<View>)

    /** Checks that a button is not multi-line. */
    fun noMultilineButton(@IdRes viewId: Int)
    fun noMultilineButton(viewMatcher: Matcher<View>)

    /** Checks that the view does not overlap other views. */
    fun noOverlaps(@IdRes viewId: Int)
    fun noOverlaps(viewMatcher: Matcher<View>)
}

object DefaultLayoutAssertions : LayoutAssertions {
    override fun noEllipsizedText(@IdRes viewId: Int) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(viewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(not(hasEllipsizedText())))

    override fun noEllipsizedText(viewMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(viewMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(not(hasEllipsizedText())))

    override fun noMultilineButton(@IdRes viewId: Int) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(viewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(not(hasMultilineButton())))

    override fun noMultilineButton(viewMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(viewMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(not(hasMultilineButton())))

    override fun noOverlaps(@IdRes viewId: Int) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(viewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(not(hasOverlaps())))

    override fun noOverlaps(viewMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(viewMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(not(hasOverlaps())))
}
