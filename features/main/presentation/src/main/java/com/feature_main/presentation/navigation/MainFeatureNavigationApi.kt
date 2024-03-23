package com.feature_main.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.common.navigation.FeatureNavigationApi
import com.common.navigation.daggerViewModel
import com.common.ui.utils.AnimationConstants
import com.feature_main.presentation.di.MainFeatureComponentProvider
import com.feature_main.presentation.screens.main.MainScreen
import com.feature_main.presentation.screens.main.MainScreenViewModel
import com.place_details.presentation.place_details.PlaceDetailsScreen
import com.place_details.presentation.place_details.PlaceDetailsScreenViewModel
import com.place_details.presentation.plan_adding.PlanAddingScreen
import com.place_details.presentation.plan_adding.PlanAddingScreenViewModel

class MainFeatureNavigationApi : FeatureNavigationApi {

    override val navigationRoute: String = MainFeatureScreens.navigationRoute

    override val startDestinationRoute: String = MainFeatureScreens.startScreenRoute

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
            val placeDetailsScreenComponent =
                mainFeatureComponent.placeDetailsFeatureComponentFactory.create()

            composable(
                route = MainFeatureScreens.MainScreen.route,
                exitTransition = {
                    fadeOut(animationSpec = tween(AnimationConstants.EXIT_ANIMATION))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(AnimationConstants.ENTER_ANIMATION))
                }
            ) {
                val mainScreenComponent = mainFeatureComponent.mainScreenComponentFactory.create()
                val viewModel: MainScreenViewModel = daggerViewModel {
                    mainScreenComponent.mainScreenViewModel
                }

                MainScreen(navController = navController, viewModel = viewModel)
            }

            composable(
                route = MainFeatureScreens.PlaceDetailsScreen.route,
                arguments = listOf(
                    navArgument(MainFeatureScreens.PLACE_DETAILS_SCREEN_ID) {
                        type = NavType.StringType
                    },
                ),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(AnimationConstants.ENTER_ANIMATION)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(AnimationConstants.EXIT_ANIMATION)
                    )
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(AnimationConstants.EXIT_ANIMATION))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(AnimationConstants.ENTER_ANIMATION))
                }
            ) {
                val id = it.arguments?.getString(MainFeatureScreens.PLACE_DETAILS_SCREEN_ID)
                    .toString()

                val viewModel = viewModel<PlaceDetailsScreenViewModel>(
                    factory = placeDetailsScreenComponent.placeDetailsScreenViewModelFactory.create(
                        id
                    ),
                )

                PlaceDetailsScreen(
                    navController = navController,
                    viewModel = viewModel,
                    shouldToShowAddPlanButton = true,
                    onAddPlanButtonClicked = MainFeatureScreens.PlanAddingScreen::passArguments
                )
            }

            composable(
                route = MainFeatureScreens.PlanAddingScreen.route,
                arguments = listOf(
                    navArgument(MainFeatureScreens.PLACE_DETAILS_SCREEN_ID) {
                        type = NavType.StringType
                    },
                    navArgument(MainFeatureScreens.PLAN_ADDING_SCREEN_PLACE_NAME) {
                        type = NavType.StringType
                    },
                ),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(AnimationConstants.ENTER_ANIMATION)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(AnimationConstants.EXIT_ANIMATION)
                    )
                }
            ) {
                val planAddingScreenComponent =
                    placeDetailsScreenComponent.planAddingScreenComponentFactory.create()

                val id = it.arguments?.getString(MainFeatureScreens.PLACE_DETAILS_SCREEN_ID)
                    .toString()

                val placeName =
                    it.arguments?.getString(MainFeatureScreens.PLAN_ADDING_SCREEN_PLACE_NAME)
                        .toString()

                val viewModel = viewModel<PlanAddingScreenViewModel>(
                    factory = planAddingScreenComponent.planAddingScreenViewModelFactory.create(
                        id,
                        placeName
                    )
                )

                PlanAddingScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}