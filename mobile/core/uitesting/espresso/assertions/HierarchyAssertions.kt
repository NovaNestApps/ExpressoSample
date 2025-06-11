package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import org.hamcrest.Matcher

/**
 * Assertions for validating UI hierarchy relationships like descendants and siblings.
 */
interface HierarchyAssertions {

    /**
     * Asserts that a view with [viewId] has a descendant matching [descendantMatcher].
     */
    fun hasDescendant(@IdRes viewId: Int, descendantMatcher: Matcher<View>)

    /**
     * Asserts that a view matching [viewMatcher] has a descendant matching [descendantMatcher].
     */
    fun hasDescendant(viewMatcher: Matcher<View>, descendantMatcher: Matcher<View>)

    /**
     * Asserts that a view with [viewId] has a sibling matching [siblingMatcher].
     */
    fun hasSibling(@IdRes viewId: Int, siblingMatcher: Matcher<View>)

    /**
     * Asserts that a view matching [viewMatcher] has a sibling matching [siblingMatcher].
     */
    fun hasSibling(viewMatcher: Matcher<View>, siblingMatcher: Matcher<View>)
}