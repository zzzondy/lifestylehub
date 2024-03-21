package com.feature_main.presentation.screens.main.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.common.ui.extensions.shimmerEffect
import com.common.ui.extensions.toPx
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.R
import com.main.domain.models.places.PagingItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlaceWidget(
    place: PagingItem.Place,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = place.photo.replace(
                    "original",
                    "${LifestyleHubTheme.sizes.placeImageSize.toPx()}x${LifestyleHubTheme.sizes.placeImageSize.toPx()}"
                ),
                contentDescription = stringResource(R.string.place_image),
                modifier = Modifier
                    .size(LifestyleHubTheme.sizes.placeImageSize)
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.medium))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        top = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall,
                    )
                )

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = stringResource(R.string.your_location),
                        modifier = Modifier.size(LifestyleHubTheme.sizes.medium),
                    )

                    Text(
                        text = place.address.ifEmpty { stringResource(R.string.no_address) },
                        style = MaterialTheme.typography.labelLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        modifier = Modifier.padding(
                            start = LifestyleHubTheme.paddings.extraSmall,
                            end = LifestyleHubTheme.paddings.extraSmall
                        )
                    )
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

                Text(
                    text = stringResource(R.string.categories),
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall
                    )
                )

                FlowColumn(
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall
                    )
                ) {
                    place.categories.forEachIndexed { index, category ->
                        if (index <= 2) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                AsyncImage(
                                    model = category.icon,
                                    contentDescription = stringResource(R.string.categories),
                                    modifier = Modifier.size(LifestyleHubTheme.sizes.medium),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                )

                                Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.extraExtraSmall))

                                Text(
                                    text = category.name,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoadingPlaceWidget(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(LifestyleHubTheme.sizes.placeImageSize)
                    .align(Alignment.CenterVertically)
                    .shimmerEffect(true, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.medium))

            Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.medium))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(
                            start = LifestyleHubTheme.paddings.extraSmall,
                            top = LifestyleHubTheme.paddings.extraSmall,
                            end = LifestyleHubTheme.paddings.extraSmall,
                        )
                        .shimmerEffect(true, MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = stringResource(R.string.your_location),
                        modifier = Modifier.size(LifestyleHubTheme.sizes.medium),
                    )

                    Text(
                        text = "",
                        style = MaterialTheme.typography.labelLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(
                                start = LifestyleHubTheme.paddings.extraSmall,
                                end = LifestyleHubTheme.paddings.extraSmall,
                            )
                            .shimmerEffect(true, MaterialTheme.shapes.medium)
                    )
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

                Text(
                    text = stringResource(R.string.categories),
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall
                    )
                )

                FlowColumn(
                    modifier = Modifier.padding(
                        start = LifestyleHubTheme.paddings.extraSmall,
                        end = LifestyleHubTheme.paddings.extraSmall
                    )
                ) {
                    repeat(1) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(LifestyleHubTheme.sizes.medium)
                                    .shimmerEffect(true, MaterialTheme.shapes.medium)
                            )

                            Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.extraExtraSmall))

                            Text(
                                text = "",
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier
                                    .fillMaxWidth(0.33f)
                                    .shimmerEffect(true, MaterialTheme.shapes.medium)
                            )
                        }
                    }
                }
            }
        }
    }
}