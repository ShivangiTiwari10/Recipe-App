package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.databinding.ActivityShowRecipeBinding

class ShowRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityShowRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}