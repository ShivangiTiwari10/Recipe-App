package com.example.recipeapp.interfaces

import com.example.recipeapp.homeData.ComplexSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiInterFace {
    @GET("recipes/complexSearch")
    fun getRecipeData(
        @Query("apiKey") apiKey: String,
//        @Query("number") number: Int,
        @Query("type") type: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("number") number: Int

    ): Call<ComplexSearch>
}