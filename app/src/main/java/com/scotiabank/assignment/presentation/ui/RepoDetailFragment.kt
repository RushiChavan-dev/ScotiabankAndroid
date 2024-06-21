package com.scotiabank.assignment.presentation.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.scotiabank.assignment.domain.model.Repo
import com.scotiabank.assignment.presentation.components.RepoDetailView
import dagger.hilt.android.AndroidEntryPoint

/**
 * The fragment responsible for displaying the Composable view
 * of the selected repository details.
 */
@AndroidEntryPoint
class RepoDetailFragment : Fragment() {
    private var repo: Repo? = null
    private var forksTotal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            // Retrieve the total forks from the bundle
            bundle.getInt("forksTotal").let {
                forksTotal = it
            }
            // Retrieve the repository object from the bundle
            repo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("repo", Repo::class.java)
            } else {
                bundle.getParcelable("repo")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Create a ComposeView and set the content to the RepoDetailView composable
        return ComposeView(requireContext()).apply {
            setContent {
                RepoDetailView(repo = repo, forksTotal = forksTotal)
            }
        }
    }
}
