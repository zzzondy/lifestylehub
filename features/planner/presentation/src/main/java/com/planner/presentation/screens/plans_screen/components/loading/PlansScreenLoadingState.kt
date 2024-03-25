package com.planner.presentation.screens.plans_screen.components.loading

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.common.ui.theme.LifestyleHubTheme

@Composable
fun PlansScreenLoadingState(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        userScrollEnabled = false,
    ) {
        items(PLACEHOLDER_ITEMS_COUNT) {
            LoadingPlanItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
        }
    }
}

private const val PLACEHOLDER_ITEMS_COUNT = 4