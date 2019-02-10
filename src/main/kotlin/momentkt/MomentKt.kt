package momentkt

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*


object MomentKt {

    const val LANGUAGE_BUNDLE_NAME = "humanize"

    const val AVG_LENGTH_YEAR: Double = 365.2422
    const val AVG_LENGTH_MONTH: Double = 30.43685
    const val SECONDS_PER_DAY: Int = 86400
    const val SECONDS_PER_HOUR: Int = 3600

    fun humanizeDiff(secDiff: Long, generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
        return humanize(secDiff, "template.past", "template.future", generalizeSeconds, locale)
    }

    fun humanizeSpan(secDiff: Long, generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
        return humanize(secDiff, "template.span", "template.span", generalizeSeconds, locale)
    }

    fun humanize(secDiff: Long, baseTemplatePast: String = "baseTemplatePast", baseTemplateFuture: String = "baseTemplateFuture", generalizeSeconds: Boolean = true, locale: Locale = Locale.getDefault()): String {
        val absSecDiff = Math.abs(secDiff)
        val negativeDiff = secDiff < 0

        val avgYearLength = 365.2422
        val avgMonthLength = avgYearLength / 12.0

        val props = ResourceBundle.getBundle(LANGUAGE_BUNDLE_NAME, locale)

        if (absSecDiff <= 44) {
            return if (generalizeSeconds) {
                if (negativeDiff) {
                    props.getString("static.seconds.general.future")
                } else {
                    props.getString("static.seconds.general.past")
                }
            } else {
                if (negativeDiff) {
                    if (absSecDiff == 1L) {
                        props.getString("$baseTemplateFuture.singular").format(locale, absSecDiff, props.getString("unit.second.singular"))
                    } else {
                        props.getString("$baseTemplateFuture.plural").format(locale, absSecDiff, props.getString("unit.second.plural"))
                    }
                } else {
                    if (absSecDiff == 1L) {
                        props.getString("$baseTemplatePast.singular").format(locale, absSecDiff, props.getString("unit.second.singular"))
                    } else {
                        props.getString("$baseTemplatePast.plural").format(locale, absSecDiff, props.getString("unit.second.plural"))
                    }
                }
            }
        }

        if (absSecDiff <= 89) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.singular").format(locale, absSecDiff, props.getString("unit.minute.singular"))
            } else {
                props.getString("$baseTemplatePast.singular").format(locale, absSecDiff, props.getString("unit.minute.singular"))
            }
        }

        val absMinDiff = absSecDiff / 60

        if (absMinDiff <= 44) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.plural").format(locale, absMinDiff, props.getString("unit.minute.plural"))
            } else {
                props.getString("$baseTemplatePast.plural").format(locale, absMinDiff, props.getString("unit.minute.plural"))
            }
        }

        if (absMinDiff <= 89) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.singular").format(locale, absMinDiff, props.getString("unit.hour.singular"))
            } else {
                props.getString("$baseTemplatePast.singular").format(locale, absMinDiff, props.getString("unit.minute.singular"))
            }
        }

        val absHourDiff = absMinDiff / 60

        if (absHourDiff <= 21) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.plural").format(locale, absHourDiff, props.getString("unit.hour.plural"))
            } else {
                props.getString("$baseTemplatePast.plural").format(locale, absHourDiff, props.getString("unit.hour.plural"))
            }
        }

        if (absHourDiff <= 35) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.singular").format(locale, absHourDiff, props.getString("unit.day.singular"))
            } else {
                props.getString("$baseTemplatePast.singular").format(locale, absHourDiff, props.getString("unit.day.singular"))
            }
        }

        val absDayDiff = absHourDiff / 24


        if (absDayDiff <= 25) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.plural").format(locale, absDayDiff, props.getString("unit.day.plural"))
            } else {
                props.getString("$baseTemplatePast.plural").format(locale, absDayDiff, props.getString("unit.day.plural"))
            }
        }

        if (absDayDiff <= 45) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.singular").format(locale, absDayDiff, props.getString("unit.month.singular"))
            } else {
                props.getString("$baseTemplatePast.singular").format(locale, absDayDiff, props.getString("unit.month.singular"))
            }
        }

        val absMonthDiff = Math.round(absDayDiff / avgMonthLength)

        if (absDayDiff <= 319) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.plural").format(locale, absMonthDiff, props.getString("unit.month.plural"))
            } else {
                props.getString("$baseTemplatePast.plural").format(locale, absMonthDiff, props.getString("unit.month.plural"))
            }
        }

        if (absDayDiff <= 547) {
            return if (negativeDiff) {
                props.getString("$baseTemplateFuture.singular").format(locale, absDayDiff, props.getString("unit.year.singular"))
            } else {
                props.getString("$baseTemplatePast.singular").format(locale, absDayDiff, props.getString("unit.year.singular"))
            }
        }

        val absYearDiff = Math.round(absDayDiff / avgYearLength)

        return if (negativeDiff) {
            props.getString("$baseTemplateFuture.plural").format(locale, absYearDiff, props.getString("unit.year.plural"))
        } else {
            props.getString("$baseTemplatePast.plural").format(locale, absYearDiff, props.getString("unit.year.plural"))
        }
    }

    fun dayOfWeekIndex(dayOfWeek: DayOfWeek, weekStart: DayOfWeek): Int {
        val localeStartOrdinal = weekStart.ordinal
        val dayOfWeekOrdinal = dayOfWeek.ordinal
        return ((7 - localeStartOrdinal) + dayOfWeekOrdinal) % 7
    }

    fun dayOfWeekIndex(dayOfWeek: DayOfWeek, locale: Locale): Int {
        return dayOfWeekIndex(dayOfWeek, WeekFields.of(locale).firstDayOfWeek)
    }

    fun firstWeek(year: Int, minimalDaysInFirstWeek: Int, weekStart: DayOfWeek): ClosedRange<LocalDate> {
        val firstDateOfYear = LocalDate.of(year, 1, 1)
        val firstDayOfYear = firstDateOfYear.dayOfWeek
        val firstDayIndex = firstDayOfYear.index(weekStart)

        val firstWeekStart = if ((8 - firstDayIndex) < minimalDaysInFirstWeek) {
            LocalDate.of(year, 1, (8 - firstDayIndex))
        } else {
            if (firstDayOfYear == weekStart) {
                firstDateOfYear
            } else {
                val lastMonthLength = LocalDate.of(year - 1, 1, 1).lengthOfMonth()
                LocalDate.of(year - 1, 12, (lastMonthLength - firstDayIndex) + 1)
            }
        }

        return firstWeekStart..firstWeekStart.plusDays(6)
    }

    fun firstWeek(year: Int, locale: Locale = Locale.getDefault()): ClosedRange<LocalDate> {
        val weekFields = WeekFields.of(locale)
        return firstWeek(year, weekFields.minimalDaysInFirstWeek, weekFields.firstDayOfWeek)
    }

    fun getWeek(year: Int, weekNumber: Int, locale: Locale = Locale.getDefault()): ClosedRange<LocalDate> {
        if (weekNumber < 0 || weekNumber >= 53) {
            throw IllegalArgumentException("WeekNumber must be in range of [0-52]")
        }
        val firstWeekDay = firstWeek(year, locale).start

        // sanity check
        assert(firstWeekDay.dayOfWeek == WeekFields.of(locale).firstDayOfWeek) {
            "${firstWeekDay.dayOfWeek} != ${WeekFields.of(locale).firstDayOfWeek}"
        }

        val weekStart = firstWeekDay.plusDays(((weekNumber) * 7).toLong())
        return weekStart..weekStart.plusDays(6)
    }
}

