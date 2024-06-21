package com.scotiabank.assignment.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.scotiabank.assignment.R
import com.scotiabank.assignment.util.SearchStatus
import com.scotiabank.assignment.util.ValidationUtils.isValidInput

/**
 * A Composable view for a search text box with input validation and search status handling.
 *
 * @param inputValue The current value of the text input.
 * @param onValueChange Callback to handle changes to the text input value.
 * @param onSearchClick Callback to handle the search button click, providing the search status.
 */
@Composable
fun TextInputCard(
    inputValue: String,
    onValueChange: (String) -> Unit,
    onSearchClick: (SearchStatus) -> Unit
) {
    var showWarning by remember { mutableStateOf(false) }
    var previousSearches by remember { mutableStateOf(String()) }

    /**
     * Determines the search status based on the input.
     *
     * @param input The current input string.
     * @return The corresponding SearchStatus.
     */
    fun getSearchStatus(input: String): SearchStatus {
        return when {
            previousSearches.isEmpty() -> {
                previousSearches = input
                SearchStatus.FIRST_SEARCH
            }
            previousSearches == input -> SearchStatus.OLD_INPUT
            else -> {
                previousSearches = input
                SearchStatus.NEW_INPUT
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .testTag("TextInputCard"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField for input
        TextField(
            value = inputValue,
            onValueChange = { newValue ->
                if (newValue.length <= 39) {
                    onValueChange(newValue)
                    showWarning = !isValidInput(newValue)
                }
            },
            label = { Text(text = stringResource(id = R.string.text_input_card_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .testTag("TextInputCard_TextField"),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Gray
            ),
            singleLine = true
        )

        // Warning text for invalid input
        if (showWarning && inputValue.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.invalid_input_warning),
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .testTag("TextInputCard_WarningText")
            )
        }

        // Search button
        Button(
            onClick = {
                val searchStatus = getSearchStatus(inputValue)
                onSearchClick(searchStatus)
            },
            enabled = isValidInput(inputValue),
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .testTag("TextInputCard_Button")
        ) {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
