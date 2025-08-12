package com.mobile.app.idling

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource
import hu.akarnokd.rxjava2.schedulers.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins

object AppIdling {
    val nav = CountingIdlingResource("nav")
}

fun registerIdlers() {
    IdlingRegistry.getInstance().register(AppIdling.nav)
    RxJavaPlugins.setInitIoSchedulerHandler { Rx2Idler.create("io") }
    RxJavaPlugins.setInitComputationSchedulerHandler { Rx2Idler.create("comp") }
    RxJavaPlugins.setInitNewThreadSchedulerHandler { Rx2Idler.create("new") }
}

fun unregisterIdlers() {
    IdlingRegistry.getInstance().unregister(AppIdling.nav)
}