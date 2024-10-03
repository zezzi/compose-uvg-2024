package com.eventapp.mealswithroom.ui.categories.repositories

import com.eventapp.mealswithroom.networking.MealsWebService
import com.eventapp.mealswithroom.networking.response.categories.Categories

class MealsCategoryRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMealsCategories(): List<Categories> {
        return webService.getMealsCategories().categories
    }
}