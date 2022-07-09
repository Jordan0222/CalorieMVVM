package com.jordan.caloriemvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jordan.caloriemvvm.domain.preferences.Preferences
import com.jordan.caloriemvvm.presentation.onboarding.activity.ActivityScreen
import com.jordan.caloriemvvm.presentation.onboarding.age.AgeScreen
import com.jordan.caloriemvvm.presentation.onboarding.gender.GenderScreen
import com.jordan.caloriemvvm.presentation.onboarding.goal.GoalScreen
import com.jordan.caloriemvvm.presentation.onboarding.height.HeightScreen
import com.jordan.caloriemvvm.presentation.onboarding.nutrient_goal.NutrientGoalScreen
import com.jordan.caloriemvvm.presentation.onboarding.weight.WeightScreen
import com.jordan.caloriemvvm.presentation.onboarding.welcome.WelcomeScreen
import com.jordan.caloriemvvm.presentation.tracker.tracker_overview.TrackerOverviewScreen
import com.jordan.caloriemvvm.ui.theme.CalorieMVVMTheme
import com.jordan.caloriemvvm.util.navigation.Route
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shouldShowOnBoarding = preferences.loadShouldShowOnboarding()

        setContent {
            CalorieMVVMTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                onNextClick = {
                                    navController.navigate(Route.GENDER)
                                }
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(
                                onNextClick = {
                                    navController.navigate(Route.AGE)
                                }
                            )
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.HEIGHT)
                                }
                            )
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.WEIGHT)
                                }
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ACTIVITY)
                                }
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(
                                onNextClick = {
                                    navController.navigate(Route.GOAL)
                                }
                            )
                        }
                        composable(Route.GOAL) {
                            GoalScreen(
                                onNextClick = {
                                    navController.navigate(Route.NUTRIENT_GOAL)
                                }
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.TRACKER_OVERVIEW)
                                }
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(
                                onNavigateToSearch = { mealName, dayOfMonth, month, year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" +
                                                "/$dayOfMonth" +
                                                "/$month" +
                                                "/$year"
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}