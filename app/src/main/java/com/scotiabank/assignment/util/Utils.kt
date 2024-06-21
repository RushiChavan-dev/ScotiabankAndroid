package com.scotiabank.assignment.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Parses a date string in ISO 8601 format to a Date object.
 *
 * @param dateStr The date string in ISO 8601 format.
 * @return The parsed Date object, or null if parsing fails.
 */
fun parseISODate(dateStr: String?): Date? {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC") // Ensure the parsing is done in UTC
    return try {
        format.parse(dateStr)
    } catch (e: Exception) {
        null
    }
}

/**
 * Formats a Date object to a human-readable string.
 *
 * @param date The Date object to format.
 * @return A formatted date string, or "Unknown date" if the date is null.
 */
fun formatDate(date: Date?): String {
    return if (date != null) {
        val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        formatter.format(date)
    } else {
        "Unknown date"
    }
}

/**
 * Object containing utility functions for validating input strings.
 */
object ValidationUtils {
    /**
     * Validates an input string based on predefined rules.
     * The input must match a specific pattern and have a length of up to 39 characters.
     *
     * @param input The input string to validate.
     * @return True if the input is valid, false otherwise.
     */
    fun isValidInput(input: String): Boolean {
        val pattern = Regex("^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\$")
        return pattern.matches(input) && !input.startsWith("-") && input.length <= 39
    }
}
