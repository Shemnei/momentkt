package momentkt

import momentkt.MomentKt.AVG_LENGTH_YEAR
import java.time.Duration
import java.time.Period

val Number.nanos: Duration get() = Duration.ofNanos(this.toLong())

val Number.millis: Duration
    get() {
        return when (this) {
            is Double -> Duration.ofNanos((this * 1000000).toLong())
            is Float -> Duration.ofNanos((this * 1000000).toLong())
            else -> Duration.ofMillis(this.toLong())
        }
    }

val Number.seconds: Duration
    get() {
        return when (this) {
            is Double -> Duration.ofMillis((this * 1000).toLong())
            is Float -> Duration.ofMillis((this * 1000).toLong())
            else -> Duration.ofSeconds(this.toLong())
        }
    }

val Number.minutes: Duration
    get() {
        return when (this) {
            is Double -> Duration.ofSeconds((this * 60).toLong())
            is Float -> Duration.ofSeconds((this * 60).toLong())
            else -> Duration.ofMinutes(this.toLong())
        }
    }

val Number.hours: Duration
    get() {
        return when (this) {
            is Double -> Duration.ofMinutes((this * 60).toLong())
            is Float -> Duration.ofMinutes((this * 60).toLong())
            else -> Duration.ofHours(this.toLong())
        }
    }

val Number.days: Period
    get() = Period.ofDays(this.toInt())

val Number.weeks: Period get() = Period.ofDays((this.toInt() * 7))

val Number.months: Period get() = Period.ofMonths((this.toInt()))

val Number.years: Period
    get() {
        return when (this) {
            is Double -> Period.ofDays((this * AVG_LENGTH_YEAR).toInt())
            is Float -> Period.ofDays((this * AVG_LENGTH_YEAR).toInt())
            else -> Period.ofYears((this.toInt()))
        }
    }