package com.example.recipeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.databinding.ActivityDessertBinding

class DessertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDessertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDessertBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}