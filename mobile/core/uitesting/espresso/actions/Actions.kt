package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

object Actions {
    // Clicks
    fun click() = ClickActions.click()
    fun doubleClick() = ClickActions.doubleClick()
    fun longClick() = ClickActions.longClick()
    fun clickOnChild(parentId: Int, childId: Int) = ClickActions.clickOnChildView(parentId, childId)
    fun clickOnChild(parentMatcher: Matcher<View>, childMatcher: Matcher<View>) = ClickActions.clickOnChild(parentMatcher, childMatcher)
    fun clickSpannable(textRes: Int) = ClickActions.clickSpannableText(textRes)

    // Scroll
    fun scrollTo() = ScrollActions.scrollTo()
    fun scrollInAnyScrollView() = ScrollActions.scrollInAnyScrollView()
    fun scrollToPosition(position: Int) = ScrollActions.scrollToPosition(position)
    fun swipeLeft() = ScrollActions.swipeLeft()
    fun swipeRight() = ScrollActions.swipeRight()
    fun swipeUp() = ScrollActions.swipeUp()
    fun swipeDown() = ScrollActions.swipeDown()

    // Text input
    fun typeText(text: String) = TextActions.typeText(text)
    fun replaceText(text: String) = TextActions.replaceText(text)
    fun clearText() = TextActions.clearText()
    fun pressImeActionButton() = TextActions.pressImeActionButton()
    fun closeSoftKeyboard() = TextActions.closeSoftKeyboard()
    fun pressBack() = TextActions.pressBack()

    // Wait/Retry
    fun waitForMatch(matcher: Matcher<View>, timeout: Long) = WaitActions.waitForMatch(matcher, timeout)
    fun waitForVisible(timeout: Long = 5000) = WaitActions.waitForVisible(timeout)

    // Switch/Toggle
    fun setSwitchState(state: Boolean) = SwitchActions.setSwitchToExpectedState(state)
}
