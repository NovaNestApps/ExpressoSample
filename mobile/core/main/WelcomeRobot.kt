package com.mobile.app.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

object WelcomeRobot {
    fun visible() = apply {
        onView(withId(R.id.welcomeQuestionOne)).check(matches(isDisplayed()))
        onView(withId(R.id.welcomeButtonOne)).check(matches(isDisplayed()))
        onView(withId(R.id.welcomeButtonTwo)).check(matches(isDisplayed()))
    }

    fun tapPrimary() = apply { onView(withId(R.id.welcomeButtonOne)).perform(click()) }
    fun tapSecondary() = apply { onView(withId(R.id.welcomeButtonTwo)).perform(click()) }
}