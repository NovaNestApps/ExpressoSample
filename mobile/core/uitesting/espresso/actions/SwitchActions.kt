package com.mobile.core.uitesting.espresso.actions

import android.view.View
import androidx.test.espresso.ViewAction

/**
 * Contains a reusable action for setting the checked state of a Switch view.
 */
object SwitchActions {

    /**
     * Sets the checked state of a Switch (e.g., ON/OFF).
     *
     * @param expectedState Desired checked state to set (`true` or `false`)
     */
    fun setSwitchToExpectedState(expectedState: Boolean): ViewAction = object : ViewAction {
        override fun getDescription() = "Set Switch to expected checked state"

        override fun getConstraints() = isAssignableFrom(Switch::class.java)

        override fun perform(uiController: UiController, view: View) {
            val switchView = view as? Switch
            if (switchView != null && switchView.isChecked != expectedState) {
                switchView.isChecked = expectedState
            }
        }
    }
}