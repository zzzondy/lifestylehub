package com.planner.presentation.screens.plans_screen.components.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.common.ui.extensions.shimmerEffect
import com.common.ui.theme.LifestyleHubTheme

@Composable
fun LoadingPlanItem(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = "",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = LifestyleHubTheme.paddings.extraSmall,
                    end = LifestyleHubTheme.paddings.extraSmall,
                    top = LifestyleHubTheme.paddings.extraSmall
                )
                .shimmerEffect(true, MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LifestyleHubTheme.paddings.extraSmall),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(LifestyleHubTheme.sizes.medium)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.extraSmall))

            Text(
                text = "",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )
        }

        Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

        Text(
            text = "",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(
                    start = LifestyleHubTheme.paddings.extraSmall,
                    end = LifestyleHubTheme.paddings.extraSmall,
                )
                .shimmerEffect(true, MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

        Text(
            text = "",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .fillMaxWidth(0.78f)
                .padding(
                    start = LifestyleHubTheme.paddings.extraSmall,
                    end = LifestyleHubTheme.paddings.extraSmall,
                    bottom = LifestyleHubTheme.paddings.medium
                )
                .shimmerEffect(true, MaterialTheme.shapes.medium)
        )
    }
}