package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.action.ViewActions
import org.hamcrest.Matcher

object RecyclerViewActions {

    /**
     * Performs a click on the item at the given position in a RecyclerView.
     * Usage: onView(withId(recyclerId)).perform(RecyclerViewActions.clickItemAtPosition(2))
     */
    fun clickItemAtPosition(position: Int): ViewAction =
        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click())

    /**
     * Performs a click on a child view within a RecyclerView item.
     * Usage: onView(withId(recyclerId)).perform(RecyclerViewActions.clickChildInItem(2, R.id.childView))
     */
    fun clickChildInItem(
        position: Int,
        @IdRes childViewId: Int
    ): ViewAction = RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
        position,
        object : ViewAction {
            override fun getConstraints(): Matcher<View> = ViewActions.click().constraints
            override fun getDescription() = "Click child view with id $childViewId at position $position"
            override fun perform(uiController: UiController, view: View) {
                val child = view.findViewById<View>(childViewId)
                child?.performClick()
            }
        }
    )
}
