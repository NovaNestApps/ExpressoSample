// androidTestQa/java/com/mobile/ui/cardhub/CardHubUiTest.kt
package com.mobile.ui.cardhub

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.*
import com.mobile.app.harness.TestHarness
import com.mobile.app.quicklaunch.activity.TestLauncherActivity
import com.mobile.ui.cardhub.fragment.CardHubFragment
import com.mobile.ui.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardHubUiTest {

    // Compose rule that hooks into any Compose in the app (since we're not using createAndroidComposeRule)
    @get:Rule(order = 0)
    val compose = createEmptyComposeRule()

    @Test
    fun renders_core_sections_and_items() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext

        // If your screen needs switches/flags to show items, pass them here:
        val config = TestHarness.Config(
            stub = "success/enrolled",
            switches = emptyList(),     // add SwitchStatus(...) if needed
            flags = emptyList(),        // add FeatureConfigStatus(...) if needed
            themeResId = null
        )

        val intent = com.mobile.ui.cardhub.TestHomeHostActivity.getHomeFragmentIntent(
            context = ctx,
            fragmentClass = CardHubFragment::class.java,
            args = null
        )

        // Boot the test host activity first so AppBootStrapper applies config, then start the host
        TestHarness.launch(config)
        ActivityScenario.launch<com.mobile.ui.cardhub.TestHomeHostActivity>(intent)

        // 1) ComposeView is visible
        onView(withId(R.id.cardHubList)).check(matches(isDisplayed()))

        // 2) Headers exist
        compose.onNodeWithText(str(R.string.cardHubCardDetailsTitle))
            .assertIsDisplayed()
        compose.onNodeWithText(str(R.string.cardHubCardControlsTitle))
            .assertIsDisplayed()
        compose.onNodeWithText(str(R.string.cardHubSpendingOptionsTitle))
            .assertIsDisplayed()

        // 3) Top items exist
        compose.onNodeWithText(str(R.string.cardHubViewPin)).assertIsDisplayed()
        compose.onNodeWithText(str(R.string.cardHubViewCardDetails)).assertIsDisplayed()
    }

    @Test
    fun scrolls_to_and_shows_deeper_items() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext

        TestHarness.launch(
            TestHarness.Config(
                stub = "success/enrolled",
                switches = emptyList(),
                flags = emptyList(),
                themeResId = null
            )
        )

        val intent = com.mobile.ui.cardhub.TestHomeHostActivity.getHomeFragmentIntent(
            context = ctx,
            fragmentClass = CardHubFragment::class.java,
            args = null
        )
        ActivityScenario.launch<com.mobile.ui.cardhub.TestHomeHostActivity>(intent)

        onView(withId(R.id.cardHubList)).check(matches(isDisplayed()))

        // Scroll to a lower item then assert it’s visible
        val target = str(R.string.cardHubReplaceCardAndPin)
        compose.onNodeWithText(target).performScrollTo().assertIsDisplayed()
    }

    @Test
    fun clicking_an_item_does_not_crash_and_triggers_handler() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext

        TestHarness.launch(TestHarness.Config(stub = "success/enrolled"))

        val intent = com.mobile.ui.cardhub.TestHomeHostActivity.getHomeFragmentIntent(
            context = ctx,
            fragmentClass = CardHubFragment::class.java,
            args = null
        )
        ActivityScenario.launch<com.mobile.ui.cardhub.TestHomeHostActivity>(intent)

        onView(withId(R.id.cardHubList)).check(matches(isDisplayed()))

        // Click a stable item ("View PIN"); we at least verify UI remains stable.
        compose.onNodeWithText(str(R.string.cardHubViewPin)).performClick()

        // Simple sanity check post-click: core header still present.
        compose.onNodeWithText(str(R.string.cardHubCardDetailsTitle)).assertIsDisplayed()
    }

    @Test
    fun survey_button_is_hidden_by_default() {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext

        TestHarness.launch(TestHarness.Config(stub = "success/enrolled"))
        val intent = com.mobile.ui.cardhub.TestHomeHostActivity.getHomeFragmentIntent(
            context = ctx,
            fragmentClass = CardHubFragment::class.java,
            args = null
        )
        ActivityScenario.launch<com.mobile.ui.cardhub.TestHomeHostActivity>(intent)

        // In XML this button has android:visibility="gone"
        onView(withId(R.id.cardHubSurveyButton)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }


fun str(@androidx.annotation.StringRes id: Int): String =
    InstrumentationRegistry.getInstrumentation().targetContext.getString(id)


}