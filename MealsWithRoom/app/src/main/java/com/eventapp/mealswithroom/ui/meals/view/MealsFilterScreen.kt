package com.eventapp.mealswithroom.ui.meals.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.eventapp.mealswithroom.R
import com.eventapp.mealswithroom.networking.response.meals.Meal
import com.eventapp.mealswithroom.ui.categories.view.MealsCategoriesScreen
import com.eventapp.mealswithroom.ui.meals.viewmodel.MealsViewModel
import com.eventapp.mealswithroom.navigation.AppBar
import com.eventapp.mealswithroom.ui.theme.MealsWithRoomTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealsFilterScreen(navController: NavController, category: String) {
    Log.d("ARGUMENTS", category)

    val viewModel: MealsViewModel = viewModel()
    val mealFilter by viewModel.meals.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchByCategory(category)
    }

    Scaffold(topBar = {
        AppBar(title = "Categories", navController = navController)
    }) {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            mealFilter?.let {
                items(it) { meal ->
                    MealCategory(meal)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealsWithRoomTheme {
        MealsCategoriesScreen(navController = rememberNavController())
    }
}