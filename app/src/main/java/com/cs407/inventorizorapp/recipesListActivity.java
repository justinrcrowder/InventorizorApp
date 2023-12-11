package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class recipesListActivity extends AppCompatActivity {

    public static ArrayList<Recipe> recipes;
    public static ArrayList<String> displayRecipes;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // Use restaurant manager to get current list of recipes
        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        recipes = restaurantManager.getRecipes();

        if (recipes != null && recipes.size() > 0) {
            displayRecipes = new ArrayList<>();
            for (Recipe recipe : recipes) {
                displayRecipes.add(recipe.getRecipeName());
            }

            // load displayRecipes into the listview
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayRecipes);
            ListView recipesList = findViewById(R.id.recipesListView);
            recipesList.setAdapter(adapter);

            // If recipe clicked on, take user to recipe info page
            recipesList.setOnItemClickListener((adapterView, view, recipeIndex, l) -> {
                Recipe selectedRecipe = recipes.get(recipeIndex);
                goToRecipeInfo(selectedRecipe, recipeIndex);
            });
        }
    }

    public void goToAddRecipe(View view) {
        Intent intent = new Intent(this, addRecipeActivity.class);
        startActivity(intent);
    }

    public void goToRestaurantProfile(View view) {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }

    public void goToRecipeInfo(Recipe selectedRecipe, int index) {
        Intent intent = new Intent(getApplicationContext(), recipeInfoActivity.class);
        intent.putExtra("SelectedRecipe", selectedRecipe);
        intent.putExtra("RecipeIndex", index);
        startActivity(intent);
    }
}