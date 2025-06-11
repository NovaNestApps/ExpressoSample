package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions

/**
 * Contains reusable scroll and swipe actions for use in Espresso UI tests.
 */
object ScrollActions {

    /**
     * Scrolls to a view using Espresso's default scroll behavior.
     */
    fun scrollTo(): ViewAction = ViewActions.scrollTo()

    /**
     * Scrolls to a specific position in a RecyclerView.
     *
     * @param position The index of the item to scroll to
     */
    fun scrollToPosition(position: Int): ViewAction = object : ViewAction {
        override fun getDescription() = "Scroll RecyclerView to position $position"

        override fun getConstraints() = isAssignableFrom(RecyclerView::class.java)

        override fun perform(uiController: UiController, view: View) {
            val recyclerView = view as RecyclerView
            recyclerView.scrollToPosition(position)
            uiController.loopMainThreadUntilIdle()
        }
    }

    /**
     * Swipes the view left.
     */
    fun swipeLeft(): ViewAction = ViewActions.swipeLeft()

    /**
     * Swipes the view right.
     */
    fun swipeRight(): ViewAction = ViewActions.swipeRight()

    /**
     * Swipes the view up.
     */
    fun swipeUp(): ViewAction = ViewActions.swipeUp()

    /**
     * Swipes the view down.
     */
    fun swipeDown(): ViewAction = ViewActions.swipeDown()

    /**
     * Scrolls to a view inside any type of scrollable container.
     * Useful for nested or horizontal scrolls.
     */
    fun scrollInAnyScrollView(): ViewAction = object : ViewAction {
        override fun getDescription() = "Scroll in any scroll view"

        override fun getConstraints() = ViewActions.scrollTo().constraints

        override fun perform(uiController: UiController, view: View) {
            // Support nested scroll views, horizontal scrolls, etc.
            ViewActions.scrollTo().perform(uiController, view)
        }
    }
}