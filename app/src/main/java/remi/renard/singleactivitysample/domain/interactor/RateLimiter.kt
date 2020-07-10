package remi.renard.singleactivitysample.domain.interactor

import android.os.SystemClock
import androidx.collection.ArrayMap
import java.util.concurrent.TimeUnit

/**
 * Utility class that decides whether we should fetch some data or not.
 *
 * @param timeout The time corresponding to the [TimeUnit] to have a valid cache.
 * @param timeUnit The time unit corresponding to a [TimeUnit] value.
 */
class RateLimiter constructor(timeout: Int, timeUnit: TimeUnit) {

    private val timestamps = ArrayMap<String, Long>()
    private val timeout: Long = timeUnit.toMillis(timeout.toLong())

    /**
     * Check if the key has expired.
     *
     * @param key the key to check.
     */
    @Synchronized
    fun shouldFetch(key: String): Boolean {
        val lastFetched = timestamps[key]
        val now = now()
        if (lastFetched == null) {
            timestamps[key] = now
            return true
        }
        if (now - lastFetched > timeout) {
            timestamps[key] = now
            return true
        }
        return false
    }

    /**
     * Remove the key from the map.
     *
     * @param key the key to remove.
     */
    @Synchronized
    fun reset(key: String) {
        timestamps.remove(key)
    }

    private fun now(): Long {
        return SystemClock.uptimeMillis()
    }
}
