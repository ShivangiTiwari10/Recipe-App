package com.example.recipeapp.homeData

data class ComplexSearch(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)