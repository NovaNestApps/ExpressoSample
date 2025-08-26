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

enum class CompoundSlot { START, TOP, END, BOTTOM }
fun Actions.clickCompoundDrawable(@IdRes viewId: Int, slot: CompoundSlot = CompoundSlot.END) =
    onView(withId(viewId)).perform(clickCompoundDrawable(slot))

/** Convenience: call by matcher */
fun Actions.clickCompoundDrawable(viewMatcher: Matcher<View>, slot: CompoundSlot = CompoundSlot.END) =
    onView(viewMatcher).perform(clickCompoundDrawable(slot))

/** The generic action (works for START/TOP/END/BOTTOM and RTL). */
fun clickCompoundDrawable(slot: CompoundSlot): ViewAction = object : ViewAction {

    override fun getDescription() = "Click compound drawable $slot on TextView"

    override fun getConstraints(): Matcher<View> =
        allOf(isDisplayed(), isAssignableFrom(TextView::class.java))

    override fun perform(uiController: UiController, view: View) {
        val tv = view as TextView

        // Resolve the drawable for the requested slot
        val rel = tv.compoundDrawablesRelative
        val abs = tv.compoundDrawables
        val drawable = when (slot) {
            CompoundSlot.START -> rel[0] ?: abs[0]
            CompoundSlot.TOP   -> abs[1]
            CompoundSlot.END   -> rel[2] ?: abs[2]
            CompoundSlot.BOTTOM-> abs[3]
        } ?: throw AssertionError("No $slot drawable present on this TextView")

        // Ensure bounds are usable
        if (drawable.bounds.isEmpty) {
            val w = if (drawable.intrinsicWidth  > 0) drawable.intrinsicWidth  else 1
            val h = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else 1
            drawable.setBounds(0, 0, w, h)
        }

        val screen = Rect()
        tv.getGlobalVisibleRect(screen)

        // Compute click coordinates
        val (x, y) = when (slot) {
            CompoundSlot.START -> {
                val cx = screen.left + tv.paddingStart + drawable.bounds.width() / 2f
                val cy = screen.exactCenterY()
                cx to cy
            }
            CompoundSlot.END -> {
                val cx = screen.right - tv.paddingEnd - drawable.bounds.width() / 2f
                val cy = screen.exactCenterY()
                cx to cy
            }
            CompoundSlot.TOP -> {
                val cx = screen.exactCenterX()
                val cy = screen.top + tv.paddingTop + drawable.bounds.height() / 2f
                cx to cy
            }
            CompoundSlot.BOTTOM -> {
                val cx = screen.exactCenterX()
                val cy = screen.bottom - tv.paddingBottom - drawable.bounds.height() / 2f
                cx to cy
            }
        }

        GeneralClickAction(
            Tap.SINGLE,
            CoordinatesProvider { floatArrayOf(x, y) },
            Press.FINGER
        ).perform(uiController, tv)
    }

    private fun Rect.exactCenterX() = left + width() / 2f
    private fun Rect.exactCenterY() = top + height() / 2f
}
}
