package com.scotiabank.assignment.network

import com.scotiabank.assignment.network.model.RepoDto
import com.scotiabank.assignment.network.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * An interface for implementing Retrofit network calls to the GitHub API.
 */
interface UserService {

    /**
     * Retrieves a GitHub user's details based on the username.
     *
     * @param username The username of the GitHub user.
     * @return A UserDto object containing the user's details.
     */
    @GET("{username}")
    suspend fun search(
        @Path("username") username: String
    ): UserDto

    /**
     * Retrieves a list of repositories for a given GitHub user.
     *
     * @param username The username of the GitHub user.
     * @return A list of RepoDto objects containing the repository details.
     */
    @GET("{username}/repos")
    suspend fun getRepos(
        @Path("username") username: String
    ): List<RepoDto>
}
