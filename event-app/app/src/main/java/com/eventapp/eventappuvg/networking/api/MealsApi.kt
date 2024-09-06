package com.eventapp.eventappuvg.networking.api

import com.eventapp.eventappuvg.networking.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MealsApi {

    @GET("categories.php")
    fun getMeals(): Call<MealsCategoriesResponse>
}