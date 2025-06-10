package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.test.espresso.ViewAction

object SwitchActions {
    fun setSwitchToExpectedState(expectedState: Boolean): ViewAction = object : ViewAction {
        override fun getDescription() = "Set Switch to expected checked state"
        override fun getConstraints() = isAssignableFrom(android.widget.Switch::class.java)
        override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
            val switchView = view as? android.widget.Switch
            if (switchView != null && switchView.isChecked != expectedState) {
                switchView.isChecked = expectedState
            }
        }
    }
}
