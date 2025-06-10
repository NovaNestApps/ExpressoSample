package com.mobile.core.uitesting.espresso.actions

import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions

object TextActions {
    fun typeText(text: String): ViewAction = ViewActions.typeText(text)
    fun replaceText(text: String): ViewAction = ViewActions.replaceText(text)
    fun clearText(): ViewAction = ViewActions.clearText()
    fun pressImeActionButton(): ViewAction = ViewActions.pressImeActionButton()
    fun closeSoftKeyboard(): ViewAction = ViewActions.closeSoftKeyboard()
    fun pressBack(): ViewAction = ViewActions.pressBack()
}
