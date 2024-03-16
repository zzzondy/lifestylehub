package com.feature_main.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.common.navigation.FeatureNavigationApi
import com.common.navigation.daggerViewModel
import com.feature_main.presentation.di.MainFeatureComponentProvider
import com.feature_main.presentation.screens.main.MainScreen
import com.feature_main.presentation.screens.main.MainScreenViewModel

class MainFeatureNavigationApi : FeatureNavigationApi {

    override val navigationRoute: String = MainFeatureScreens.navigationRoute

    override fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = MainFeatureScreens.startScreenRoute,
            route = navigationRoute
        ) {
            val context = navController.context.applicationContext
            val mainFeatureComponent =
                (context as MainFeatureComponentProvider).provideMainFeatureComponent()

            composable(route = MainFeatureScreens.MainScreen.route) {
                val mainScreenComponent = mainFeatureComponent.mainScreenComponentFactory.create()
                val viewModel: MainScreenViewModel = daggerViewModel {
                    mainScreenComponent.mainScreenViewModel
                }

                MainScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}