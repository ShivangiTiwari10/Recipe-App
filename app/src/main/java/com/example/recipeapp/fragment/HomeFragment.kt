package com.example.recipeapp.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.*
import com.example.recipeapp.activity.DessertActivity
import com.example.recipeapp.activity.MainsActivity
import com.example.recipeapp.activity.StarterActivity
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
    private lateinit var dialog: AlertDialog

    private lateinit var myAdapter: RecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)


        dialog = AlertDialog.Builder(requireContext()).setView(R.layout.loading_layout)
            .setCancelable(false)
            .create()

//        dialog = BuildConfig

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
        dialog.show()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiInterFace::class.java)

        val retrofitData = retrofitBuilder.getRecipeData(
            "162f6608c8314f2782a1770b7c758cd7", "snack", true, 20,
        )

        retrofitData.enqueue(object : Callback<ComplexSearch?> {
            override fun onResponse(
                call: Call<ComplexSearch?>,
                response: Response<ComplexSearch?>
            ) {

                val responseBody = response.body()
                dialog.dismiss()

                val complexrecipeList = responseBody?.results ?: emptyList()

                myAdapter = RecipeAdapter(requireContext(), complexrecipeList)

                binding.myRecycler.adapter = myAdapter
                binding.myRecycler.layoutManager = LinearLayoutManager(requireContext())


                myAdapter.setOnItemClickListener(object : RecipeAdapter.onItemClickListener {
                    override fun onItemClicking(position: Int) {

                        val clickedStarter = complexrecipeList[position]
                        val intent = Intent(requireContext(), StarterActivity::class.java)
                        intent.putExtra("Image", clickedStarter.image)
                        intent.putExtra("title", clickedStarter.title)

                        intent.putExtra("summary", clickedStarter.summary)
//                        intent.putExtra("stepps",clickedStarter.)
                        intent.putExtra("cookingMinutes", clickedStarter.cookingMinutes.toString())

                        val analyzedInstructions = clickedStarter.analyzedInstructions

                        if (analyzedInstructions.isNotEmpty()) {
                            val steps = analyzedInstructions[0].steps.map { it.step }
                            intent.putStringArrayListExtra("AnalyzedInstructions", ArrayList(steps))
                        } else {
                            intent.putStringArrayListExtra("AnalyzedInstructions", ArrayList())
                        }

                        startActivity(intent)
                    }

                })


            }

            override fun onFailure(call: Call<ComplexSearch?>, t: Throwable) {
                Toast.makeText(requireContext(), "ApiNot calling", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun dessertData() {
        dialog.show()

        val retrofitBuilder = Retrofit.Builder()

            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiInterFace::class.java)

        val retrofitData = retrofitBuilder.getRecipeData(
            "162f6608c8314f2782a1770b7c758cd7", "dessert", true, 20
        )
        retrofitData.enqueue(object : Callback<ComplexSearch?> {
            override fun onResponse(
                call: Call<ComplexSearch?>,
                response: Response<ComplexSearch?>
            ) {

                val responseBody = response.body()
                dialog.dismiss()

                val complexrecipeList = responseBody?.results ?: emptyList()

                myAdapter = RecipeAdapter(requireContext(), complexrecipeList)

                binding.myRecycler.adapter = myAdapter
                binding.myRecycler.layoutManager = LinearLayoutManager(requireContext())


                myAdapter.setOnItemClickListener(object : RecipeAdapter.onItemClickListener {
                    override fun onItemClicking(position: Int) {

                        val clickedStarter = complexrecipeList[position]
                        val intent = Intent(requireContext(), DessertActivity::class.java)
                        intent.putExtra("Image", clickedStarter.image)
                        intent.putExtra("title", clickedStarter.title)
                        intent.putExtra("cookingMinutes", clickedStarter.cookingMinutes.toString())


                        val analyzedInstructions = clickedStarter.analyzedInstructions
                        if (analyzedInstructions.isNotEmpty()) {
                            val steps = analyzedInstructions[0].steps.map { it.step }
                            intent.putStringArrayListExtra("AnalyzedInstructions", ArrayList(steps))
                        } else {
                            intent.putStringArrayListExtra("AnalyzedInstructions", ArrayList())
                        }

                        startActivity(intent)

                    }
                })

            }

            override fun onFailure(call: Call<ComplexSearch?>, t: Throwable) {
                Toast.makeText(requireContext(), "ApiNot calling", Toast.LENGTH_SHORT).show()

            }
        })


    }

    private fun mainsData() {
        dialog.show()

        val retrofitBuilder = Retrofit.Builder()

            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiInterFace::class.java)

        val retrofitData = retrofitBuilder.getRecipeData(
            "162f6608c8314f2782a1770b7c758cd7", "main course", true, 20
        )

        retrofitData.enqueue(object : Callback<ComplexSearch?> {
            override fun onResponse(
                call: Call<ComplexSearch?>,
                response: Response<ComplexSearch?>
            ) {
                val responseBody = response.body()
                dialog.dismiss()

                val complexrecipeList = responseBody?.results ?: emptyList()

                myAdapter = RecipeAdapter(requireContext(), complexrecipeList)

                binding.myRecycler.adapter = myAdapter
                binding.myRecycler.layoutManager = LinearLayoutManager(requireContext())

                myAdapter.setOnItemClickListener(object : RecipeAdapter.onItemClickListener {
                    override fun onItemClicking(position: Int) {
                        val clickedMainsData = complexrecipeList[position]

                        val intent = Intent(requireContext(), MainsActivity::class.java)
                        intent.putExtra("Image", clickedMainsData.image)
                        intent.putExtra("title", clickedMainsData.title)
                        intent.putExtra(
                            "cookingMinutes",
                            clickedMainsData.cookingMinutes.toString()
                        )


                        val analyzedInstructions = clickedMainsData.analyzedInstructions

                        if (analyzedInstructions.isNotEmpty()) {
                            val steps = analyzedInstructions[0].steps.map { it.step }
                            intent.putStringArrayListExtra("AnalyzedInstructions", ArrayList(steps))
                        } else {
                            intent.putStringArrayListExtra("AnalyzedInstructions", ArrayList())
                        }

                        val ingredient = clickedMainsData.analyzedInstructions

                        if (ingredient.isNotEmpty()) {
                            val ingred = ingredient[0].steps.map { it.ingredients }
                            intent.putExtra("Ingredient", ingred[0].toString())
                        } else {
                            Log.d("ingredient", " Data Not found")
                        }
                        startActivity(intent)
                    }
                })

            }

            override fun onFailure(call: Call<ComplexSearch?>, t: Throwable) {
                Toast.makeText(requireContext(), "ApiNot calling", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
