package com.eventapp.mealswithroom.ui.categories.view

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.eventapp.mealswithroom.ui.navigation.AppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealsCategoriesScreen(navController: NavController) {
    Scaffold(topBar = {
        AppBar(title = "Recipes", navController = navController)
    }) {
        Text(text = "Test")
    }
}