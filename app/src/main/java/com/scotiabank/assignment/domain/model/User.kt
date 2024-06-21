package com.scotiabank.assignment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * A Parcelable data class representing a GitHub user.
 * This class allows the user data to be passed between Android components.
 *
 * @property avatarUrl The URL of the user's avatar image.
 * @property name The name of the user.
 * @property forksTotal The total number of forks associated with the user's repositories.
 */
@Parcelize
data class User(
    val avatarUrl: String? = null,
    val name: String? = null,
    var forksTotal: Int = 0
) : Parcelable
