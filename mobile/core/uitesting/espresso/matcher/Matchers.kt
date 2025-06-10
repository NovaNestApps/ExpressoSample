package com.mobile.core.uitesting.espresso.matcher

import com.mobile.core.uitesting.espresso.matcher.text.*
import com.mobile.core.uitesting.espresso.matcher.drawable.*
import com.mobile.core.uitesting.espresso.matcher.hierarchy.*
import com.mobile.core.uitesting.espresso.matcher.recyclerview.*
import com.mobile.core.uitesting.espresso.matcher.toolbar.*
import com.mobile.core.uitesting.espresso.matcher.viewprops.*
import com.mobile.core.uitesting.espresso.matcher.advanced.*

object Matchers {
    val withText = ::withText
    val withHint = ::withHint
    val withBoldText = ::withBoldText
    val withFontSize = ::withFontSize
    val withTextColor = ::withTextColor
    val withInputMask = ::withInputMask

    val withDrawable = ::withDrawable
    val withCompoundDrawable = ::withCompoundDrawable
    val withBackgroundColor = ::withBackgroundColor

    val withParent = ::withParent
    val hasDescendant = ::hasDescendant
    val hasSibling = ::hasSibling
    val withChildCount = ::withChildCount
    val withIndex = ::withIndex

    val withRecyclerViewItem = ::withRecyclerViewItem
    val recyclerViewDoesNotContain = ::recyclerViewDoesNotContain

    val withToolbarTitle = ::withToolbarTitle

    val isChecked = ::isChecked
    val isEnabled = ::isEnabled
    val isFocusable = ::isFocusable
    val isSelected = ::isSelected
    val isClickable = ::isClickable
    val withTag = ::withTag
    val withContentDescription = ::withContentDescription

    val withMinValue = ::withMinValue
    val withMaxValue = ::withMaxValue
}
