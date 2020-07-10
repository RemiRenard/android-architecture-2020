package remi.renard.singleactivitysample.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.getCalendarMonth(): Int {
    val calendar = Calendar.getInstance().also { it.time = this }
    return calendar.get(Calendar.MONTH)
}

fun Date.getCalendarYear(): Int {
    val calendar = Calendar.getInstance().also { it.time = this }
    return calendar.get(Calendar.YEAR)
}

private const val DATE_SEPARATOR = '-'
private const val TIME_SEPARATOR = ':'

@Suppress("LongParameterList")
fun String.formatWithPattern(
    stringPatternFormat: DatePatternFormat,
    datePatternFormat: DatePatternFormat,
    stringDateSeparator: Char = DATE_SEPARATOR,
    stringTimeSeparator: Char = TIME_SEPARATOR,
    dateDateSeparator: Char = DATE_SEPARATOR,
    dateTimeSeparator: Char = TIME_SEPARATOR
): String {
    return formatWithPattern(
        stringPatternFormat.pattern,
        datePatternFormat.pattern,
        stringDateSeparator,
        stringTimeSeparator,
        dateDateSeparator,
        dateTimeSeparator
    )
}

@Suppress("LongParameterList")
fun String.formatWithPattern(
    stringPattern: String,
    datePattern: String,
    stringDateSeparator: Char = DATE_SEPARATOR,
    stringTimeSeparator: Char = TIME_SEPARATOR,
    dateDateSeparator: Char = DATE_SEPARATOR,
    dateTimeSeparator: Char = TIME_SEPARATOR
): String {
    val date = parseWithPattern(stringPattern, stringDateSeparator, stringTimeSeparator)
    return date.formatWithPattern(datePattern, dateDateSeparator, dateTimeSeparator)
}

fun String.parseWithPattern(
    patternFormat: DatePatternFormat,
    dateSeparator: Char = DATE_SEPARATOR,
    timeSeparator: Char = TIME_SEPARATOR
): Date {
    return parseWithPattern(patternFormat.pattern, dateSeparator, timeSeparator)
}

fun String.parseWithPattern(
    pattern: String,
    dateSeparator: Char = DATE_SEPARATOR,
    timeSeparator: Char = TIME_SEPARATOR
): Date {
    val patternClean = replaceCharacters(pattern, dateSeparator, timeSeparator)
    val format = getSimpleDateFormat(patternClean)
    return format.parse(this)
}

fun Date.formatWithPattern(
    patternFormat: DatePatternFormat,
    dateSeparator: Char = DATE_SEPARATOR,
    timeSeparator: Char = TIME_SEPARATOR
): String {
    return formatWithPattern(patternFormat.pattern, dateSeparator, timeSeparator)
}

fun Date.formatWithPattern(
    pattern: String,
    dateSeparator: Char = DATE_SEPARATOR,
    timeSeparator: Char = TIME_SEPARATOR
): String {
    val patternClean = replaceCharacters(pattern, dateSeparator, timeSeparator)
    val format = getSimpleDateFormat(patternClean)
    return format.format(this)
}

private fun replaceCharacters(pattern: String, dateSeparator: Char, timeSeparator: Char): String {
    var replacePattern = pattern

    if (dateSeparator != DATE_SEPARATOR) {
        replacePattern = pattern.replace(DATE_SEPARATOR, dateSeparator)
    }

    if (timeSeparator != TIME_SEPARATOR) {
        replacePattern = pattern.replace(TIME_SEPARATOR, timeSeparator)
    }

    return replacePattern
}

private fun getSimpleDateFormat(pattern: String): SimpleDateFormat {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    format.timeZone = TimeZone.getDefault()
    return format
}
