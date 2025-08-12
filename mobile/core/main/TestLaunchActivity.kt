package com.mobile.app.quicklaunch.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import com.mobile.app.bootstrap.AppBootstrapper
import javax.inject.Inject

class TestLaunchActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_STUB_SCENARIO = "EXTRA_STUB_SCENARIO"
        private const val EXTRA_SWITCH_STATUSES = "EXTRA_SWITCH_STATUSES"
        private const val EXTRA_FEATURE_CONFIG_STATUSES = "EXTRA_FEATURE_CONFIG_STATUSES"
        private const val EXTRA_THEME = "EXTRA_THEME"

        fun getIntent(
            context: Context,
            stubScenario: String,
            switchStatuses: List<SwitchStatus> = emptyList(),
            featureConfigStatuses: List<FeatureConfigStatus> = emptyList(),
            @StyleRes themeResId: Int? = null
        ): Intent = Intent(context, TestLaunchActivity::class.java).apply {
            putExtra(EXTRA_STUB_SCENARIO, stubScenario)
            putParcelableArrayListExtra(EXTRA_SWITCH_STATUSES, ArrayList(switchStatuses))
            putParcelableArrayListExtra(EXTRA_FEATURE_CONFIG_STATUSES, ArrayList(featureConfigStatuses))
            themeResId?.let { putExtra(EXTRA_THEME, it) }
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    @Inject lateinit var bootstrapper: AppBootstrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1) Apply test theme before any layout inflation
        intent.getIntExtra(EXTRA_THEME, 0).takeIf { it != 0 }?.let { setTheme(it) }

        super.onCreate(savedInstanceState)

        // 2) Inject test DI
        AndroidInjector.component(this, QaComponent::class.java).inject(this)

        // 3) Read config
        val stubScenario = intent.getStringExtra(EXTRA_STUB_SCENARIO) ?: "Enrolled"
        val switches = intent.getParcelableArrayListExtra<SwitchStatus>(EXTRA_SWITCH_STATUSES) ?: arrayListOf()
        val flags = intent.getParcelableArrayListExtra<FeatureConfigStatus>(EXTRA_FEATURE_CONFIG_STATUSES) ?: arrayListOf()

        // 4) Prime app and start the real entry activity
        bootstrapper.apply(stubScenario, switches, flags)
        startActivity(SplashActivity.getLaunchIntent(this))

        // 5) Keep back stack clean
        finish()
    }
}