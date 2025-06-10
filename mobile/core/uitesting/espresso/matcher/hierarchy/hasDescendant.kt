// Stub for hasDescendant matcher
package com.mobile.core.uitesting.espresso.matcher.hierarchy

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Matches a view with a descendant matching the provided matcher.
 *
 * Usage: onView(withId(R.id.root)).check(matches(hasDescendant(withText("Child text"))))
 */
fun hasDescendant(descendantMatcher: Matcher<View>): Matcher<View> =
    object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("has descendant: ")
            descendantMatcher.describeTo(description)
        }
        override fun matchesSafely(view: View): Boolean {
            if (view !is ViewGroup) return false
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                if (descendantMatcher.matches(child)) return true
                if (child is ViewGroup && matchesSafely(child)) return true
            }
            return false
        }
    }
