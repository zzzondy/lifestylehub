package com.lifestylehub.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lifestylehub.features.main.navigation.registerMainFeatureGraph

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = FeaturesNavigationGraphs.MainScreenFeature.route,
        modifier = modifier
    ) {
        registerMainFeatureGraph(navController)
    }
}