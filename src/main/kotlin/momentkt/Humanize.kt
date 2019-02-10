package momentkt

import momentkt.MomentKt.AVG_LENGTH_MONTH
import momentkt.MomentKt.SECONDS_PER_DAY
import momentkt.MomentKt.humanizeDiff
import momentkt.MomentKt.humanizeSpan
import java.time.*
import java.util.*

fun LocalDateTime.humanize(generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault(), zoneOffset: ZoneOffset = OffsetDateTime.now().offset): String {
    return humanizeDiff(LocalDateTime.now().toEpochSecond(zoneOffset) - this.toEpochSecond(zoneOffset), generalizeSeconds, locale)
}

fun LocalDate.humanize(generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault(), zoneOffset: ZoneOffset = OffsetDateTime.now().offset): String {
    return humanizeDiff(LocalDate.now().toEpochSecond(LocalTime.MIDNIGHT, zoneOffset) - this.toEpochSecond(LocalTime.MIDNIGHT, zoneOffset), generalizeSeconds, locale)
}

fun Instant.humanize(generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
    return humanizeDiff(Instant.now().epochSecond - this.epochSecond, generalizeSeconds, locale)
}


fun ZonedDateTime.humanize(generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
    return humanizeDiff(ZonedDateTime.now().toEpochSecond() - this.toEpochSecond(), generalizeSeconds, locale)
}

fun OffsetTime.humanize(generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
    return humanizeDiff(OffsetTime.now().toEpochSecond(LocalDate.now()) - this.toEpochSecond(LocalDate.now()), generalizeSeconds, locale)
}

fun OffsetDateTime.humanize(generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
    return humanizeDiff(OffsetDateTime.now().toEpochSecond() - this.toEpochSecond(), generalizeSeconds, locale)
}


fun Duration.humanize(generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
    return humanizeSpan(this.seconds, generalizeSeconds, locale)
}

fun Period.humanize(generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
    val days = ((this.days + this.toTotalMonths() * AVG_LENGTH_MONTH) * SECONDS_PER_DAY).toLong()
    return humanizeSpan(days, generalizeSeconds, locale)
}