package com.scotiabank.assignment.util

/**
 * Interface for providing string resources.
 * This interface abstracts the way string resources are retrieved, making it easier to use them in different contexts.
 */
interface StringProvider {
    /**
     * Retrieves a string resource based on its resource ID.
     *
     * @param resId The resource ID of the string.
     * @return The string associated with the specified resource ID.
     */
    fun getString(resId: Int): String
}
