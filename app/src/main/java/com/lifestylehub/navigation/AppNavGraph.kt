package com.lifestylehub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.common.navigation.FeatureNavigationApi
import com.common.navigation.register

@Composable
fun AppNavGraph(
    navController: NavHostController,
    featureNavigationApis: List<FeatureNavigationApi>,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = featureNavigationApis[0].navigationRoute,
        modifier = modifier,
    ) {
        featureNavigationApis.forEach { featuresNavigationApi ->
            register(
                featureNavigationApi = featuresNavigationApi,
                navController = navController,
                modifier = modifier
            )
        }
    }
}