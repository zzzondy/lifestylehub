package com.lifestylehub.features.main.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lifestylehub.common.navigation.FeaturesNavigationGraphs

fun NavGraphBuilder.registerMainFeatureGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = MainFeatureScreens.MainScreen.route,
        route = FeaturesNavigationGraphs.MainScreenFeature.route,
    ) {
        composable(
            route = MainFeatureScreens.MainScreen.route,
        ) {
            Column {
                Text(text = "Hello")
            }
        }
    }
}