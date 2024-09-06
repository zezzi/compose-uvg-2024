package com.eventapp.eventappuvg.ui.meals.viewmodel

import androidx.lifecycle.ViewModel
import com.eventapp.eventappuvg.networking.responses.MealsCategoryResponse
import com.eventapp.eventappuvg.ui.meals.repository.MealsRepository

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {

    fun getMeals(successCallback: (response: MealsCategoryResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}