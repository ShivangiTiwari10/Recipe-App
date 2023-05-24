package com.example.recipeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.databinding.ActivityMainsBinding

class MainsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}