package com.eventapp.mealswithroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.eventapp.mealswithroom.ui.navigation.Navigation
import com.eventapp.mealswithroom.ui.theme.MealsWithRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealsWithRoomTheme {
                Navigation()
            }
        }
    }
}