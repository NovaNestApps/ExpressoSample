package com.mobile.app.harness

import android.content.Intent
import androidx.annotation.StyleRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.core.app.ActivityScenario
import com.mobile.app.quicklaunch.activity.TestLaunchActivity

object TestHarness {

    data class Config(
        val stub: String = "Enrolled",
        val switches: List<SwitchStatus> = emptyList(),
        val flags: List<FeatureConfigStatus> = emptyList(),
        @StyleRes val themeResId: Int? = null
    )

    fun intent(config: Config): Intent {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        return TestLaunchActivity.getIntent(
            context = ctx,
            stubScenario = config.stub,
            switchStatuses = config.switches,
            featureConfigStatuses = config.flags,
            themeResId = config.themeResId
        )
    }

    fun launch(config: Config): ActivityScenario<TestLaunchActivity> =
        ActivityScenario.launch(intent(config))
}