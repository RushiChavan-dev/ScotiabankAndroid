package com.scotiabank.assignment.network.model

import com.google.gson.annotations.SerializedName

/**
 * A data transfer object (DTO) class for a GitHub user.
 * This class is used for network operations, specifically for parsing JSON responses from the GitHub API.
 *
 * @property avatarUrl The URL of the user's avatar image.
 * @property name The name of the user.
 * @property login The login username of the user.
 */
data class UserDto(
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("login")
    val login: String? = null
)
