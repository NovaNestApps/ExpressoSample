package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions

object ScrollActions {
    fun scrollTo(): ViewAction = ViewActions.scrollTo()
    fun scrollToPosition(position: Int): ViewAction = object : ViewAction {
        override fun getDescription() = "Scroll RecyclerView to position $position"
        override fun getConstraints() = isAssignableFrom(androidx.recyclerview.widget.RecyclerView::class.java)
        override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
            val recyclerView = view as androidx.recyclerview.widget.RecyclerView
            recyclerView.scrollToPosition(position)
            uiController.loopMainThreadUntilIdle()
        }
    }
    fun swipeLeft(): ViewAction = ViewActions.swipeLeft()
    fun swipeRight(): ViewAction = ViewActions.swipeRight()
    fun swipeUp(): ViewAction = ViewActions.swipeUp()
    fun swipeDown(): ViewAction = ViewActions.swipeDown()

    fun scrollInAnyScrollView(): ViewAction = object : ViewAction {
        override fun getDescription() = "Scroll in any scroll view"
        override fun getConstraints() = ViewActions.scrollTo().constraints
        override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
            // Your implementation for complex/nested scrolls goes here
            // e.g., NestedScrollView, ScrollView, HorizontalScrollView, etc.
            ViewActions.scrollTo().perform(uiController, view)
        }
    }
}
