package com.example.recipeapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.RandomActivity
import com.example.recipeapp.adapter.RandomAdapter
import com.example.recipeapp.databinding.FragmentGoodFoodBinding
import com.example.recipeapp.goodFoodData.GoodFoodData
import com.example.recipeapp.interfaces.goodFoodApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GoodFoodFragment : Fragment() {

    private lateinit var binding: FragmentGoodFoodBinding
    private lateinit var myAdapter: RandomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentGoodFoodBinding.inflate(layoutInflater)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")

            .addConverterFactory((GsonConverterFactory.create()))
            .build()
            .create(goodFoodApiInterface::class.java)

        val retrofitData = retrofitBuilder.getRandomFoodData(
            "162f6608c8314f2782a1770b7c758cd7", "vegetarian", 20

        )

        retrofitData.enqueue(object : Callback<GoodFoodData?> {
            override fun onResponse(call: Call<GoodFoodData?>, response: Response<GoodFoodData?>) {

                val responseBody = response.body()
                val randomRecipeList = responseBody?.recipes ?: emptyList()

                myAdapter = RandomAdapter(requireContext(), randomRecipeList)
                binding.randomRecycler.adapter = myAdapter
                binding.randomRecycler.layoutManager = LinearLayoutManager(requireContext())


                myAdapter.setOnItemClickListener(object : RandomAdapter.OnItemClickListener {
                    override fun onItemClicking(position: Int) {

                        val clickedRecipe = randomRecipeList[position]

                        val intent = Intent(requireContext(), RandomActivity::class.java)
                        intent.putExtra("Image", clickedRecipe.image)
                        intent.putExtra("Title", clickedRecipe.title)
                        intent.putExtra("Detail", "Instruction:\n${clickedRecipe.instructions}")
                        intent.putExtra(
                            "PrizeServing",
                            "PrizePerServing:\n ${clickedRecipe.pricePerServing}"
                        )
                        intent.putExtra("summery", "Summary:\n ${clickedRecipe.summary}")
                        intent.putExtra("ready", "ReadyIn:\n ${clickedRecipe.readyInMinutes} Minutes")

                        startActivity(intent)
                    }

                })
            }

            override fun onFailure(call: Call<GoodFoodData?>, t: Throwable) {
            }
        })


        return (binding.root)
    }

}