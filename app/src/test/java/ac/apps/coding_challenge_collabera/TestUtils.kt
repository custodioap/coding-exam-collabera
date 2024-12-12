package ac.apps.coding_challenge_collabera

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

// Utility function to observe LiveData and return its value synchronously
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,  // Timeout duration
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {} // A block to be executed after observe is set
): T {
    var data: T? = null
    // Use CountDownLatch to block the thread until the LiveData updates or times out
    val latch = CountDownLatch(1)

    // Observer that will capture the LiveData value
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown() // Signal that LiveData value has been updated
            this@getOrAwaitValue.removeObserver(this) // Remove observer once value is received
        }
    }

    // Set the observer to the LiveData
    this.observeForever(observer)

    // Run the optional block after observing
    afterObserve.invoke()

    // Wait for the LiveData value to be updated or timeout
    latch.await(time, timeUnit)

    // Return the data captured by the observer
    @Suppress("UNCHECKED_CAST")
    return data as T
}
