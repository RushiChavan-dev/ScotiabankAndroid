package com.scotiabank.assignment.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scotiabank.assignment.domain.model.Repo
import com.scotiabank.assignment.util.formatDate
import com.scotiabank.assignment.util.parseISODate

/**
 * A Composable view for displaying detailed information of a selected repository (repo).
 *
 * @param repo The Repo object containing the details to be displayed.
 * @param forksTotal The total number of forks across all repositories of the user.
 */
@Composable
fun RepoDetailView(repo: Repo?, forksTotal: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        repo?.let {
            // Display the name of the repository
            Text(
                text = "Repo name: ${repo.name}",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display the description of the repository if available
            repo.description?.let {
                Text(
                    text = "Description: ${repo.description}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            // Display the number of stargazers
            Text(
                text = "Number of star gazers: ${repo.stargazersCount}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                color = MaterialTheme.colorScheme.secondary
            )

            // Display the last updated date after formatting it
            val formattedDate = formatDate(parseISODate(repo.updatedAt))
            Text(
                text = "Last updated: $formattedDate",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Display the fork count for the current repository
            Text(
                text = "Fork count for ${repo.name}: ${repo.curRepoForks}",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the total fork count across all repositories
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total forks across all Repo: $forksTotal",
                color = if (forksTotal >= 5000) Color.Red else Color.Black,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
            )
            // Display a star icon if the total fork count is high
            if (forksTotal >= 5000) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "High forks",
                    tint = Color(0xFFB4A43E),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}
