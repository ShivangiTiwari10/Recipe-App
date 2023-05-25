package com.example.recipeapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.databinding.ActivityDessertBinding
import com.squareup.picasso.Picasso

class DessertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDessertBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDessertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra("Image")
        val title = intent.getStringExtra("title")
        val cooking = intent.getStringExtra("cookingMinutes")


        Picasso.get().load(image).into(binding.recipeImage)
        binding.recipeTitle.text = title
        binding.textView.text = "Time: $cooking Minutes"


        val analyzedInstructions = intent.getStringArrayListExtra("AnalyzedInstructions")
        binding.stepstext.text = analyzedInstructions.toString()
    }
}