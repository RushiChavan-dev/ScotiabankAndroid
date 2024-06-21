package com.scotiabank.assignment.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.scotiabank.assignment.presentation.components.RepoListContent
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment responsible for displaying user details and their repositories.
 */
@AndroidEntryPoint
class RepoListFragment : Fragment() {
    // ViewModel instance, scoped to this Fragment
    private val viewModel: RepoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Create a ComposeView to set the Compose content
        return ComposeView(requireContext()).apply {
            setContent {
                // Pass the ViewModel and NavController to the composable function
                RepoListContent(viewModel = viewModel, navController = findNavController())
            }
        }
    }
}
