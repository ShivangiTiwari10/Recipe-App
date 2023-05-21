package com.example.recipeapp.interfaces

import com.example.recipeapp.goodFoodData.GoodFoodData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface goodFoodApiInterface {
    @GET("recipes/random")
    fun getRandomFoodData(
        @Query("apiKey") apiKey: String,
        @Query("tags") tags: String,
        @Query("number") number: Int
    ): Call<GoodFoodData>

}