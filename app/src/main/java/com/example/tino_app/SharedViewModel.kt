package com.example.tino_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {

    private val _registrationComplete = MutableLiveData<Boolean>()
    val registrationComplete: LiveData<Boolean> get() = _registrationComplete
    // Sample data for RecyclerView
    private val recipes = listOf(
        Recipe("Mediterranean Bliss Salad", "A refreshing mix of Mediterranean flavors with crisp vegetables.", R.drawable.ffone),
        Recipe("Spicy Cajun Shrimp Skewers", "Juicy shrimp skewers with bold Cajun seasoning.", R.drawable.footwo),
        Recipe("Creamy Tuscan Chicken Pasta", "Rich and creamy pasta with sun-dried tomatoes and chicken.", R.drawable.foothree),
        Recipe("Mango Tango Smoothie Bowl", "A vibrant smoothie bowl bursting with tropical flavors.", R.drawable.fmang),
        Recipe("Savory Herb-Crusted Lamb Chops", "Perfectly seasoned lamb chops with a herb crust.", R.drawable.foofive),
        Recipe("Thai Basil Chicken Stir-Fry", "A classic Thai dish with aromatic basil and tender chicken.", R.drawable.foosix),
        Recipe("Smoky BBQ Jackfruit Sliders", "Delicious plant-based sliders with smoky BBQ jackfruit.", R.drawable.fooseven),
        Recipe("Crispy Teriyaki Salmon Rolls", "Crispy rolls filled with salmon and a touch of teriyaki.", R.drawable.fooeight),
        Recipe("Rustic Garlic Rosemary Focaccia", "A rustic bread infused with garlic and fresh rosemary.", R.drawable.foonine),
        Recipe("Golden Saffron Paella", "A traditional Spanish dish with golden saffron rice.", R.drawable.footen),
        Recipe("Velvety Pumpkin Spice Soup", "A warm, creamy soup with a touch of pumpkin spice.", R.drawable.feleven),
        Recipe("Zesty Lemon Herb Quinoa", "Light and zesty quinoa with lemon and fresh herbs.", R.drawable.ftwelve),
        Recipe("Decadent Chocolate Lava Cake", "Rich chocolate cake with a gooey molten center.", R.drawable.f3teen),
        Recipe("Crunchy Korean Kimchi Pancakes", "Crispy pancakes with a spicy kick of kimchi.", R.drawable.f4teen),
        Recipe("Caramelized Onion and Mushroom Tart", "A savory tart topped with caramelized onions and mushrooms.", R.drawable.f5teen),
        Recipe("Fiery Peri-Peri Chicken Wings", "Spicy chicken wings with bold peri-peri flavors.", R.drawable.f6teen),
        Recipe("Tangy Raspberry Cheesecake Bars", "Cheesecake bars with a tangy raspberry swirl.", R.drawable.f7teen),
        Recipe("Creamy Spinach and Artichoke Dip", "A rich and creamy dip with spinach and artichokes.", R.drawable.f8teen),
        Recipe("Hearty Moroccan Lentil Stew", "A comforting stew with Moroccan spices and lentils.", R.drawable.f9teen),
        Recipe("Fluffy Coconut Mango Rice Pudding", "Creamy rice pudding with a tropical twist of coconut and mango.", R.drawable.ftwenty)
    )


    private val _filteredRecipes = MutableStateFlow(recipes)
    val filteredRecipes: StateFlow<List<Recipe>> = _filteredRecipes

    fun filterRecipes(query: String?) {
        if (query.isNullOrEmpty() || query.length < 3) {
            _filteredRecipes.value = recipes
        } else {
            _filteredRecipes.update {
                recipes.filter { recipe ->
                    recipe.name.contains(query, ignoreCase = true) ||
                            recipe.description.contains(query, ignoreCase = true)
                }
            }
        }
    }

    fun setRegistrationComplete(value : Boolean) {
        _registrationComplete.value = value
    }

}
