package com.bharathksunil.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

import timber.log.Timber

import com.bharathksunil.utils.DateUtil.DateTimeUnits.MINUTES
import java.util.Objects.requireNonNull

/**
 * A Utility for maintaining uniformity of date time storage
 *
 * @author Bharath on 24-02-2018.
 */
object DateUtil {
    private var datePattern = "hh:mm:ss a, dd MMMM yyyy"
    private var timeZone = "IST"

    /**
     * Call this method to get the current time and date as a string
     *
     * @return the current date and time.
     */
    val currentDateTimeAsString: String
        get() {
            val calendar = Calendar.getInstance()
            calendar.timeZone = TimeZone.getTimeZone(timeZone)
            return getDateAsString(calendar.time)
        }

    /**
     * Initialise this in the Apps Application class's onCreate() and pass a valid date pattern you
     * would like to use across the app.
     * By default we will be using [.datePattern] which gives: 12:35:59 AM, 12 March 2018
     *
     * @param datePattern a valid java date pattern.
     */
    fun setDatePattern(datePattern: String) {
        requireNonNull(datePattern)
        DateUtil.datePattern = datePattern
    }

    /**
     * Initialise this in the Apps Application class's onCreate() and pass a valid timeZone.
     * If not set, by default IST will be used.
     *
     * @param timeZone the timeZone that must be used for the app
     */
    fun setTimeZone(timeZone: String) {
        requireNonNull(timeZone)
        DateUtil.timeZone = timeZone
    }

    /**
     * Call this method to get the date as the string in the pattern set in [.datePattern]
     *
     * @param date the date object
     * @return the formatted string of the date in the [.datePattern]
     */
    fun getDateAsString(date: Date): String {
        requireNonNull(date)
        return SimpleDateFormat(datePattern, Locale.getDefault()).format(date)
    }

    /**
     * Call this method to get the date as a [Date] object from the string date
     *
     * @param date the date in the format of the string
     * @return date object corresponding to the date sting
     * @throws ParseException exception that occurred while passing the string as it is not a date.
     */
    @Throws(ParseException::class)
    fun getDateFromString(date: String): Date {
        requireNonNull(date)
        return SimpleDateFormat(datePattern, Locale.getDefault()).parse(date)
    }

    /**
     * Get absolute difference between 2 times in the time unit you need.<br></br>
     * **How to use:** Pass in the two dates you want the difference with the unit of difference.
     * eg: number of seconds/minutes/days between timeOne and timeTwo.
     *
     * @param timeOne   the first Date
     * @param timeTwo   the second Date
     * @param timeUnits the [DateTimeUnits] in which the difference is required
     * @return Difference(timeOne - timeTwo) between the two dates in the unit which was requested
     */
    fun getAbsoluteTimeDifference(timeOne: Date,
                                  timeTwo: Date,
                                  timeUnits: DateTimeUnits): String {
        requireNonNull(timeOne)
        requireNonNull(timeTwo)
        requireNonNull(timeUnits)
        val diffInMs = Math.abs(timeOne.time - timeTwo.time)
        val days = TimeUnit.MILLISECONDS.toDays(diffInMs).toInt()
        val hours = (TimeUnit.MILLISECONDS.toHours(diffInMs) - TimeUnit.DAYS.toHours(days.toLong())).toInt()
        val minutes = (TimeUnit.MILLISECONDS.toMinutes(diffInMs) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diffInMs))).toInt()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMs).toInt()
        when (timeUnits) {
            DateUtil.DateTimeUnits.DAYS -> return java.lang.Long.toString(days.toLong())
            DateUtil.DateTimeUnits.SECONDS -> return java.lang.Long.toString(seconds.toLong())
            MINUTES -> return java.lang.Long.toString(minutes.toLong())
            DateUtil.DateTimeUnits.HOURS -> return java.lang.Long.toString(hours.toLong())
            DateUtil.DateTimeUnits.MILLISECONDS -> return java.lang.Long.toString(diffInMs)
            else -> return java.lang.Long.toString(diffInMs)
        }
    }

    /**
     * Get absolute difference between 2 times in the time unit you need.<br></br>
     * **How to use:** Pass in the two dates you want the difference with the unit of difference.
     * eg: number of seconds/minutes/days between timeOne and timeTwo.
     *
     * @param timeOne   the first Date
     * @param timeTwo   the second Date
     * @param timeUnits the [DateTimeUnits] in which the difference is required
     * @return Difference(timeOne - timeTwo) between the two dates in the unit which was requested
     */
    fun getAbsoluteTimeDifference(timeOne: String,
                                  timeTwo: String,
                                  timeUnits: DateTimeUnits): String {
        requireNonNull(timeOne)
        requireNonNull(timeTwo)
        requireNonNull(timeUnits)
        try {
            val firstDate = getDateFromString(timeOne)
            val secondDate = getDateFromString(timeTwo)
            return getAbsoluteTimeDifference(secondDate, firstDate, timeUnits)
        } catch (e: ParseException) {
            Timber.e(e)
        }

        return "NA"
    }

    /**
     * Get difference between 2 times in the time unit you need.<br></br>
     * **How to use:** Pass in the two dates you want the difference with the unit of difference.
     * eg: number of seconds/minutes/days between timeOne and timeTwo.
     *
     * @param timeOne   the first Date
     * @param timeTwo   the second Date
     * @param timeUnits the [DateTimeUnits] in which the difference is required
     * @return Difference(timeOne - timeTwo) between the two dates in the unit which was requested
     */
    fun getTimeDifference(timeOne: Date,
                          timeTwo: Date,
                          timeUnits: DateTimeUnits): String {
        requireNonNull(timeOne)
        requireNonNull(timeTwo)
        requireNonNull(timeUnits)
        val diffInMs = timeOne.time - timeTwo.time
        val days = TimeUnit.MILLISECONDS.toDays(diffInMs).toInt()
        val hours = (TimeUnit.MILLISECONDS.toHours(diffInMs) - TimeUnit.DAYS.toHours(days.toLong())).toInt()
        val minutes = (TimeUnit.MILLISECONDS.toMinutes(diffInMs) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diffInMs))).toInt()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMs).toInt()
        when (timeUnits) {
            DateUtil.DateTimeUnits.DAYS -> return java.lang.Long.toString(days.toLong())
            DateUtil.DateTimeUnits.SECONDS -> return java.lang.Long.toString(seconds.toLong())
            MINUTES -> return java.lang.Long.toString(minutes.toLong())
            DateUtil.DateTimeUnits.HOURS -> return java.lang.Long.toString(hours.toLong())
            DateUtil.DateTimeUnits.MILLISECONDS -> return java.lang.Long.toString(diffInMs)
            else -> return java.lang.Long.toString(diffInMs)
        }
    }

    /**
     * Get difference between 2 times in the time unit you need.<br></br>
     * **How to use:** Pass in the two dates you want the difference with the unit of difference.
     * eg: number of seconds/minutes/days between timeOne and timeTwo.
     *
     * @param timeOne   the first Date
     * @param timeTwo   the second Date
     * @param timeUnits the [DateTimeUnits] in which the difference is required
     * @return Difference(timeOne - timeTwo) between the two dates in the unit which was requested
     */
    fun getTimeDifference(timeOne: String,
                          timeTwo: String,
                          timeUnits: DateTimeUnits): String {
        requireNonNull(timeOne)
        requireNonNull(timeTwo)
        requireNonNull(timeUnits)
        try {
            val firstDate = getDateFromString(timeOne)
            val secondDate = getDateFromString(timeTwo)
            return getTimeDifference(secondDate, firstDate, timeUnits)
        } catch (e: ParseException) {
            Timber.e(e)
        }

        return "NA"
    }

    /**
     * Call this function to check if the time has past the currentTime
     *
     * @param time the time you want to compare with the present
     * @return true if the time has passed the currentTime
     */
    fun isTimePast(time: String): Boolean {
        requireNonNull(time)
        var diff: Long = 0
        try {
            diff = java.lang.Long.parseLong(getTimeDifference(
                    getDateFromString(currentDateTimeAsString),
                    getDateFromString(time),
                    MINUTES
            ))
        } catch (e: ParseException) {
            Timber.e(e)
        }

        return diff > 0
    }

    /**
     * Call this function to get the milliseconds that has passed since the epoch from the date string
     *
     * @param date the date string
     * @return the milliseconds since epoch
     */
    fun getTimestampFromDate(date: String): Long? {
        requireNonNull(date)
        val thisDate: Date
        try {
            thisDate = getDateFromString(date)
        } catch (e: ParseException) {
            Timber.e(e)
            return null
        }

        return thisDate.time
    }

    internal enum class DateTimeUnits {
        /**
         * Days
         */
        DAYS,
        /**
         * Hours
         */
        HOURS,
        /**
         * Minutes
         */
        MINUTES,
        /**
         * Seconds
         */
        SECONDS,
        /**
         * Milliseconds
         */
        MILLISECONDS
    }
}

