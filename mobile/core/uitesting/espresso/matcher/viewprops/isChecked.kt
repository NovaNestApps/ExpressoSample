package com.mobile.core.uitesting.espresso.matcher.viewprops

import android.view.View
import android.widget.Checkable
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Matches any Checkable (CheckBox, Switch, RadioButton) that is checked.
 */
fun isChecked(): Matcher<View> = object : BoundedMatcher<View, Checkable>(Checkable::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("is checked")
    }
    override fun matchesSafely(item: Checkable): Boolean = item.isChecked
}
// Stub for isChecked matcher
