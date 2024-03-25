package com.planner.presentation.screens.plans_screen.components.empty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.common.ui.theme.LifestyleHubTheme
import com.planner.presentation.R
import com.planner.presentation.screens.plans_screen.state_hoisting.PlansScreenAction

@Composable
fun PlansScreenEmptyState(
    onAction: (PlansScreenAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = LifestyleHubTheme.paddings.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.plans_are_empty),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

        Button(
            onClick = { onAction(PlansScreenAction.OnAddPlanClicked) }
        ) {
            Text(text = stringResource(R.string.add))
        }
    }
}