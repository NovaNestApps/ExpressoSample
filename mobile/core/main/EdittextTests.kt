// androidTest/java/.../UIEditTextUITest.kt
package com.mobile.ui.demo.test.main

import com.mobile.ui.demo.robots.view.uiEditTextScreen
import org.junit.Test

class UIEditTextUITest : TestJourneyStartHandler() { // reuse your base rule/harness

    @Test
    fun hint_field_shows_hint_and_accepts_text() = uiEditTextScreen {
        assertHintFieldVisible()
        assertHint("Hint text")                // label
        // type into the actual field with hint if it has id, else skip
    }

    @Test
    fun search_field_clears_via_end_drawable() = uiEditTextScreen {
        assertSearchFieldVisible()
        typeInSearch("What can we help you with?")
        clearSearchWithEndDrawable()
        assertSearchCleared()
    }

    @Test
    fun number_field_accepts_only_digits() = uiEditTextScreen {
        assertNumberFieldVisible()
        typeInNumber("12ab34")
        // If your EditText filters non-digits, the text should be "1234".
        DefaultAssertions.isDisplayed(BaseMatchers.withText("1234"))
    }

    @Test
    fun disabled_field_not_enabled_and_not_clickable() = uiEditTextScreen {
        assertDisabledFieldVisible()
        assertDisabledNotEnabled()
        DefaultAssertions.isNotClickable(BaseMatchers.withId(R.id.disabledCase))
    }

    @Test
    fun error_field_shows_message() = uiEditTextScreen {
        assertWithErrorVisible()
        // If error is set in XML, just assert the message TextView next to it:
        assertErrorShown("Error message")        // match exact text from your layout
    }

    @Test
    fun tips_field_shows_helper_text() = uiEditTextScreen {
        assertWithTipsVisible()
        assertTipsShown("UIText with tips message")
    }

    @Test
    fun password_with_textdrawable_toggles_mask() = uiEditTextScreen {
        assertPasswordWithDrawableVisible()
        typeInPassword("Password123")
        assertPasswordMasked()
        togglePasswordVisibility()
        assertPasswordUnmasked()
        togglePasswordVisibility()
        assertPasswordMasked()
    }

    @Test
    fun programmatic_edittext_is_rendered() = uiEditTextScreen {
        assertProgrammaticFieldVisible()
    }
}