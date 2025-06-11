package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import org.hamcrest.Matcher

/**
 * Base interface for common view assertions used in Espresso UI testing.
 * Provides functions to check visibility, enablement, selection, tag, and existence of views.
 */
interface BaseAssertions {

    /**
     * Asserts that the view with the given ID is displayed.
     * 
     * @param viewId The resource ID of the view.
     */
    fun isDisplayed(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is displayed.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isDisplayed(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID is not displayed.
     * 
     * @param viewId The resource ID of the view.
     */
    fun isNotDisplayed(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is not displayed.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isNotDisplayed(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID is completely displayed.
     * 
     * @param viewId The resource ID of the view.
     */
    fun isCompletelyDisplayed(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is completely displayed.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isCompletelyDisplayed(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID is enabled.
     * 
     * @param viewId The resource ID of the view.
     */
    fun isEnabled(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is enabled.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isEnabled(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID is disabled.
     * 
     * @param viewId The resource ID of the view.
     */
    fun isDisabled(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is disabled.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isDisabled(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID is checked (e.g., a checkbox or radio button).
     * 
     * @param viewId The resource ID of the view.
     */
    fun isChecked(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is checked.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isChecked(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID is not checked.
     * 
     * @param viewId The resource ID of the view.
     */
    fun isNotChecked(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is not checked.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isNotChecked(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID is selected.
     * 
     * @param viewId The resource ID of the view.
     */
    fun isSelected(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is selected.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isSelected(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID is not selected.
     * 
     * @param viewId The resource ID of the view.
     */
    fun isNotSelected(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher is not selected.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun isNotSelected(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID does not exist in the view hierarchy.
     * 
     * @param viewId The resource ID of the view.
     */
    fun doesNotExist(@IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher does not exist in the view hierarchy.
     * 
     * @param viewMatcher Matcher used to find the view.
     */
    fun doesNotExist(viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID has the specified tag.
     * 
     * @param tag Expected tag string.
     * @param viewId The resource ID of the view.
     */
    fun hasTag(tag: String, @IdRes viewId: Int)

    /**
     * Asserts that the view matching the given matcher has the specified tag.
     * 
     * @param tag Expected tag string.
     * @param viewMatcher Matcher used to find the view.
     */
    fun hasTag(tag: String, viewMatcher: Matcher<View>)

    /**
     * Asserts that the view with the given ID matches the provided matcher.
     * 
     * @param viewId The resource ID of the view.
     * @param matcher Matcher to assert against the view.
     */
    fun matches(@IdRes viewId: Int, matcher: Matcher<View>)

    /**
     * Asserts that the view matching the given matcher satisfies the given condition.
     * 
     * @param viewMatcher Matcher used to find the view.
     * @param matcher Matcher to assert against the view.
     */
    fun matches(viewMatcher: Matcher<View>, matcher: Matcher<View>)
}