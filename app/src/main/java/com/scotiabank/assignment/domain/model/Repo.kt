package com.scotiabank.assignment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * A Parcelable data class representing a GitHub repository.
 * This class allows the repository data to be passed between Android components.
 *
 * @property name The name of the repository.
 * @property description A brief description of the repository.
 * @property updatedAt The last update timestamp of the repository.
 * @property curRepoForks The number of forks of the repository at the current state.
 * @property stargazersCount The number of stars the repository has received.
 * @property forks The total number of forks of the repository.
 */
@Parcelize
data class Repo(
    val name: String? = null,
    val description: String? = null,
    val updatedAt: String? = null,
    val curRepoForks: Int = 0,
    val stargazersCount: Int = 0,
    val forks: Int = 0
) : Parcelable
