package com.auth.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.auth.presentation.di.AuthFeatureComponentProvider
import com.auth.presentation.screens.login.LoginScreen
import com.auth.presentation.screens.profile.ProfileScreen
import com.auth.presentation.screens.sign_up.SignUpScreen
import com.common.navigation.FeatureNavigationApi
import com.common.navigation.daggerViewModel
import com.common.ui.utils.AnimationConstants

class AuthFeatureNavigationApi : FeatureNavigationApi {

    override val navigationRoute: String = AuthFeatureScreens.navigationRoute

    override val startDestinationRoute: String = AuthFeatureScreens.startScreenRoute

    override fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            route = navigationRoute,
            startDestination = startDestinationRoute,
        ) {
            val context = navController.context.applicationContext
            val authFeatureComponent =
                (context as AuthFeatureComponentProvider).provideAuthFeatureComponent()

            composable(
                route = AuthFeatureScreens.ProfileScreen.route,
                exitTransition = {
                    fadeOut(animationSpec = tween(AnimationConstants.EXIT_ANIMATION))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(AnimationConstants.ENTER_ANIMATION))
                }
            ) {
                val profileScreenComponent =
                    authFeatureComponent.profileScreenComponentFactory.create()

                val viewModel = daggerViewModel {
                    profileScreenComponent.profileScreenViewModel
                }

                ProfileScreen(navController = navController, viewModel = viewModel)
            }

            composable(
                route = AuthFeatureScreens.SignUpScreen.route,
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
                val signUpScreenComponent =
                    authFeatureComponent.signUpScreenComponentFactory.create()

                val viewModel = daggerViewModel {
                    signUpScreenComponent.signUpScreenViewModel
                }

                SignUpScreen(navController = navController, viewModel = viewModel)
            }

            composable(
                route = AuthFeatureScreens.LoginScreen.route,
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
                val loginScreenComponent = authFeatureComponent.loginScreenComponentFactory.create()

                val viewModel = daggerViewModel {
                    loginScreenComponent.loginScreenViewModel
                }

                LoginScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}