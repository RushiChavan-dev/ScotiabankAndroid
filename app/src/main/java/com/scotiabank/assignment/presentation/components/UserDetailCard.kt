package com.scotiabank.assignment.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.scotiabank.assignment.domain.model.User

/**
 * A Composable view for displaying a GitHub user's avatar and name.
 *
 * @param user The User object containing the user's details to be displayed.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserDetailCard(
    user: User
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        // Display the user's avatar if available
        user.avatarUrl?.let {
            GlideImage(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                model = user.avatarUrl,
                contentDescription = null
            )
        }

        // Display the user's name if available
        user.name?.let {
            Text(
                modifier = Modifier.padding(top = 5.dp),
                style = MaterialTheme.typography.titleSmall,
                text = it
            )
        }
    }
}
