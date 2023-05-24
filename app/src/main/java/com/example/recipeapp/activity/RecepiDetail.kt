package com.example.recipeapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipeapp.databinding.ActivityRecepiDetailBinding
import com.squareup.picasso.Picasso

class RecepiDetail : AppCompatActivity() {

    private lateinit var binding: ActivityRecepiDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecepiDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra("Image")
        val title = intent.getStringExtra("title")
        val mised = intent.getStringExtra("missed")
        val ussed = intent.getStringExtra("used")
        val likes = intent.getStringExtra("likes")
        val iid = intent.getStringExtra("Id")


        binding.txtTitle.text = title
        Picasso.get().load(image).into(binding.recipeImage)
        binding.textMissed.text = "MissedIngredient:$mised"
        binding.textUsed.text = "UsedIngredient:$ussed"
        binding.likes.text = "Likes:$likes"
        binding.Id.text = "ID:$iid"

    }
}