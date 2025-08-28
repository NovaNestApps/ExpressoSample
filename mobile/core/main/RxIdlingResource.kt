import androidx.test.espresso.IdlingResource
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.Scheduler
import java.util.concurrent.atomic.AtomicInteger

object RxIdlingResource : IdlingResource {

    private val counter = AtomicInteger(0)
    @Volatile private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = "RxJava2IdlingResource"

    override fun isIdleNow(): Boolean = counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    fun register() {
        RxJavaPlugins.setInitIoSchedulerHandler { wrapScheduler(it.createIoScheduler()) }
        RxJavaPlugins.setInitComputationSchedulerHandler { wrapScheduler(it.createComputationScheduler()) }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { wrapScheduler(it.createNewThreadScheduler()) }
        RxJavaPlugins.setInitSingleSchedulerHandler { wrapScheduler(it.createSingleScheduler()) }
    }

    private fun wrapScheduler(scheduler: Scheduler): Scheduler {
        return CountingScheduler(scheduler, counter) { callback?.onTransitionToIdle() }
    }

    fun unregister() {
        // Optional: reset handlers if needed
        RxJavaPlugins.reset()
    }
}

import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class CountingScheduler(
    private val actual: Scheduler,
    private val counter: AtomicInteger,
    private val onIdleCallback: () -> Unit
) : Scheduler() {

    override fun createWorker(): Worker {
        val worker = actual.createWorker()
        return object : Worker() {
            override fun schedule(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                counter.incrementAndGet()
                return worker.schedule({
                    try {
                        run.run()
                    } finally {
                        if (counter.decrementAndGet() == 0) onIdleCallback()
                    }
                }, delay, unit)
            }

            override fun dispose() = worker.dispose()

            override fun isDisposed(): Boolean = worker.isDisposed
        }
    }

    override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
        counter.incrementAndGet()
        return actual.scheduleDirect({
            try {
                run.run()
            } finally {
                if (counter.decrementAndGet() == 0) onIdleCallback()
            }
        }, delay, unit)
    }
}
