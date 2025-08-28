import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingRegistry
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger

object RxIdlingResource : IdlingResource {

    private val counter = AtomicInteger(0)
    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = "RxJava2IdlingResource"

    override fun isIdleNow(): Boolean = counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    fun register() {
        RxJavaPlugins.setInitIoSchedulerHandler { wrapScheduler(Schedulers.io()) }
        RxJavaPlugins.setInitComputationSchedulerHandler { wrapScheduler(Schedulers.computation()) }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { wrapScheduler(Schedulers.newThread()) }
        RxJavaPlugins.setInitSingleSchedulerHandler { wrapScheduler(Schedulers.single()) }

        IdlingRegistry.getInstance().register(this)
    }

    fun unregister() {
        RxJavaPlugins.reset()
        IdlingRegistry.getInstance().unregister(this)
    }

    private fun wrapScheduler(scheduler: io.reactivex.Scheduler): io.reactivex.Scheduler {
        return object : io.reactivex.Scheduler() {
            override fun createWorker(): Worker {
                val worker = scheduler.createWorker()
                return object : Worker() {
                    override fun schedule(run: Runnable, delay: Long, unit: java.util.concurrent.TimeUnit): Disposable {
                        increment()
                        return worker.schedule({
                            try {
                                run.run()
                            } finally {
                                decrement()
                            }
                        }, delay, unit)
                    }

                    override fun dispose() = worker.dispose()
                    override fun isDisposed() = worker.isDisposed
                }
            }

            override fun scheduleDirect(run: Runnable, delay: Long, unit: java.util.concurrent.TimeUnit): Disposable {
                increment()
                return scheduler.scheduleDirect({
                    try {
                        run.run()
                    } finally {
                        decrement()
                    }
                }, delay, unit)
            }
        }
    }

    private fun increment() {
        counter.incrementAndGet()
    }

    private fun decrement() {
        if (counter.decrementAndGet() == 0) {
            callback?.onTransitionToIdle()
        }
    }
}