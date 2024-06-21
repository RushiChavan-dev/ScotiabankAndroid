package com.scotiabank.assignment.util

import android.content.Context
import javax.inject.Inject

/**
 * Implementation of the StringProvider interface.
 * This class provides string resources from the Android context.
 *
 * @property context The context used to access string resources.
 */
class ResourceStringProvider @Inject constructor(
    private val context: Context
) : StringProvider {

    /**
     * Retrieves a string resource based on its resource ID.
     *
     * @param resId The resource ID of the string.
     * @return The string associated with the specified resource ID.
     */
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}
