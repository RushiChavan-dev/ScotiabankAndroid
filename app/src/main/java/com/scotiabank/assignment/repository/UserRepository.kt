package com.scotiabank.assignment.repository

import com.scotiabank.assignment.domain.model.Repo
import com.scotiabank.assignment.domain.model.User

/**
 * An interface defining the retrofit network functions for user-related data.
 * This interface is responsible for declaring the methods to search for a user
 * and to retrieve the list of repositories for a given user.
 */
interface UserRepository {
    /**
     * Searches for a user based on the provided username.
     *
     * @param username The username of the GitHub user to search for.
     * @return The User object containing the user's details.
     */
    suspend fun search(username: String): User

    /**
     * Retrieves the list of repositories for the specified user.
     *
     * @param username The username of the GitHub user whose repositories are to be retrieved.
     * @return A list of Repo objects containing the details of the user's repositories.
     */
    suspend fun getRepos(username: String): List<Repo>
}
