package com.lifestylehub.common.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.lifestylehub.R

enum class FeaturesNavigationGraphs(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector,
    @StringRes val iconContentDescription: Int? = null,
) {
    MainScreenFeature(
        route = "main_feature",
        title = R.string.main,
        icon = Icons.Rounded.Home,
        iconContentDescription = R.string.home
    )
}