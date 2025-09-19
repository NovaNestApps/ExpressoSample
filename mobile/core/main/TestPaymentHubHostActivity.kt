// app/src/androidTest/java/.../paymenthub/TestPaymentHubHostActivity.kt
package your.pkg.androidtest.paymenthub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import your.app.pkg.R
import com.mobile.ui.paymenthub.mvvm.welcome.PaymentHubWelcomeScreenFragment
import com.mobile.ui.paymenthub.mvvm.welcome.PaymentHubNavigationHandler
import com.mobile.ui.paymenthub.mvvm.welcome.PaymentHubContext // <-- your real type

/**
 * Minimal host that implements PaymentHubNavigationHandler so the fragment can attach.
 * It records calls so tests can assert navigation.
 */
class TestPaymentHubHostActivity :
    AppCompatActivity(),
    PaymentHubNavigationHandler {

    // State the test will assert
    @Volatile var resumedWith: PaymentHubContext? = null
        private set
    @Volatile var moreDetailsWith: PaymentHubContext? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Host the fragment immediately
        if (savedInstanceState == null) {
            val ctx = intent.getSerializableExtra(ARG_CTX) as PaymentHubContext
            val fragment = PaymentHubWelcomeScreenFragment.newInstance(ctx)
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment, "under-test")
                .commitNow()
        }
    }

    // === PaymentHubNavigationHandler ===
    override fun resumePaymentHubWelcomeFromWelcomeScreen(paymentHubContext: PaymentHubContext?) {
        resumedWith = paymentHubContext
    }

    override fun showPaymentHubWelcomeMoreDetailsScreen(paymentHubContext: PaymentHubContext?) {
        moreDetailsWith = paymentHubContext
    }

    companion object {
        const val ARG_CTX = "test-ctx"
    }
}