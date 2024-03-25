package com.place_details.presentation.plan_adding

import android.app.DatePickerDialog
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.common.ui.theme.LifestyleHubTheme
import com.place_details.presentation.R
import com.place_details.presentation.plan_adding.state_hoisting.PlanAddingScreenAction

@Composable
fun PlanAddingScreenContent(
    name: String,
    placeName: String,
    isNameError: Boolean,
    year: Int,
    month: Int,
    day: Int,
    onAction: (PlanAddingScreenAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, newYear, newMonth, newDay ->
            onAction(PlanAddingScreenAction.OnUpdateDate(newYear, newMonth, newDay))
        },
        year, month - 1, day,
    )

    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            Text(
                text = stringResource(R.string.you_planned_to_visit),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = placeName,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium),
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
        }

        item {
            OutlinedTextField(
                value = name,
                onValueChange = { onAction(PlanAddingScreenAction.OnUpdateName(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium),
                label = {
                    Text(text = stringResource(R.string.name))
                },
                isError = isNameError,
                trailingIcon = {
                    if (name.isNotBlank()) {
                        IconButton(
                            onClick = { onAction(PlanAddingScreenAction.OnUpdateName("")) }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = stringResource(
                                    R.string.clear
                                )
                            )
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
        }

        item {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium),
                onClick = {
                    datePickerDialog.show()
                }
            ) {
                Row(
                    modifier = Modifier
                        .height(LifestyleHubTheme.sizes.extraLarge)
                        .fillMaxWidth()
                        .padding(horizontal = LifestyleHubTheme.paddings.extraSmall),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = stringResource(R.string.planner),
                        modifier = Modifier.size(LifestyleHubTheme.sizes.medium),
                    )

                    Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.extraSmall))

                    Text(
                        text = "$day $month $year",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
        }

        item {
            Button(
                onClick = { onAction(PlanAddingScreenAction.OnAddButtonClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
            ) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}