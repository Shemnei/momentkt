package momentkt

import java.time.LocalDate
import java.time.LocalDateTime

object ago
object fromNow

infix fun Number.nanos(fromNow: fromNow): LocalDateTime = LocalDateTime.now() + this.nanos
infix fun Number.nanos(ago: ago): LocalDateTime = LocalDateTime.now() - this.nanos

infix fun Number.millis(fromNow: fromNow): LocalDateTime = LocalDateTime.now() + this.millis
infix fun Number.millis(ago: ago): LocalDateTime = LocalDateTime.now() - this.millis

infix fun Number.seconds(fromNow: fromNow): LocalDateTime = LocalDateTime.now() + this.seconds
infix fun Number.seconds(ago: ago): LocalDateTime = LocalDateTime.now() - this.seconds

infix fun Number.minutes(fromNow: fromNow): LocalDateTime = LocalDateTime.now() + this.minutes
infix fun Number.minutes(ago: ago): LocalDateTime = LocalDateTime.now() - this.minutes

infix fun Number.hours(fromNow: fromNow): LocalDateTime = LocalDateTime.now() + this.hours
infix fun Number.hours(ago: ago): LocalDateTime = LocalDateTime.now() - this.hours


infix fun Number.days(fromNow: fromNow): LocalDate = LocalDate.now() + this.days
infix fun Number.days(ago: ago): LocalDate = LocalDate.now() - this.days

infix fun Number.weeks(fromNow: fromNow): LocalDate = LocalDate.now() + this.weeks
infix fun Number.weeks(ago: ago): LocalDate = LocalDate.now() - this.weeks

infix fun Number.months(fromNow: fromNow): LocalDate = LocalDate.now() + this.months
infix fun Number.months(ago: ago): LocalDate = LocalDate.now() - this.months

infix fun Number.years(fromNow: fromNow): LocalDate = LocalDate.now() + this.years
infix fun Number.years(ago: ago): LocalDate = LocalDate.now() - this.years