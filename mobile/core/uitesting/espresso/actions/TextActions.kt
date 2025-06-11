package com.mobile.core.uitesting.espresso.actions

import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions

/**
 * Contains reusable text input and keyboard-related actions for Espresso.
 */
object TextActions {

    /**
     * Types the given text into an input field.
     */
    fun typeText(text: String): ViewAction = ViewActions.typeText(text)

    /**
     * Replaces the current text with the provided value.
     */
    fun replaceText(text: String): ViewAction = ViewActions.replaceText(text)

    /**
     * Clears the current text from an input field.
     */
    fun clearText(): ViewAction = ViewActions.clearText()

    /**
     * Simulates pressing the IME action (Done/Next) on the keyboard.
     */
    fun pressImeActionButton(): ViewAction = ViewActions.pressImeActionButton()

    /**
     * Closes the soft keyboard.
     */
    fun closeSoftKeyboard(): ViewAction = ViewActions.closeSoftKeyboard()

    /**
     * Simulates pressing the back button.
     */
    fun pressBack(): ViewAction = ViewActions.pressBack()
}