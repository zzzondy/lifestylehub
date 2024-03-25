package com.common.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.register(
    featureNavigationApi: FeatureNavigationApi,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    featureNavigationApi.registerFeatureNavigationGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier
    )
}