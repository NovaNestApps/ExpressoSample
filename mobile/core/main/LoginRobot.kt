package com.mobile.app.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

class LoginRobot {

    fun assertLoginButtonEnabled(enabled: Boolean) = apply {
        onView(withId(R.id.loginButton))
            .check(matches(if (enabled) isEnabled() else isNotEnabled()))
    }

    fun enterUsername(username: String) = apply {
        onView(withId(R.id.username)).perform(replaceText(username), closeSoftKeyboard())
    }

    fun enterPassword(password: String) = apply {
        onView(withId(R.id.password)).perform(replaceText(password), closeSoftKeyboard())
    }

    fun assertForgotDetailsScreenVisible() = apply {
        onView(withId(R.id.forgotDetails)).check(matches(isDisplayed()))
    }

    fun clickLogin() = apply { onView(withId(R.id.loginButton)).perform(click()) }
}