package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher

/**
 * Default implementation of general assertions.
 */
object DefaultAssertions : BaseAssertions {

    override fun isDisplayed(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(isDisplayed()))

    override fun isDisplayed(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(isDisplayed()))

    override fun isNotDisplayed(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(not(isDisplayed())))

    override fun isNotDisplayed(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(not(isDisplayed())))

    override fun isCompletelyDisplayed(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(isCompletelyDisplayed()))

    override fun isCompletelyDisplayed(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(isCompletelyDisplayed()))

    override fun isEnabled(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(isEnabled()))

    override fun isEnabled(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(isEnabled()))

    override fun isDisabled(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(not(isEnabled())))

    override fun isDisabled(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(not(isEnabled())))

    override fun isChecked(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(isChecked()))

    override fun isChecked(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(isChecked()))

    override fun isNotChecked(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(not(isChecked())))

    override fun isNotChecked(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(not(isChecked())))

    override fun isSelected(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(isSelected()))

    override fun isSelected(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(isSelected()))

    override fun isNotSelected(@IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(not(isSelected())))

    override fun isNotSelected(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(not(isSelected())))

    override fun doesNotExist(@IdRes viewId: Int) =
        onView(withId(viewId)).check(doesNotExist())

    override fun doesNotExist(viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(doesNotExist())

    override fun hasTag(tag: String, @IdRes viewId: Int) =
        onView(withId(viewId)).check(matches(withTagValue(org.hamcrest.Matchers.`is`(tag))))

    override fun hasTag(tag: String, viewMatcher: Matcher<View>) =
        onView(viewMatcher).check(matches(withTagValue(org.hamcrest.Matchers.`is`(tag))))

    override fun matches(@IdRes viewId: Int, matcher: Matcher<View>) =
        onView(withId(viewId)).check(matches(matcher))

    override fun matches(viewMatcher: Matcher<View>, matcher: Matcher<View>) =
        onView(viewMatcher).check(matches(matcher))
}
