package com.planner.presentation.screens.plans_screen.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.common.ui.extensions.toPx
import com.common.ui.theme.LifestyleHubTheme
import com.planner.domain.models.Plan
import com.planner.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanItem(
    plan: Plan,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onDelete: () -> Unit = {},
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx()
    val dismissState = rememberSwipeToDismissBoxState(
        positionalThreshold = {
            (screenWidth / 2.5).toFloat()
        }
    )

    LaunchedEffect(key1 = dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
            dismissState.snapTo(SwipeToDismissBoxValue.EndToStart)
            dismissState.snapTo(SwipeToDismissBoxValue.Settled)
            onDelete()
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            DismissBackground(swipeToDismissBoxValue = dismissState.dismissDirection)
        },
        modifier = modifier
            .clip(MaterialTheme.shapes.medium),
        enableDismissFromStartToEnd = false,
    ) {
        Card(
            modifier = Modifier
                .clickable(enabled = plan.placeId != null) { onClick() }
        ) {
            Text(
                text = plan.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    start = LifestyleHubTheme.paddings.extraSmall,
                    end = LifestyleHubTheme.paddings.extraSmall,
                    top = LifestyleHubTheme.paddings.extraSmall
                )
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.extraSmall),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Rounded.DateRange,
                    contentDescription = stringResource(R.string.planner),
                    modifier = Modifier.size(LifestyleHubTheme.sizes.medium)
                )

                Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.extraSmall))

                Text(
                    text = plan.date,
                    style = MaterialTheme.typography.titleSmall,
                )
            }

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

            if (plan.notes != null) {
                Text(
                    text = stringResource(R.string.notes),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall,
                    )
                )

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

                Text(
                    text = plan.notes.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall,
                    )
                )
            } else if (plan.placeId != null && plan.placeName != null) {
                Text(
                    text = stringResource(R.string.you_planned_to_visit),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall,
                    )
                )

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

                Text(
                    text = plan.placeName.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall,
                        bottom = LifestyleHubTheme.paddings.medium
                    )
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DismissBackground(swipeToDismissBoxValue: SwipeToDismissBoxValue) {
    val color = when (swipeToDismissBoxValue) {
        SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error

        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
    ) {
        if (swipeToDismissBoxValue == SwipeToDismissBoxValue.EndToStart) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = stringResource(R.string.delete),
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = LifestyleHubTheme.paddings.small)
            )
        }
    }
}

