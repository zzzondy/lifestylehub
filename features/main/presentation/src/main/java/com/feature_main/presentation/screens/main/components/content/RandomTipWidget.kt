package com.feature_main.presentation.screens.main.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.common.ui.extensions.shimmerEffect
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.R
import com.main.domain.models.tips.RandomTip

@Composable
fun RandomTipWidget(
    randomTip: RandomTip,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = stringResource(R.string.tip_for_you),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.extraSmall)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = randomTip.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.extraSmall)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }
    }
}

@Composable
fun LoadingRandomTipWidget(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = "",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .padding(horizontal = LifestyleHubTheme.paddings.extraSmall)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.extraSmall)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }
    }
}

@Composable
fun ErrorTipWidget(
    message: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(
                    LifestyleHubTheme.paddings.medium
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}