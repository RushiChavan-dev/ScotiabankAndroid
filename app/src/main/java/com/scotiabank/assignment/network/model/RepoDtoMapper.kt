package com.scotiabank.assignment.network.model

import com.scotiabank.assignment.domain.helper.DomainMapper
import com.scotiabank.assignment.domain.model.Repo

/**
 * A data transfer object (DTO) mapper class responsible for converting between
 * RepoDto objects and Repo domain model objects.
 */
class RepoDtoMapper : DomainMapper<RepoDto, Repo> {

    /**
     * Maps a RepoDto object to a Repo domain model object.
     *
     * @param model The RepoDto object to be converted.
     * @return The converted Repo domain model object.
     */
    override fun mapToDomainModel(model: RepoDto): Repo {
        return Repo(
            name = model.name,
            description = model.description,
            updatedAt = model.updatedAt,
            curRepoForks = model.curRepoForks,
            stargazersCount = model.stargazersCount,
            forks = model.forks
        )
    }

    /**
     * Maps a Repo domain model object to a RepoDto object.
     *
     * @param domainModel The Repo domain model object to be converted.
     * @return The converted RepoDto object.
     */
    override fun mapFromDomainModel(domainModel: Repo): RepoDto {
        return RepoDto(
            name = domainModel.name,
            description = domainModel.description,
            updatedAt = domainModel.updatedAt,
            curRepoForks = domainModel.curRepoForks,
            stargazersCount = domainModel.stargazersCount,
            forks = domainModel.forks
        )
    }

    /**
     * Converts a list of RepoDto objects to a list of Repo domain model objects.
     *
     * @param initial The list of RepoDto objects to be converted.
     * @return The list of converted Repo domain model objects.
     */
    fun toDomainList(initial: List<RepoDto>): List<Repo> {
        return initial.map { mapToDomainModel(it) }
    }
}
