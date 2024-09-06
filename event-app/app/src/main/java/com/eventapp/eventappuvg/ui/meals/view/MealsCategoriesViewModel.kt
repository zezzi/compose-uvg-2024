package com.eventapp.eventappuvg.ui.meals.view

import androidx.lifecycle.ViewModel
import com.eventapp.eventappuvg.networking.response.MealsCategoriesResponse
import com.eventapp.eventappuvg.ui.meals.repository.MealsRepository

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {

    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}