package com.example.recipeapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.homeData.recipeDataItem
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class RecipeAdapter(private val context: Context, val recipeist: List<recipeDataItem>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private lateinit var myListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClicking(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        myListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val itemView = LayoutInflater.from(context).inflate(R.layout.recipe_items, parent, false)
        Log.d("onCreate", "$itemView")
        return RecipeViewHolder(
            itemView
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        val currentItem = recipeist[position]
        holder.title.text = currentItem.title
        holder.detail.text = "usedIngredient: ${currentItem.usedIngredientCount}"

        Picasso.get().load(currentItem.image).into(holder.imagee)
        Log.d("TiTle", currentItem.title)
        Log.d("imageType", currentItem.imageType)

        holder.ratingg.rating = currentItem.likes.toFloat()

        holder.itemView.setOnClickListener {
            myListener.onItemClicking(position)
        }

    }

    override fun getItemCount(): Int {
        Log.d("getItemCount", "${recipeist.size}")

        return recipeist.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val imagee: ShapeableImageView
        val detail: TextView
        val ratingg: RatingBar

        init {
            title = itemView.findViewById(R.id.recipeTitle)
            imagee = itemView.findViewById(R.id.productImage)
            detail = itemView.findViewById(R.id.recipeDiscription)
            ratingg = itemView.findViewById(R.id.ratingbar)
        }

    }
}