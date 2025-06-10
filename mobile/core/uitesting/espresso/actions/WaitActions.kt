package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.test.espresso.ViewAction
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Matcher

object WaitActions {
    fun waitForMatch(matcher: Matcher<View>, timeout: Long): ViewAction = object : ViewAction {
        override fun getDescription() = "Waiting for view matching $matcher"
        override fun getConstraints() = isRoot()
        override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
            val startTime = System.currentTimeMillis()
            val endTime = startTime + timeout
            do {
                for (child in TreeIterables.breadthFirstViewTraversal(view)) {
                    if (matcher.matches(child)) return
                }
                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)
            throw AssertionError("View matching $matcher not found within $timeout ms")
        }
    }

    fun waitForVisible(timeout: Long): ViewAction = object : ViewAction {
        override fun getDescription() = "Wait for any visible view"
        override fun getConstraints() = isRoot()
        override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
            // Custom logic for waiting for visible, or reuse waitForMatch with a visibility matcher
        }
    }
}
