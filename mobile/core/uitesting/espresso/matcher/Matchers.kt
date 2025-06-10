package com.mobile.core.uitesting.espresso.matcher

import com.mobile.core.uitesting.espresso.matcher.text.*
import com.mobile.core.uitesting.espresso.matcher.drawable.*
import com.mobile.core.uitesting.espresso.matcher.hierarchy.*
import com.mobile.core.uitesting.espresso.matcher.recyclerview.*
import com.mobile.core.uitesting.espresso.matcher.toolbar.*
import com.mobile.core.uitesting.espresso.matcher.viewprops.*
import com.mobile.core.uitesting.espresso.matcher.advanced.*

oobject Matchers {
    val withText: (String) -> Matcher<View> = ::withText
    val withHint: (String) -> Matcher<View> = ::withHint
    val withBoldText: () -> Matcher<View> = ::withBoldText
    val withFontSize: (Float) -> Matcher<View> = ::withFontSize
    val withTextColor: (Int) -> Matcher<View> = ::withTextColor
    val withInputMask: (String) -> Matcher<View> = ::withInputMask

    val withDrawable: (Int, Int?, Bitmap?) -> Matcher<View> = ::withDrawable
    val withCompoundDrawable: (Int?, Int?, Int?, Int?, Int?) -> Matcher<View> = ::withCompoundDrawable
    val withBackgroundColor: (Int, String) -> Matcher<View> = ::withBackgroundColor

    val withParent: (Matcher<View>) -> Matcher<View> = ::withParent
    val hasDescendant: (Matcher<View>) -> Matcher<View> = ::hasDescendant
    val hasSibling: (Matcher<View>) -> Matcher<View> = ::hasSibling
    val withChildCount: (Int) -> Matcher<View> = ::withChildCount
    val withIndex: (Matcher<View>, Int) -> Matcher<View> = ::withIndex

    val withRecyclerViewItem: (Int, Matcher<View>) -> Matcher<View> = ::withRecyclerViewItem
    val recyclerViewDoesNotContain: (Matcher<View>) -> Matcher<View> = ::recyclerViewDoesNotContain

    val withToolbarTitle: (String) -> Matcher<View> = ::withToolbarTitle

    val isChecked: () -> Matcher<View> = ::isChecked
    val isEnabled: () -> Matcher<View> = ::isEnabled
    val isFocusable: () -> Matcher<View> = ::isFocusable
    val isSelected: () -> Matcher<View> = ::isSelected
    val isClickable: () -> Matcher<View> = ::isClickable
    val withTag: (String) -> Matcher<View> = ::withTag
    val withContentDescription: (String) -> Matcher<View> = ::withContentDescription

    val withMinValue: (Int) -> Matcher<View> = ::withMinValue
    val withMaxValue: (Int) -> Matcher<View> = ::withMaxValue
}