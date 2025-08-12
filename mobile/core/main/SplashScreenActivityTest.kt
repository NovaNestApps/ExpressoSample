package com.mobile.ui.splash.activity

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.IdlingPolicies
import com.mobile.app.harness.TestHarness
import com.mobile.app.idling.registerIdlers
import com.mobile.app.idling.unregisterIdlers
import com.mobile.app.robots.LoginRobot
import com.mobile.app.robots.WelcomeRobot
import org.junit.*
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class SplashScreenActivityTest {

    private lateinit var scenario: androidx.test.core.app.ActivityScenario<com.mobile.app.quicklaunch.activity.TestLaunchActivity>

    @Before
    fun setUp() {
        // Make Espresso patient during app start
        IdlingPolicies.setIdlingResourceTimeout(10, TimeUnit.SECONDS)
        IdlingPolicies.setMasterPolicyTimeout(10, TimeUnit.SECONDS)
        registerIdlers()

        scenario = TestHarness.launch(
            TestHarness.Config(
                stub = "Enrolled",
                switches = emptyList(),
                flags = emptyList(),
                // themeResId = R.style.LloydsAppTheme  // set per-test if needed
            )
        )
    }

    @After
    fun tearDown() {
        unregisterIdlers()
        if (::scenario.isInitialized) scenario.close()
    }

    @Test
    fun welcome_to_login_happy_path() {
        WelcomeRobot.visible().tapPrimary()

        LoginRobot()
            .assertForgotDetailsScreenVisible()
            .assertLoginButtonEnabled(false)
            .enterUsername("admin")
            .enterPassword("password123")
            .assertLoginButtonEnabled(true)
            .clickLogin()
    }
}