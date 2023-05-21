package com.example.recipeapp.interfaces

import com.example.recipeapp.homeData.recipeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiInterFace {
    @GET("recipes/findByIngredients")

    fun getRecipeData(
        @Query("ingredients") ingredients: String,
//        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): Call<recipeData>
}