package com.eventapp.eventappuvg.networking.responses

import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryDescription") var description: String,
    @SerializedName("strCategoryThumb") val imageUrl: String)