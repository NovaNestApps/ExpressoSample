// app/src/androidTest/java/.../paymenthub/PaymentHubWelcomeScreenTest.kt
package your.pkg.androidtest.paymenthub

import android.content.Intent
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNode
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobile.ui.paymenthub.mvvm.welcome.PaymentHubContext // <-- your real type
import your.app.pkg.R
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaymentHubWelcomeScreenTest {

    @get:Rule
    val compose = createAndroidComposeRule<TestPaymentHubHostActivity>()

    private fun launchWith(ctx: PaymentHubContext) {
        val intent = Intent(
            androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext,
            TestPaymentHubHostActivity::class.java
        ).putExtra(TestPaymentHubHostActivity.ARG_CTX, ctx)

        compose.activityRule.scenario.close() // ensure clean start if reused
        androidx.test.core.app.ActivityScenario.launch<TestPaymentHubHostActivity>(intent)
    }

    @Test
    fun clicking_continue_calls_resume_with_context() {
        val ctx = PaymentHubContext.PAYMENT_HUB // pick the enum/value your app expects
        launchWith(ctx)

        // find "Continue" button (Compose) and click
        compose.onNode(hasText(compose.activity.getString(R.string.continue_)) and hasClickAction(), useUnmergedTree = true)
            .performClick()

        compose.waitForIdle()

        // assert handler was called with the same context
        val host = compose.activity
        assertEquals(ctx, host.resumedWith)
    }

    @Test
    fun clicking_how_it_works_calls_more_details_with_context() {
        val ctx = PaymentHubContext.PAYMENT_HUB
        launchWith(ctx)

        compose.onNode(hasText(compose.activity.getString(R.string.how_it_works)) and hasClickAction(), useUnmergedTree = true)
            .performClick()

        compose.waitForIdle()

        val host = compose.activity
        assertEquals(ctx, host.moreDetailsWith)
    }
}