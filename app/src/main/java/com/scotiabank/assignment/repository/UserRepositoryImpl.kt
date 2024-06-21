package com.scotiabank.assignment.repository

import com.scotiabank.assignment.domain.model.Repo
import com.scotiabank.assignment.domain.model.User
import com.scotiabank.assignment.network.UserService
import com.scotiabank.assignment.network.model.RepoDtoMapper
import com.scotiabank.assignment.network.model.UserDtoMapper

/**
 * An implementation of the UserRepository interface.
 * This class is responsible for providing the actual implementation of the network calls
 * to search for a user and retrieve their repositories using Retrofit.
 *
 * @property userService The UserService used to make the network calls.
 * @property userMapper The UserDtoMapper used to map UserDto to User domain model.
 * @property repoMapper The RepoDtoMapper used to map RepoDto to Repo domain model.
 */
class UserRepositoryImpl(
    private val userService: UserService,
    private val userMapper: UserDtoMapper,
    private val repoMapper: RepoDtoMapper
) : UserRepository {

    /**
     * Searches for a user based on the provided username.
     *
     * @param username The username of the GitHub user to search for.
     * @return The User object containing the user's details.
     */
    override suspend fun search(username: String): User {
        return userMapper.mapToDomainModel(userService.search(username))
    }

    /**
     * Retrieves the list of repositories for the specified user.
     *
     * @param username The username of the GitHub user whose repositories are to be retrieved.
     * @return A list of Repo objects containing the details of the user's repositories.
     */
    override suspend fun getRepos(username: String): List<Repo> {
        return repoMapper.toDomainList(userService.getRepos(username))
    }
}
