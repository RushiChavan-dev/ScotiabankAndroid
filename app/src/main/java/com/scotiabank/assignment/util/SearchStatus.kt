package com.scotiabank.assignment.util

/**
 * Enum class representing the status of a search operation.
 * This enum is used to determine the state of the search query input.
 */
enum class SearchStatus {
    /**
     * Represents the first search operation.
     */
    FIRST_SEARCH,

    /**
     * Represents a search operation with the same input as the previous one.
     */
    OLD_INPUT,

    /**
     * Represents a search operation with a new input different from the previous one.
     */
    NEW_INPUT
}
