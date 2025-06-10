package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import org.hamcrest.Matcher

object ClickActions {
    fun click(): ViewAction = ViewActions.click()
    fun doubleClick(): ViewAction = ViewActions.doubleClick()
    fun longClick(): ViewAction = ViewActions.longClick()

    fun clickOnChildView(parentViewId: Int, childViewId: Int): ViewAction = object : ViewAction {
        override fun getConstraints() = ViewActions.click().constraints
        override fun getDescription() = "Click on a child view with specified ID"
        override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
            val parent = view.findViewById<View>(parentViewId)
            parent?.findViewById<View>(childViewId)?.performClick()
                ?: throw AssertionError("Child view with id $childViewId not found in parent $parentViewId")
        }
    }

    fun clickOnChild(parentMatcher: Matcher<View>, childMatcher: Matcher<View>): ViewAction = object : ViewAction {
        override fun getConstraints() = ViewActions.click().constraints
        override fun getDescription() = "Click on a child view matching matcher"
        override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
            val root = view
            val child = TreeIterables.breadthFirstViewTraversal(root).firstOrNull { childMatcher.matches(it) }
            child?.performClick() ?: throw AssertionError("Child matching matcher not found")
        }
    }

    fun clickSpannableText(textRes: Int): ViewAction = object : ViewAction {
        override fun getDescription() = "Click on ClickableSpan text"
        override fun getConstraints() = isAssignableFrom(android.widget.TextView::class.java)
        override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
            val context = view.context
            val textToMatch = context.getString(textRes)
            val textView = view as android.widget.TextView
            val text = textView.text
            if (text is android.text.Spanned) {
                val spans = text.getSpans(0, text.length, android.text.style.ClickableSpan::class.java)
                for (span in spans) {
                    val start = text.getSpanStart(span)
                    val end = text.getSpanEnd(span)
                    val spanText = text.subSequence(start, end).toString()
                    if (spanText == textToMatch) {
                        span.onClick(textView)
                        return
                    }
                }
            }
            throw AssertionError("No matching span found for text: $textToMatch")
        }
    }
}
