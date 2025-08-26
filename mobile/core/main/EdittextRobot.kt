// androidTest/java/.../robots/view/UIEditTextRobot.kt
package com.mobile.ui.demo.robots.view

import androidx.test.espresso.action.ViewActions.replaceText
import com.mobile.core.uitesting.espresso.actions.Actions
import com.mobile.core.uitesting.espresso.assertions.DefaultAssertions
import com.mobile.core.uitesting.espresso.matcher.BaseMatchers
import com.mobile.ui.demo.R

fun uiEditTextScreen(block: UIEditTextRobot.() -> Unit) = UIEditTextRobot().apply(block)

class UIEditTextRobot {

    // ---- visibility smoke ----
    fun assertHintFieldVisible() =
        DefaultAssertions.isDisplayed(R.id.hintTextCase)

    fun assertSearchFieldVisible() =
        DefaultAssertions.isDisplayed(R.id.searchEditText)

    fun assertNumberFieldVisible() =
        DefaultAssertions.isDisplayed(R.id.editNumber)

    fun assertDisabledFieldVisible() =
        DefaultAssertions.isDisplayed(R.id.disabledCase)

    fun assertPasswordWithDrawableVisible() =
        DefaultAssertions.isDisplayed(R.id.passwordTextWithTextDrawable)

    fun assertWithErrorVisible() =
        DefaultAssertions.isDisplayed(R.id.editWithError)

    fun assertWithTipsVisible() =
        DefaultAssertions.isDisplayed(R.id.editWithTips)

    fun assertProgrammaticFieldVisible() =
        DefaultAssertions.isDisplayed(R.id.quickButtonXml) // use the id you add for the pure-code one if different

    // ---- common interactions ----
    fun typeInSearch(text: String) =
        Actions.replaceText(R.id.searchEditText, text, closeKb = true)

    fun typeInNumber(text: String) =
        Actions.replaceText(R.id.editNumber, text, closeKb = true)

    fun typeInPassword(text: String) =
        Actions.replaceText(R.id.passwordTextWithTextDrawable, text, closeKb = true)

    fun clearSearchWithEndDrawable() =
        Actions.clickDrawableEnd(R.id.searchEditText)  // helper below

    fun togglePasswordVisibility() =
        Actions.clickDrawableEnd(R.id.passwordTextWithTextDrawable)

    // ---- assertions using your matchers ----
    fun assertHint(text: String) =
        DefaultAssertions.isDisplayed(BaseMatchers.withText(text))

    fun assertSearchCleared() =
        DefaultAssertions.isDisplayed(BaseMatchers.withText("")) // caret stays; field empty

    fun assertDisabledNotEnabled() =
        DefaultAssertions.isDisabled(R.id.disabledCase)

    fun assertNumberInputType() =
        DefaultAssertions.isDisplayed(BaseMatchers.withInputMask("\\d+")) // or a custom withInputType matcher

    fun assertErrorShown(message: String) =
        DefaultAssertions.isDisplayed(BaseMatchers.withText(message))

    fun assertTipsShown(message: String) =
        DefaultAssertions.isDisplayed(BaseMatchers.withText(message))

    fun assertPasswordMasked() =
        DefaultAssertions.matches(
            R.id.passwordTextWithTextDrawable,
            BaseMatchers.withInputMask("•|\\*+") // or a matcher that checks transformation method
        )

    fun assertPasswordUnmasked() =
        DefaultAssertions.matches(
            R.id.passwordTextWithTextDrawable,
            BaseMatchers.withoutTransformationMethod()
        )
}