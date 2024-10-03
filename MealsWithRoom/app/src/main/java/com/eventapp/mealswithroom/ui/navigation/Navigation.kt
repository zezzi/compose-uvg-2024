package com.eventapp.mealswithroom.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eventapp.mealswithroom.ui.categories.view.MealsCategoriesScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.MealsCategories.route,
        modifier = modifier) {

        composable(route = NavigationState.MealsCategories.route) {
            MealsCategoriesScreen(navController = navController)
        }
        composable(NavigationState.MealsRecipesList.route) {
            // Screen of Details
        }
        composable(route = NavigationState.Home.route) {
            // Screen of Home
        }
        composable(route = NavigationState.Profile.route) {
            //Screen of Profile
        }
    }
}