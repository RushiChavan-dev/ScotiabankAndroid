package com.scotiabank.assignment.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParseException
import com.scotiabank.assignment.R
import com.scotiabank.assignment.domain.model.Repo
import com.scotiabank.assignment.domain.model.User
import com.scotiabank.assignment.repository.UserRepository
import com.scotiabank.assignment.util.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * The ViewModel that's attached to RepoListFragment.
 * This ViewModel is responsible for managing the UI-related data for the RepoListFragment.
 */
@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val repository: UserRepository,
    private val stringProvider: StringProvider
) : ViewModel() {

    // StateFlow to hold the current user
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    // StateFlow to hold the list of repositories
    private val _repos = MutableStateFlow<List<Repo>>(emptyList())
    val repos: StateFlow<List<Repo>> = _repos.asStateFlow()

    // StateFlow to hold any error messages
    private val _error = MutableStateFlow<String>("")
    val error: StateFlow<String> = _error.asStateFlow()

    // StateFlow to hold the current query string
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    /**
     * Function to search for user and their repositories based on the current query.
     * This function is launched in the viewModelScope to handle coroutines.
     */
    fun searchUser() {
        viewModelScope.launch {
            _error.value = "" // Clear any existing errors
            try {
                // Call repository methods to search for user and their repositories
                val userResult = repository.search(_query.value)
                val reposResult = repository.getRepos(_query.value)

                // Calculate total forks for the user by summing up forks of all repositories
                userResult.forksTotal = reposResult.sumOf { it.forks }

                // Update the UI state with the retrieved data
                _user.value = userResult
                _repos.value = reposResult
            } catch (e: HttpException) {
                // Handle specific HTTP exceptions (e.g., 404)
                _error.value = when (e.code()) {
                    404 -> stringProvider.getString(R.string.error_user_not_found)
                    401 -> stringProvider.getString(R.string.error_bad_credentials)
                    else -> stringProvider.getString(R.string.error_network)
                }
            } catch (e: UnknownHostException) {
                // Handle unknown host exception
                _user.value = null
                _repos.value = emptyList()
                _error.value = stringProvider.getString(R.string.error_unknown_host)
            } catch (e: IOException) {

                Log.d("TAG", "searchUser: $e")
                // Handle IOException for network-related errors
                _error.value = stringProvider.getString(R.string.error_network)
            } catch (e: JsonParseException) {
                // Handle JSON parsing errors
                _error.value = stringProvider.getString(R.string.error_invalid_response)
            } catch (e: Exception) {
                // Handle other generic exceptions
                _error.value = stringProvider.getString(R.string.error_unknown)
            }
        }
    }

    /**
     * Function to handle changes in the search query.
     * Updates the query MutableStateFlow with the new value.
     */
    fun onQueryChanged(query: String) {
        _query.value = query
    }
}
