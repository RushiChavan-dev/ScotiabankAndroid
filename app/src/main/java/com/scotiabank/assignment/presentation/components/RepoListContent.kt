package com.scotiabank.assignment.presentation.components

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.scotiabank.assignment.R
import com.scotiabank.assignment.presentation.ui.RepoListViewModel
import com.scotiabank.assignment.util.SearchStatus
import kotlinx.coroutines.delay

/**
 * Composable function that displays the content of the RepoListFragment.
 *
 * @param viewModel The ViewModel associated with this composable.
 * @param navController The NavController for navigation actions.
 */
@Composable
fun RepoListContent(viewModel: RepoListViewModel, navController: NavController) {
    // Collecting StateFlow values from the ViewModel
    val user by viewModel.user.collectAsState()
    val repos by viewModel.repos.collectAsState()
    val error by viewModel.error.collectAsState()
    val query by viewModel.query.collectAsState()

    // Mutable states for handling visibility and search status
    var isNewSearchResult by remember { mutableStateOf(SearchStatus.NEW_INPUT) }
    var userDetailVisible by remember { mutableStateOf(false) }
    var repoCardsVisible by remember { mutableStateOf(false) }

    // Main UI layout
    Column(modifier = Modifier.fillMaxSize()) {
        // Text input card for entering the search query
        TextInputCard(
            inputValue = query,
            onValueChange = {
                viewModel.onQueryChanged(it)
            },
            onSearchClick = { isNewSearch ->
                // Update the search result status and reset visibility if new search
                isNewSearchResult = isNewSearch
                if (isNewSearch == SearchStatus.NEW_INPUT) {
                    userDetailVisible = false
                    repoCardsVisible = false
                }
                viewModel.searchUser()
            }
        )

        // Conditionally display the repositories or the error message
        if (error.isNotEmpty()) {
            if (error == stringResource(id = R.string.error_unknown_host) ||error == stringResource(id = R.string.error_network) || error == stringResource(id = R.string.error_bad_credentials) || error == stringResource(id = R.string.error_unknown) ){
                isNewSearchResult = SearchStatus.NEW_INPUT
                userDetailVisible = true
                repoCardsVisible = true
            }
            Text(text = error, color = MaterialTheme.colorScheme.error)
        } else {
            // Animated visibility for UserDetailCard
            AnimatedVisibility(
                visible = userDetailVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight / 10 }, // Slide in from the bottom
                    animationSpec = tween(durationMillis = 1000) // 1 second duration
                ) + fadeIn(
                    initialAlpha = 0.5f,
                    animationSpec = tween(durationMillis = 1000) // 1 second duration
                )
            ) {
                user?.let {
                    UserDetailCard(it)
                }
            }

            // Animated visibility for LazyColumn containing RepoCards
            AnimatedVisibility(
                visible = repoCardsVisible,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight / 10 }, // Slide in from the bottom
                    animationSpec = tween(durationMillis = 1000) // 1 second duration
                ) + fadeIn(
                    initialAlpha = 0.5f,
                    animationSpec = tween(durationMillis = 1000) // 1 second duration
                )
            ) {
                // Display the list of repositories in a lazy column
                LazyColumn {
                    itemsIndexed(items = repos) { _, repo ->
                        RepoCard(repo = repo, onClick = {
                            // Create a bundle to pass data to the navigation destination
                            val bundle = Bundle().apply {
                                putParcelable("repo", repo)
                                user?.let { putInt("forksTotal", it.forksTotal) }
                            }
                            // Navigate to the repo detail view
                            navController.navigate(R.id.view_repo, bundle)
                        })
                    }
                }
            }
        }
    }

    // Observe the search result and update visibility states accordingly
    LaunchedEffect(user, repos, query) {
        when (isNewSearchResult) {
            SearchStatus.OLD_INPUT -> {
                // Handle visibility state for old input
            }
            SearchStatus.FIRST_SEARCH -> {
                userDetailVisible = user != null
                repoCardsVisible = repos.isNotEmpty()
            }
            SearchStatus.NEW_INPUT -> {
                delay(400)
                userDetailVisible = user != null
                repoCardsVisible = repos.isNotEmpty()
                isNewSearchResult = SearchStatus.OLD_INPUT
            }
        }
    }
}
