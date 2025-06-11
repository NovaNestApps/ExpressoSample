package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.test.espresso.ViewAction
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Matcher

/**
 * Contains reusable waiting actions for handling async UI rendering or animations.
 */
object WaitActions {

    /**
     * Waits until a view matching [matcher] is found or timeout is reached.
     *
     * @param matcher Matcher for the view to wait for
     * @param timeout Timeout in milliseconds
     */
    fun waitForMatch(matcher: Matcher<View>, timeout: Long): ViewAction = object : ViewAction {
        override fun getDescription() = "Waiting for view matching $matcher"

        override fun getConstraints() = isRoot()

        override fun perform(uiController: UiController, view: View) {
            val startTime = System.currentTimeMillis()
            val endTime = startTime + timeout
            do {
                for (child in TreeIterables.breadthFirstViewTraversal(view)) {
                    if (matcher.matches(child)) return
                }
                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)

            throw PerformException.Builder()
                .withActionDescription(description)
                .withViewDescription("Timed out")
                .withCause(TimeoutException())
                .build()
        }
    }

    /**
     * Waits for any visible view to appear within the timeout period.
     *
     * @param timeout Timeout in milliseconds
     */
    fun waitForVisible(timeout: Long): ViewAction = object : ViewAction {
        override fun getDescription() = "Wait for any visible view"

        override fun getConstraints() = isRoot()

        override fun perform(uiController: UiController, view: View) {
            // Add matcher check for visibility if needed
            ViewActions.scrollTo().perform(uiController, view)
        }
    }
}