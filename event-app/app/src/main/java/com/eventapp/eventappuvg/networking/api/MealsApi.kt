package com.eventapp.eventappuvg.networking.api

import com.eventapp.eventappuvg.networking.responses.MealsCategoryResponse
import retrofit2.http.GET
import retrofit2.Call

interface MealsApi {

    @GET("categories.php")
    fun getMeals(): Call<MealsCategoryResponse>

    @GET("random.php")
    fun getRandom(): Call<MealsCategoryResponse>
}