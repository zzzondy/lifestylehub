package com.lifestylehub

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.common.navigation.BottomBarItem
import com.common.navigation.FeatureNavigationApi
import com.lifestylehub.navigation.AppNavGraph

@Composable
fun AppContent(
    bottomBarItems: List<BottomBarItem>,
    featureNavigationApis: List<FeatureNavigationApi>
) {
    val navController = rememberNavController()
    val currentDestinationRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    val currentDestinationParentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.parent?.route

    val shouldShowBottomBar =
        featureNavigationApis.any { it.startDestinationRoute == currentDestinationRoute } || currentDestinationParentRoute == null

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = shouldShowBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomBar(
                    navController = navController,
                    currentDestinationParentRoute = currentDestinationParentRoute,
                    items = bottomBarItems
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        AppNavGraph(
            navController = navController,
            featureNavigationApis = featureNavigationApis,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                )
                .systemBarsPadding()
        )
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    currentDestinationParentRoute: String?,
    items: List<BottomBarItem>,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
    ) {
        items.forEach { bottomBarItem ->
            NavigationBarItem(
                selected = currentDestinationParentRoute == bottomBarItem.navigationRoute,
                onClick = {
                    navController.navigate(bottomBarItem.navigationRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(bottomBarItem.iconId),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = stringResource(bottomBarItem.nameId))
                }
            )
        }
    }
}