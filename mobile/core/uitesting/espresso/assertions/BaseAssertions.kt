package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import org.hamcrest.Matcher

/**
 * General assertions for Espresso UI tests.
 */
interface BaseAssertions {

    /** Checks if a view is displayed. */
    fun isDisplayed(@IdRes viewId: Int)
    fun isDisplayed(viewMatcher: Matcher<View>)

    /** Checks if a view is not displayed. */
    fun isNotDisplayed(@IdRes viewId: Int)
    fun isNotDisplayed(viewMatcher: Matcher<View>)

    /** Checks if a view is completely displayed. */
    fun isCompletelyDisplayed(@IdRes viewId: Int)
    fun isCompletelyDisplayed(viewMatcher: Matcher<View>)

    /** Checks if a view is enabled. */
    fun isEnabled(@IdRes viewId: Int)
    fun isEnabled(viewMatcher: Matcher<View>)

    /** Checks if a view is disabled. */
    fun isDisabled(@IdRes viewId: Int)
    fun isDisabled(viewMatcher: Matcher<View>)

    /** Checks if a view is checked. */
    fun isChecked(@IdRes viewId: Int)
    fun isChecked(viewMatcher: Matcher<View>)

    /** Checks if a view is not checked. */
    fun isNotChecked(@IdRes viewId: Int)
    fun isNotChecked(viewMatcher: Matcher<View>)

    /** Checks if a view is selected. */
    fun isSelected(@IdRes viewId: Int)
    fun isSelected(viewMatcher: Matcher<View>)

    /** Checks if a view is not selected. */
    fun isNotSelected(@IdRes viewId: Int)
    fun isNotSelected(viewMatcher: Matcher<View>)

    /** Checks if a view does not exist. */
    fun doesNotExist(@IdRes viewId: Int)
    fun doesNotExist(viewMatcher: Matcher<View>)

    /** Checks if a view has the given tag. */
    fun hasTag(tag: String, @IdRes viewId: Int)
    fun hasTag(tag: String, viewMatcher: Matcher<View>)

    /** Checks if a view matches the given matcher. */
    fun matches(@IdRes viewId: Int, matcher: Matcher<View>)
    fun matches(viewMatcher: Matcher<View>, matcher: Matcher<View>)
}
