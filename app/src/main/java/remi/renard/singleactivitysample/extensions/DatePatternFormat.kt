package remi.renard.singleactivitysample.extensions

/**
 * The `DatePatternFormat` class is an enumeration class that provides pattern to format [String] or [Date] to [String].
 */
enum class DatePatternFormat(val pattern: String) {

    /**
     * Format with the pattern `yyyy-MM-dd'T'HH:mm:ssZ`.
     */
    RFC_822("yyyy-MM-dd'T'HH:mm:ssZ"),

    /**
     * Format with the pattern `yyyy-MM-dd'T'HH:mm:ss.SSS'Z'`.
     */
    ISO_8601("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),

    /**
     * Format with the pattern `yyyy-MM-dd'T'HH:mm:ss'Z'`.
     */
    ISO_8601_SHORT("yyyy-MM-dd'T'HH:mm:ss'Z'"),

    /**
     * Format with the pattern `yyyy-MM-dd HH:mm:ss`.
     */
    DATE_TIME_FORMAT("yyyy-MM-dd HH:mm:ss"),

    /**
     * Format with the pattern `EEEE d MMMM`.
     */
    FULL_DATE_FORMAT("EEEE d MMMM"),

    /**
     * Format with the pattern `E d MMMM`.
     */
    LONG_DATE_FORMAT("E d MMMM"),

    /**
     * Format with the pattern `dd MMM yyyy`.
     */
    LONG_DATE_FORMAT_D_M_Y("dd MMM yyyy"),

    /**
     * Format with the pattern `dd MMM`.
     */
    LONG_DATE_FORMAT_D_M("dd MMM"),

    /**
     * Format with the pattern `dd-MM-yyyy`.
     */
    SHORT_DATE_FORMAT_D_M_Y("dd-MM-yyyy"),

    /**
     * Format with the pattern `MM-yyyy`.
     */
    SHORT_DATE_FORMAT_M_Y("MM-yyyy"),

    /**
     * Format with the pattern `dd-MM`.
     */
    SHORT_DATE_FORMAT_D_M("dd-MM"),

    /**
     * Format with the pattern `yyyy-MM-dd`.
     */
    SHORT_DATE_FORMAT_Y_M_D("yyyy-MM-dd"),

    /**
     * Format with the pattern `yyyy-MM`.
     */
    SHORT_DATE_FORMAT_Y_M("yyyy-MM"),

    /**
     * Format with the pattern `MM-dd`.
     */
    SHORT_DATE_FORMAT_M_D("MM-dd"),

    /**
     * Format with the pattern `HH:mm:ss.SSS`.
     */
    FULL_TIME_FORMAT("HH:mm:ss.SSS"),

    /**
     * Format with the pattern `HH:mm:ss`.
     */
    LONG_TIME_FORMAT("HH:mm:ss"),

    /**
     * Format with the pattern `HH:mm`.
     */
    TIME_FORMAT("HH:mm")
}
