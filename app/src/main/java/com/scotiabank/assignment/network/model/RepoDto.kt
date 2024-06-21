package com.scotiabank.assignment.network.model

import com.google.gson.annotations.SerializedName

/**
 * A data transfer object (DTO) class for a GitHub repository.
 * This class is used for network operations, specifically for parsing JSON responses from the GitHub API.
 *
 * @property name The name of the repository.
 * @property description A brief description of the repository.
 * @property updatedAt The last update timestamp of the repository.
 * @property stargazersCount The number of stars the repository has received.
 * @property curRepoForks The current number of forks of the repository.
 * @property forks The total number of forks of the repository.
 */
data class RepoDto(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("stargazers_count")
    val stargazersCount: Int = 0,

    @SerializedName("forks_count")
    val curRepoForks: Int = 0,

    @SerializedName("forks")
    val forks: Int = 0
)
