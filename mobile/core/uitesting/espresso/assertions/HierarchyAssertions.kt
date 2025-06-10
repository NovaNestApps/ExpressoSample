package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import org.hamcrest.Matcher

/**
 * Hierarchy-based assertions for descendants and siblings.
 */
interface HierarchyAssertions {
    /** Checks if a view has a descendant matching the given matcher. */
    fun hasDescendant(@IdRes viewId: Int, descendantMatcher: Matcher<View>)
    fun hasDescendant(viewMatcher: Matcher<View>, descendantMatcher: Matcher<View>)

    /** Checks if a view has a sibling matching the given matcher. */
    fun hasSibling(@IdRes viewId: Int, siblingMatcher: Matcher<View>)
    fun hasSibling(viewMatcher: Matcher<View>, siblingMatcher: Matcher<View>)
}

object DefaultHierarchyAssertions : HierarchyAssertions {
    override fun hasDescendant(@IdRes viewId: Int, descendantMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(viewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.hasDescendant(descendantMatcher)))

    override fun hasDescendant(viewMatcher: Matcher<View>, descendantMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(viewMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.hasDescendant(descendantMatcher)))

    override fun hasSibling(@IdRes viewId: Int, siblingMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(viewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.hasSibling(siblingMatcher)))

    override fun hasSibling(viewMatcher: Matcher<View>, siblingMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(viewMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.hasSibling(siblingMatcher)))
}
