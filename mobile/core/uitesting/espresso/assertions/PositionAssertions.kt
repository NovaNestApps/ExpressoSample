package com.mobile.core.uitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import org.hamcrest.Matcher

/**
 * Position assertions for relative view position/layout.
 */
interface PositionAssertions {
    /** Checks if leftView is left of rightView. */
    fun isLeftOf(@IdRes leftViewId: Int, @IdRes rightViewId: Int)
    fun isLeftOf(leftMatcher: Matcher<View>, rightMatcher: Matcher<View>)

    /** Checks if topView is above bottomView. */
    fun isAbove(@IdRes topViewId: Int, @IdRes bottomViewId: Int)
    fun isAbove(topMatcher: Matcher<View>, bottomMatcher: Matcher<View>)

    /** Checks if bottomView is below topView. */
    fun isBelow(@IdRes bottomViewId: Int, @IdRes topViewId: Int)
    fun isBelow(bottomMatcher: Matcher<View>, topMatcher: Matcher<View>)

    /** Checks if rightView is right of leftView. */
    fun isRightOf(@IdRes rightViewId: Int, @IdRes leftViewId: Int)
    fun isRightOf(rightMatcher: Matcher<View>, leftMatcher: Matcher<View>)
}

object DefaultPositionAssertions : PositionAssertions {
    override fun isLeftOf(leftViewId: Int, rightViewId: Int) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(leftViewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isLeftOf(androidx.test.espresso.matcher.ViewMatchers.withId(rightViewId))))

    override fun isLeftOf(leftMatcher: Matcher<View>, rightMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(leftMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isLeftOf(rightMatcher)))

    override fun isAbove(topViewId: Int, bottomViewId: Int) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(topViewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isAbove(androidx.test.espresso.matcher.ViewMatchers.withId(bottomViewId))))

    override fun isAbove(topMatcher: Matcher<View>, bottomMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(topMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isAbove(bottomMatcher)))

    override fun isBelow(bottomViewId: Int, topViewId: Int) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(bottomViewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isBelow(androidx.test.espresso.matcher.ViewMatchers.withId(topViewId))))

    override fun isBelow(bottomMatcher: Matcher<View>, topMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(bottomMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isBelow(topMatcher)))

    override fun isRightOf(rightViewId: Int, leftViewId: Int) =
        androidx.test.espresso.Espresso.onView(androidx.test.espresso.matcher.ViewMatchers.withId(rightViewId))
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isRightOf(androidx.test.espresso.matcher.ViewMatchers.withId(leftViewId))))

    override fun isRightOf(rightMatcher: Matcher<View>, leftMatcher: Matcher<View>) =
        androidx.test.espresso.Espresso.onView(rightMatcher)
            .check(androidx.test.espresso.assertion.ViewAssertions.matches(androidx.test.espresso.matcher.ViewMatchers.isRightOf(leftMatcher)))
}
