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
    val extraLarge: Dp
)

object LifestyleHubTheme {

    val paddings: LifestyleHubPaddings
        @Composable
        get() = LocalLifecycleHubPaddings.current
}

val LocalLifecycleHubPaddings =
    staticCompositionLocalOf<LifestyleHubPaddings> { error("No paddings provided") }