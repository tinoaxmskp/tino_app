package com.example.tino_app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Recipe(val name: String, val description: String, val thumbnail: Int)
class RecipeAdapter(private var recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView = itemView.findViewById(R.id.ivThumbnail)
        val name: TextView = itemView.findViewById(R.id.tvRecipeName)
        val description: TextView = itemView.findViewById(R.id.tvRecipeDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.thumbnail.setImageResource(recipe.thumbnail)
        holder.name.text = recipe.name
        holder.description.text = recipe.description

        // Set click listener
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RecipeDetailActivity::class.java).apply {
                putExtra("RECIPE_NAME", recipe.name)
                putExtra("RECIPE_DESCRIPTION", recipe.description)
                putExtra("RECIPE_THUMBNAIL", recipe.thumbnail)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = recipes.size

    fun updateData(newRecipes: List<Recipe>) {
        if (newRecipes != recipes) {
            recipes = newRecipes
            notifyDataSetChanged()
        }
    }

}
