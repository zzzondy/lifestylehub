package com.planner.presentation.navigation

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
import com.place_details.presentation.place_details.PlaceDetailsScreen
import com.place_details.presentation.place_details.PlaceDetailsScreenViewModel
import com.planner.presentation.di.PlannerFeatureComponentProvider
import com.planner.presentation.screens.adding_plan.AddingPlanScreen
import com.planner.presentation.screens.plans_screen.PlansScreen

class PlannerFeatureNavigationApi : FeatureNavigationApi {

    override val navigationRoute: String = PlannerFeatureScreens.navigationRoute

    override val startDestinationRoute: String = PlannerFeatureScreens.startScreenDestination

    override fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = PlannerFeatureScreens.startScreenDestination,
            route = navigationRoute
        ) {
            val context = navController.context.applicationContext
            val plannerFeatureComponent =
                (context as PlannerFeatureComponentProvider).providePlannerFeatureComponent()

            composable(
                route = PlannerFeatureScreens.PlansListScreen.route,
                exitTransition = {
                    fadeOut(animationSpec = tween(AnimationConstants.EXIT_ANIMATION))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(AnimationConstants.ENTER_ANIMATION))
                }
            ) {
                val plansScreenComponent =
                    plannerFeatureComponent.plansScreenComponentFactory.create()

                val viewModel = daggerViewModel {
                    plansScreenComponent.plansScreenViewModel
                }

                PlansScreen(navController = navController, viewModel = viewModel)
            }

            composable(
                route = PlannerFeatureScreens.AddingPlanScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(AnimationConstants.ENTER_ANIMATION)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(AnimationConstants.EXIT_ANIMATION)
                    )
                }
            ) {
                val addingPlanScreenComponent =
                    plannerFeatureComponent.addingPlanScreenComponentFactory.create()
                val viewModel = daggerViewModel {
                    addingPlanScreenComponent.addingPlanScreenViewModel
                }

                AddingPlanScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(
                route = PlannerFeatureScreens.PlaceDetailsScreen.route,
                arguments = listOf(
                    navArgument(PlannerFeatureScreens.PLACE_DETAILS_SCREEN_ID) {
                        type = NavType.StringType
                    }
                ),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(AnimationConstants.ENTER_ANIMATION)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(AnimationConstants.EXIT_ANIMATION)
                    )
                }
            ) {
                val placeDetailsFeatureComponent =
                    plannerFeatureComponent.placeDetailsFeatureComponentFactory.create()

                val id =
                    it.arguments?.getString(PlannerFeatureScreens.PLACE_DETAILS_SCREEN_ID)
                        .toString()

                val viewModel = viewModel<PlaceDetailsScreenViewModel>(
                    factory = placeDetailsFeatureComponent.placeDetailsScreenViewModelFactory.create(id)
                )

                PlaceDetailsScreen(
                    navController = navController,
                    viewModel = viewModel,
                    shouldToShowAddPlanButton = false,
                )
            }
        }
    }
}