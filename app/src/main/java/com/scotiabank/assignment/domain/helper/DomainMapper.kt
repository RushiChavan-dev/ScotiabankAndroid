package com.scotiabank.assignment.domain.helper

/**
 * An interface responsible for defining the contract for mapping functions
 * that convert between two types: a generic type T and a domain model type DomainModel.
 *
 * @param T The type of the source object.
 * @param DomainModel The type of the target domain model object.
 */
interface DomainMapper<T, DomainModel> {

    /**
     * Converts an object of type T to an object of type DomainModel.
     *
     * @param model The source object to be converted to a domain model.
     * @return The converted domain model object.
     */
    fun mapToDomainModel(model: T): DomainModel

    /**
     * Converts an object of type DomainModel to an object of type T.
     *
     * @param domainModel The domain model object to be converted back to the source type.
     * @return The converted object of type T.
     */
    fun mapFromDomainModel(domainModel: DomainModel): T
}
