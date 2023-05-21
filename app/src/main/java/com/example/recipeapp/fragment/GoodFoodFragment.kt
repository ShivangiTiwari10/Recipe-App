package com.example.recipeapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp.RandomActivity
import com.example.recipeapp.databinding.FragmentGoodFoodBinding

class GoodFoodFragment : Fragment() {

    private lateinit var binding: FragmentGoodFoodBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentGoodFoodBinding.inflate(layoutInflater)


        binding.txtRandom.setOnClickListener {

            val intent = Intent(requireContext(), RandomActivity::class.java)
            startActivity(intent)


            //            to Go other fragment
            //            findNavController().navigate(R.id.action_goodFoodFragment_to_searchFragment)
        }
        return (binding.root)
    }
}