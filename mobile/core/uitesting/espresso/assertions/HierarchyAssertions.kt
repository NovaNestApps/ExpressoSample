package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import org.hamcrest.Matcher

/**
 * Assertions related to view hierarchy such as siblings and descendants.
 */
interface HierarchyAssertions {

    /**
     * Asserts that a view with the given ID has a descendant matching the provided matcher.
     *
     * @param viewId The ID of the parent view.
     * @param descendantMatcher Matcher for the expected descendant.
     */
    fun hasDescendant(@IdRes viewId: Int, descendantMatcher: Matcher<View>)

    /**
     * Asserts that a view matching the given matcher has a descendant matching the provided matcher.
     *
     * @param viewMatcher Matcher to locate the view.
     * @param descendantMatcher Matcher to verify within the view hierarchy.
     */
    fun hasDescendant(viewMatcher: Matcher<View>, descendantMatcher: Matcher<View>)

    /**
     * Asserts that a view with the given ID has a sibling matching the provided matcher.
     *
     * @param viewId The ID of the view to locate.
     * @param siblingMatcher Matcher for the sibling view.
     */
    fun hasSibling(@IdRes viewId: Int, siblingMatcher: Matcher<View>)

    /**
     * Asserts that a view matching the given matcher has a sibling matching the provided matcher.
     *
     * @param viewMatcher Matcher for the view.
     * @param siblingMatcher Matcher for the sibling.
     */
    fun hasSibling(viewMatcher: Matcher<View>, siblingMatcher: Matcher<View>)
}