package com.example.recipeapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp.databinding.FragmentGoodFoodBinding

class GoodFoodFragment : Fragment() {

    private lateinit var binding: FragmentGoodFoodBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentGoodFoodBinding.inflate(layoutInflater)
        return (binding.root)
    }

}