package com.lifestylehub

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.lifestylehub.common.navigation.AppNavGraph
import com.lifestylehub.common.navigation.FeaturesNavigationGraphs

@Composable
fun AppContent() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                currentDestination = navController.currentDestination,
                items = FeaturesNavigationGraphs.entries
            )
        }
    ) { paddingValues ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    currentDestination: NavDestination?,
    items: List<FeaturesNavigationGraphs>,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        items.forEach { featureNavigationGraph ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == featureNavigationGraph.route } == true,
                onClick = {
                    navController.navigate(featureNavigationGraph.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = featureNavigationGraph.icon,
                        contentDescription = if (featureNavigationGraph.iconContentDescription != null) {
                            stringResource(featureNavigationGraph.iconContentDescription)
                        } else null
                    )
                },
                label = {
                    Text(text = stringResource(featureNavigationGraph.title))
                }
            )
        }
    }
}