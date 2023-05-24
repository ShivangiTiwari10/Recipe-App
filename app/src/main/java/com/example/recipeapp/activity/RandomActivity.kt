package com.example.recipeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.databinding.ActivityRandomBinding
import com.squareup.picasso.Picasso

class RandomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRandomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra("Image")
        val title = intent.getStringExtra("Title")
        val detail = intent.getStringExtra("Detail")
        val prize = intent.getStringExtra("PrizeServing")
        val sumery = intent.getStringExtra("summery")
        val readyy = intent.getStringExtra("ready")


        binding.scrollViewtxt.text = detail
        binding.recipeTitle.text = title
        binding.prize.text = prize
        binding.summarytext.text = sumery
        binding.readyinminutes.text = readyy


        Picasso.get().load(image).into(binding.randomImage)
    }
}