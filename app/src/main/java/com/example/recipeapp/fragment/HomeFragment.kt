package com.example.recipeapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.adapter.RecipeAdapter
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.example.recipeapp.homeData.ComplexSearch
import com.example.recipeapp.interfaces.RecipeApiInterFace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var myAdapter: RecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.starter.setOnClickListener {
            starterData()
        }
        binding.mains.setOnClickListener {
            mainsData()
        }
        binding.dessert.setOnClickListener {
            dessertData()
        }
        //        ctrl+ shift + space + enter
        binding.txtRandom.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_goodFoodFragment)
        }
        return binding.root
    }

    fun starterData() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiInterFace::class.java)

        val retrofitData = retrofitBuilder.getRecipeData(
            "162f6608c8314f2782a1770b7c758cd7", "snack", 20
        )

        retrofitData.enqueue(object : Callback<ComplexSearch?> {
            override fun onResponse(
                call: Call<ComplexSearch?>,
                response: Response<ComplexSearch?>
            ) {

                val responseBody = response.body()
                val complexrecipeList = responseBody?.results ?: emptyList()

                myAdapter = RecipeAdapter(requireContext(), complexrecipeList)

                binding.myRecycler.adapter = myAdapter
                binding.myRecycler.layoutManager = LinearLayoutManager(requireContext())


            }

            override fun onFailure(call: Call<ComplexSearch?>, t: Throwable) {
                Toast.makeText(requireContext(), "ApiNot calling", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun dessertData() {
        val retrofitBuilder = Retrofit.Builder()

            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiInterFace::class.java)

        val retrofitData = retrofitBuilder.getRecipeData(
            "162f6608c8314f2782a1770b7c758cd7", "dessert", 20
        )
        retrofitData.enqueue(object : Callback<ComplexSearch?> {
            override fun onResponse(
                call: Call<ComplexSearch?>,
                response: Response<ComplexSearch?>
            ) {

                val responseBody = response.body()
                val complexrecipeList = responseBody?.results ?: emptyList()

                myAdapter = RecipeAdapter(requireContext(), complexrecipeList)

                binding.myRecycler.adapter = myAdapter
                binding.myRecycler.layoutManager = LinearLayoutManager(requireContext())

            }

            override fun onFailure(call: Call<ComplexSearch?>, t: Throwable) {
                Toast.makeText(requireContext(), "ApiNot calling", Toast.LENGTH_SHORT).show()

            }
        })


    }

    private fun mainsData() {
        val retrofitBuilder = Retrofit.Builder()

            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiInterFace::class.java)

        val retrofitData = retrofitBuilder.getRecipeData(
            "162f6608c8314f2782a1770b7c758cd7", "main course", 20
        )

        retrofitData.enqueue(object : Callback<ComplexSearch?> {
            override fun onResponse(
                call: Call<ComplexSearch?>,
                response: Response<ComplexSearch?>
            ) {
                val responseBody = response.body()
                val complexrecipeList = responseBody?.results ?: emptyList()

                myAdapter = RecipeAdapter(requireContext(), complexrecipeList)

                binding.myRecycler.adapter = myAdapter
                binding.myRecycler.layoutManager = LinearLayoutManager(requireContext())
            }

            override fun onFailure(call: Call<ComplexSearch?>, t: Throwable) {
                Toast.makeText(requireContext(), "ApiNot calling", Toast.LENGTH_SHORT).show()
            }
        })


    }
}
