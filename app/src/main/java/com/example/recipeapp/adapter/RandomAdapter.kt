package com.example.recipeapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.goodFoodData.Recipe
import com.squareup.picasso.Picasso

class RandomAdapter(private val context: Context, val recipies: List<Recipe>) :
    RecyclerView.Adapter<RandomAdapter.RandomViewHolder>() {


    private lateinit var myListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicking(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        myListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.random_activity_item, parent, false)
        Log.d("onCreate", "$itemView")
        return RandomViewHolder(
            itemView
        )
    }


    override fun getItemCount(): Int {
        return recipies.size
    }

    override fun onBindViewHolder(holder: RandomViewHolder, position: Int) {

        val currntItem = recipies[position]

        holder.text1.text = currntItem.title

        Picasso.get().load(currntItem.image).into(holder.image1)

        holder.itemView.setOnClickListener {
            myListener.onItemClicking(position)
        }


    }

    class RandomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image1: ImageView
        val text1: TextView

        init {
            image1 = itemView.findViewById(R.id.card1img)
            text1 = itemView.findViewById(R.id.txt1)
        }

    }

}