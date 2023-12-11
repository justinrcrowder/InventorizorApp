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
        restaurantManager restaurantManager = com.cs407.inventorizorapp.restaurantManager.getInstance();
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

            // Need to add button to go to Recipe Info Page
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
}