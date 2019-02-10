package momentkt

import momentkt.MomentKt.dayOfWeekIndex
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.Year
import java.util.*


fun DayOfWeek.index(locale: Locale = Locale.getDefault()): Int {
    return dayOfWeekIndex(this, locale)
}

fun DayOfWeek.index(weekStart: DayOfWeek): Int {
    return dayOfWeekIndex(this, weekStart)
}

fun Year.firstWeek(locale: Locale = Locale.getDefault()): ClosedRange<LocalDate> {
    return MomentKt.firstWeek(this.value, locale)
}
// TODO: 05.10.2018 for localdatetime datetime date offset ...
// TODO: 05.10.2018 more sanity checks

fun LocalDate.getWeekNumber(locale: Locale = Locale.getDefault()): Int {
    val firstWeekDay = MomentKt.firstWeek(this.year, locale).start
    val weekNumber = (Math.abs(Duration.between(this.atStartOfDay(), firstWeekDay.atStartOfDay()).toDays()) / 7).toInt()

    assert(weekNumber in (0..52)) { "$weekNumber !in (0 .. 52)" }

    return weekNumber
}

fun LocalDate.getWeek(locale: Locale = Locale.getDefault()): Pair<Int, ClosedRange<LocalDate>> {
    val weekNo = this.getWeekNumber(locale)
    val week = MomentKt.getWeek(this.year, weekNo, locale)
    return weekNo to week
}

val LocalDate.week: Pair<Int, ClosedRange<LocalDate>>
    get() = this.getWeek()

val LocalDate.weekNumber: Int
    get() = this.getWeekNumber()

