package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import org.hamcrest.Matcher

/**
 * General assertions for Espresso UI tests.
 * Defines common UI visibility, state, tag, and matcher-based checks.
 */
interface BaseAssertions {

    /** Asserts that a view with [viewId] is displayed. */
    fun isDisplayed(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is displayed. */
    fun isDisplayed(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] is not displayed. */
    fun isNotDisplayed(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is not displayed. */
    fun isNotDisplayed(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] is completely visible. */
    fun isCompletelyDisplayed(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is completely visible. */
    fun isCompletelyDisplayed(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] is enabled. */
    fun isEnabled(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is enabled. */
    fun isEnabled(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] is disabled. */
    fun isDisabled(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is disabled. */
    fun isDisabled(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] is checked. */
    fun isChecked(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is checked. */
    fun isChecked(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] is not checked. */
    fun isNotChecked(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is not checked. */
    fun isNotChecked(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] is selected. */
    fun isSelected(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is selected. */
    fun isSelected(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] is not selected. */
    fun isNotSelected(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] is not selected. */
    fun isNotSelected(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] does not exist in the hierarchy. */
    fun doesNotExist(@IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] does not exist in the hierarchy. */
    fun doesNotExist(viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] has the specified tag. */
    fun hasTag(tag: String, @IdRes viewId: Int)

    /** Asserts that a view matching [viewMatcher] has the specified tag. */
    fun hasTag(tag: String, viewMatcher: Matcher<View>)

    /** Asserts that a view with [viewId] matches the given matcher. */
    fun matches(@IdRes viewId: Int, matcher: Matcher<View>)

    /** Asserts that a view matched by [viewMatcher] matches the given [matcher]. */
    fun matches(viewMatcher: Matcher<View>, matcher: Matcher<View>)
}