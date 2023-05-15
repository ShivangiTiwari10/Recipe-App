package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        activityScope.launch {

            delay(3000)

            val intent = Intent(this@MainActivity, ShowRecipe::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(binding.root)
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}