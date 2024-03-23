package com.place_details.presentation.place_details.components.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.common.ui.extensions.shimmerEffect
import com.common.ui.theme.LifestyleHubTheme

@Composable
fun PlaceDetailsScreenLoadingState(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        userScrollEnabled = false
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LifestyleHubTheme.sizes.placeBestPhotoHeight)
                    .shimmerEffect(true)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LifestyleHubTheme.sizes.placeDetailsPhotoHeight)
            ) {
                Box(
                    modifier = Modifier
                        .width(LifestyleHubTheme.sizes.placeDetailsPhotoWidth)
                        .height(LifestyleHubTheme.sizes.placeDetailsPhotoHeight)
                        .shimmerEffect(true)
                )

                Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.small))

                Box(
                    modifier = Modifier
                        .width(LifestyleHubTheme.sizes.placeDetailsPhotoWidth)
                        .height(LifestyleHubTheme.sizes.placeDetailsPhotoHeight)
                        .shimmerEffect(true)
                )
            }
            
            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
        }

        item {
            Text(
                text = "",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))

            Row(
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            ) {
                Box(
                    modifier = Modifier
                        .size(LifestyleHubTheme.sizes.large)
                        .shimmerEffect(true, MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.small))

                Text(
                    text = "",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .shimmerEffect(true, MaterialTheme.shapes.medium)
                )
            }

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }
    }
}

@Preview
@Composable
private fun PlaceDetailsScreenLoadingStatePreview() {
    LifestyleHubTheme {
        PlaceDetailsScreenLoadingState(modifier = Modifier.fillMaxSize())
    }
}