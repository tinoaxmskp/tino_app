package com.example.tino_app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // Get data from intent
        val recipeName = intent.getStringExtra("RECIPE_NAME")
        val recipeDescription = intent.getStringExtra("RECIPE_DESCRIPTION")
        val recipeThumbnail = intent.getIntExtra("RECIPE_THUMBNAIL", 0)

        // Bind data to views
        findViewById<TextView>(R.id.tvRecipeNameDetail).text = recipeName
        findViewById<TextView>(R.id.tvRecipeDescriptionDetail).text = recipeDescription
        findViewById<ImageView>(R.id.ivRecipeThumbnailDetail).setImageResource(recipeThumbnail)
    }
}
