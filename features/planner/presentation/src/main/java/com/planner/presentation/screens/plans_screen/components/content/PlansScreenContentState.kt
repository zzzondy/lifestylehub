package com.planner.presentation.screens.plans_screen.components.content

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.common.ui.theme.LifestyleHubTheme
import com.planner.domain.models.Plan
import com.planner.presentation.screens.plans_screen.state_hoisting.PlansScreenAction

@Composable
fun PlansScreenContentState(
    plans: List<Plan>,
    modifier: Modifier = Modifier,
    onAction: (PlansScreenAction) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(plans) { plan ->
            PlanItem(
                plan = plan,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium),
                onDelete = { onAction(PlansScreenAction.OnDeletePlan(plan.id)) },
                onClick = {
                    if (plan.placeId != null) onAction(
                        PlansScreenAction.OnPlanWithPlaceClicked(
                            plan.placeId.toString()
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
        }
    }
}