package com.feature_main.presentation.screens.place_details.components.content

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.common.ui.extensions.toPx
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.R
import com.feature_main.presentation.screens.place_details.state_hoisting.PlaceDetailsScreenAction
import com.main.domain.models.places.PlaceDetails

@Composable
fun PlaceDetailsScreenContentState(
    placeDetails: PlaceDetails,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            AsyncImage(
                model = placeDetails.bestPhoto.replace(
                    "original",
                    "${LifestyleHubTheme.sizes.placeBestPhotoWidth.toPx()}x${LifestyleHubTheme.sizes.placeBestPhotoHeight.toPx()}"
                ),
                contentDescription = stringResource(R.string.place_image),
                modifier = Modifier
                    .height(
                        LifestyleHubTheme.sizes.placeBestPhotoHeight
                    )
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))
        }

        if (placeDetails.placePhotos.isNotEmpty()) {
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(LifestyleHubTheme.sizes.placeDetailsPhotoHeight)
                ) {
                    itemsIndexed(placeDetails.placePhotos) { index, photo ->
                        if (index != 0) {
                            AsyncImage(
                                model = photo.replace(
                                    "original",
                                    "${LifestyleHubTheme.sizes.placeDetailsPhotoWidth.toPx()}x${LifestyleHubTheme.sizes.placeDetailsPhotoHeight.toPx()}"
                                ),
                                contentDescription = stringResource(R.string.place_image),
                                modifier = Modifier
                                    .height(LifestyleHubTheme.sizes.placeDetailsPhotoHeight)
                                    .width(LifestyleHubTheme.sizes.placeDetailsPhotoWidth)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
            }
        }

        item {
            Text(
                text = placeDetails.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
        }

        item {
            Row(
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Place,
                    contentDescription = stringResource(R.string.place_image),
                    modifier = Modifier.size(LifestyleHubTheme.sizes.large)
                )

                Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.small))

                Text(
                    text = placeDetails.address.ifEmpty { stringResource(R.string.no_address) },
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }

        item {
            Text(
                text = stringResource(R.string.contacts),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

            if (placeDetails.phone == null && placeDetails.facebook == null && placeDetails.twitter == null && placeDetails.instagram == null) {
                Text(
                    text = stringResource(R.string.no_contacts),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
                )
            }

            if (placeDetails.phone != null) {
                Row(
                    modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Phone,
                        contentDescription = stringResource(R.string.phone),
                        modifier = Modifier.size(LifestyleHubTheme.sizes.large)
                    )

                    Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.small))

                    Text(
                        text = placeDetails.phone.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))
            }

            if (placeDetails.instagram != null) {
                Row(
                    modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.instagram),
                        contentDescription = stringResource(R.string.instagram),
                        modifier = Modifier.size(LifestyleHubTheme.sizes.large)
                    )

                    Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.small))

                    Text(
                        text = placeDetails.instagram.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))
            }

            if (placeDetails.twitter != null) {
                Row(
                    modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.twitter),
                        contentDescription = stringResource(R.string.twitter),
                        modifier = Modifier.size(LifestyleHubTheme.sizes.large)
                    )

                    Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.small))

                    Text(
                        text = placeDetails.twitter.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))
            }

            if (placeDetails.facebook != null) {
                Row(
                    modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.facebook),
                        contentDescription = stringResource(R.string.facebook),
                        modifier = Modifier.size(LifestyleHubTheme.sizes.large)
                    )

                    Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.small))

                    Text(
                        text = placeDetails.facebook.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))
            }

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }

        item {
            Text(
                text = stringResource(R.string.categories),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

            placeDetails.categories.forEach { category ->
                Row(
                    modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
                ) {
                    AsyncImage(
                        model = category.icon,
                        contentDescription = stringResource(R.string.categories),
                        modifier = Modifier.size(LifestyleHubTheme.sizes.large),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )

                    Spacer(modifier = Modifier.width(LifestyleHubTheme.paddings.small))

                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}