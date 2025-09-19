@file:Suppress("FunctionName")

package your.pkg.androidtest.support

import android.content.Context
import androidx.core.os.bundleOf
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.yourpkg.R
import com.yourpkg.qa.TestHarness
import com.yourpkg.qa.TestHarness.Config
import com.yourpkg.qa.FeatureConfigStatus
import com.yourpkg.quicklaunch.activity.TestLauncherActivity
import com.yourpkg.retail.ui.supporthub.fragment.SupportHubAllCategoriesFragment
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SupportAllCategoriesUiTest {

    private val ctx: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setup() {
        // Make sure the flags that gate items/buttons are ON for test
        TestHarness.launch(
            Config(
                stub = "success/enrolled",
                switches = emptyList(),
                flags = listOf(
                    // category visibility / badges / journeys
                    FeatureConfigStatus("IS_STATEMENTS_AND_CONTACT_DETAILS_CONTENT_ENABLED", true),
                    FeatureConfigStatus("HELP_WITH_A_TRANSACTION_ENABLED", true),
                    FeatureConfigStatus("PREVENT_FRAUD_SUPPORT_ENABLED", true),
                    FeatureConfigStatus("COST_OF_LIVING_ENABLED", true),
                    FeatureConfigStatus("IS_NATIVE_COST_OF_LIVING_ENABLED", false), // use subcategory flow
                    // message-us buttons
                    FeatureConfigStatus("MESSAGE_US_ENABLED", true),
                    FeatureConfigStatus("MESSAGE_US_INFO_ENABLED", true),
                ),
                themeResId = R.style.LloydsBrandTheme
            )
        )
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    private fun launchAllCategories() {
        val intent = TestLauncherActivity.getFragmentIntent(
            context       = ctx,
            fragmentClass = SupportHubAllCategoriesFragment::class,
            args          = bundleOf(),                     // no args required
            themeResId    = R.style.LloydsBrandTheme,
            hostKind      = "home"                          // IMPORTANT: HomeComponent host
        )
        ActivityScenario.launch<TestLauncherActivity>(intent)
    }

    @Test
    fun renders_all_core_categories_and_message_buttons() {
        launchAllCategories()

        // List is a ComposeView; text assertions via Espresso are fine
        onView(withText("Support")).check(matches(isDisplayed()))
        onView(withText("Statements")).check(matches(isDisplayed()))
        onView(withText(containsString("Change of contact"))).check(matches(isDisplayed()))
        onView(withText(containsString("Paying in, out"))).check(matches(isDisplayed()))
        onView(withText("Card management")).check(matches(isDisplayed()))
        onView(withText("Transaction help")).check(matches(isDisplayed()))
        onView(withText("Prevent fraud")).check(matches(isDisplayed()))
        onView(withText(containsString("Cost of living"))).check(matches(isDisplayed()))

        // Message us buttons (ids from your XML)
        onView(withId(R.id.supportAllCategoriesMessageUsButton)).check(matches(isDisplayed()))
        onView(withId(R.id.supportAllCategoriesMessageUsInfoButton)).check(matches(isDisplayed()))
    }

    @Test
    fun tap_card_management_opens_card_hub_section() {
        launchAllCategories()

        onView(withText("Card management")).perform(click())

        // The sub-screen shows "Card management" header (from your Card hub)
        onView(withText("Card management")).check(matches(isDisplayed()))
        // You can strengthen this by checking for a well-known row on the card hub:
        // onView(withText("View PIN")).check(matches(isDisplayed()))
    }

    @Test
    fun tap_transaction_help_starts_statements_router_activity() {
        launchAllCategories()

        onView(withText("Transaction help")).perform(click())

        // Replace the class if yours differs
        Intents.intended(hasComponent("com.yourpkg.retail.ui.statements.StatementsDeepLinkRouterActivity"))
    }

    @Test
    fun tap_message_us_starts_chat() {
        launchAllCategories()

        onView(withId(R.id.supportAllCategoriesMessageUsButton)).perform(click())

        // Replace with your actual fully-qualified class name
        Intents.intended(hasComponent("com.yourpkg.mobilechat.MobileChatActivity"))
    }

    @Test
    fun tap_message_us_info_opens_info_screen() {
        launchAllCategories()

        onView(withId(R.id.supportAllCategoriesMessageUsInfoButton)).perform(click())

        // Replace with your actual fully-qualified class name
        Intents.intended(hasComponent("com.yourpkg.help.HelpWithMessageUsInfoActivity"))
    }
}