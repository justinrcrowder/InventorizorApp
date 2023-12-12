package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class recipeInfoActivity extends AppCompatActivity {

    private int recipeIndex;
    private ArrayList<Recipe> recipes;
    private RestaurantManager restaurantManager;
    private Button backButton;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);

        // Initialize restaurantmanager
        restaurantManager = RestaurantManager.getInstance();

        // Get selected recipe from restaurantmanager
        recipes = restaurantManager.getRecipes();
        Recipe selectedRecipe = (Recipe) getIntent().getSerializableExtra("SelectedRecipe");
        recipeIndex = getIntent().getIntExtra("RecipeIndex", -1);

        String recipeName = selectedRecipe.getRecipeName();
        ArrayList<String> recipeIngredients = selectedRecipe.getRecipeIngredients();
        String recipeInstructions = selectedRecipe.getRecipeInstructions();

        // Show recipe info (Need to add the ingredients list)
        TextView recipeNameText = findViewById(R.id.recipeName);
        TextView recipeInstructionsText = findViewById(R.id.instructionsField);
        recipeNameText.setText(recipeName);
        recipeInstructionsText.setText(recipeInstructions);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {
            goToRecipeList(view);
        });

        editButton = findViewById(R.id.editRecipeButton);
        editButton.setOnClickListener(view -> {
            goToAddEditRecipe();
        });
    }

    private void goToRecipeList(View view) {
        Intent intent = new Intent(this, recipesListActivity.class);
        startActivity(intent);
    }

    public void goToAddEditRecipe() {
        Intent intent = new Intent(this, addRecipeActivity.class);
        startActivity(intent);
    }
}