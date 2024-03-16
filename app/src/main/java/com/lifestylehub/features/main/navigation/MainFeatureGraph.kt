package com.lifestylehub.features.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lifestylehub.common.navigation.FeaturesNavigationGraphs
import com.lifestylehub.features.main.screens.main.presentation.MainScreen

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
            MainScreen(navController = navController)
        }
    }
}