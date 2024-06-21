package com.scotiabank.assignment.network.model

import com.scotiabank.assignment.domain.helper.DomainMapper
import com.scotiabank.assignment.domain.model.User

/**
 * A data transfer object (DTO) mapper class responsible for converting between
 * UserDto objects and User domain model objects.
 */
class UserDtoMapper : DomainMapper<UserDto, User> {

    /**
     * Maps a UserDto object to a User domain model object.
     *
     * @param model The UserDto object to be converted.
     * @return The converted User domain model object.
     */
    override fun mapToDomainModel(model: UserDto): User {
        return User(
            avatarUrl = model.avatarUrl,
            name = model.name
        )
    }

    /**
     * Maps a User domain model object to a UserDto object.
     *
     * @param domainModel The User domain model object to be converted.
     * @return The converted UserDto object.
     */
    override fun mapFromDomainModel(domainModel: User): UserDto {
        return UserDto(
            avatarUrl = domainModel.avatarUrl,
            name = domainModel.name
        )
    }
}
