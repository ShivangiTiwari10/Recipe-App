package com.example.recipeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityShowRecipeBinding

class ShowRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityShowRecipeBinding
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        val navController = navHostFragment!!.findNavController()


        val popupMenu = PopupMenu(this, binding.bottomBar)
        popupMenu.inflate(R.menu.bottom_menu)

        binding.bottomBar.setupWithNavController(popupMenu.menu, navController)
        binding.bottomBar.setupWithNavController(popupMenu.menu, navController)

        binding.bottomBar.onItemSelected = {

            when (it) {
                0 -> {
                    i = 0
                    navController.navigate(R.id.homeFragment)
                }
                1 -> i = 1
                2 -> i = 2
            }
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            title = when (destination.id) {
                R.id.goodFoodFragment -> "GoodFood"
                R.id.searchFragment -> "Search"
                else -> "Recipe"
            }
        }

    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        if (i == 0) {
            finish()
        }

    }

}