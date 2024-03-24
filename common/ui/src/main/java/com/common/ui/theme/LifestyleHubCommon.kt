package com.common.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

data class LifestyleHubPaddings(
    val default: Dp,
    val extraExtraSmall: Dp,
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
    val bottomBarPaddingValue: Dp
)

data class LifestyleHubSizes(
    val weatherIconSize: Dp,
    val weatherCardHeight: Dp,
    val placeImageSize: Dp,
    val placeCardHeight: Dp,
    val placeBestPhotoHeight: Dp,
    val placeDetailsPhotoHeight: Dp,
    val placeDetailsPhotoWidth: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
    val extraExtraLarge: Dp,
    val small: Dp,
    val extraSmall: Dp,
)

object LifestyleHubTheme {

    val paddings: LifestyleHubPaddings
        @Composable
        get() = LocalLifecycleHubPaddings.current

    val sizes: LifestyleHubSizes
        @Composable
        get() = LocalLifecycleHubSizes.current
}

val LocalLifecycleHubPaddings =
    staticCompositionLocalOf<LifestyleHubPaddings> { error("No paddings provided") }

val LocalLifecycleHubSizes =
    staticCompositionLocalOf<LifestyleHubSizes> { error("No sizes provided") }