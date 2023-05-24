package com.example.recipeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.databinding.ActivityStarterBinding


class StarterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStarterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStarterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}