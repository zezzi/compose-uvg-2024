package com.eventapp.eventappuvg.ui.meals.repository

import com.eventapp.eventappuvg.networking.MealsWebService
import com.eventapp.eventappuvg.networking.responses.MealsCategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    fun getMeals(successCallback: (response: MealsCategoryResponse?) -> Unit) {
        return webService.getMeals().enqueue(object : Callback<MealsCategoryResponse> {

            override fun onResponse(
                call: Call<MealsCategoryResponse>,
                response: Response<MealsCategoryResponse>
            ) {
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<MealsCategoryResponse>, t: Throwable) {
                // TODO treat failure
            }
        })
    }
}