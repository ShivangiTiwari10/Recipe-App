package com.example.recipeapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.RecepiDetail
import com.example.recipeapp.adapter.RecipeAdapter
import com.example.recipeapp.interfaces.RecipeApiInterFace
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.example.recipeapp.homeData.recipeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var myadapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory((GsonConverterFactory.create()))
            .build()
            .create(RecipeApiInterFace::class.java)
        val retrofitData = retrofitBuilder.getRecipeData(
            "apples,+flour,+sugar\n" +
                    "[", "162f6608c8314f2782a1770b7c758cd7"
        )


//        ctrl+ shift + space + enter
        retrofitData.enqueue(object : Callback<recipeData?> {
            override fun onResponse(call: Call<recipeData?>, response: Response<recipeData?>) {
                val responseBody = response.body()
                val recipeList = responseBody ?: recipeData()

                myadapter = RecipeAdapter(requireContext(), recipeList)
                binding.myRecycler.adapter = myadapter

                binding.myRecycler.layoutManager = LinearLayoutManager(requireContext())

                myadapter.setOnItemClickListener(object : RecipeAdapter.onItemClickListener {
                    override fun onItemClicking(position: Int) {
                        val clickedRecipe = recipeList[position]


                        val intent = Intent(requireContext(), RecepiDetail::class.java)

                        intent.putExtra("Image", clickedRecipe.image)
                        intent.putExtra("title", clickedRecipe.title)
                        intent.putExtra("missed", clickedRecipe.missedIngredientCount.toString())
                        intent.putExtra("used", clickedRecipe.usedIngredientCount.toString())
                        intent.putExtra("likes", clickedRecipe.likes.toString())
                        intent.putExtra("Id", clickedRecipe.id.toString())
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(call: Call<recipeData?>, t: Throwable) {
                Log.d("MyRetrofitActivity", "onFailure ${t.message}")
            }
        })

        binding.txtRandom.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment2_to_goodFoodFragment)
        }
        return binding.root
    }

}