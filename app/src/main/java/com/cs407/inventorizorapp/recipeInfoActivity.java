package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class recipeInfoActivity extends AppCompatActivity {

    private int recipeIndex;
    private ArrayList<Recipe> recipes;
    private RestaurantManager restaurantManager;
    private Button backButton;
    private Button editButton;
    public static ArrayList<String> displayIngredients;
    private ArrayList<Ingredient> ingredientsList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_information);

        // Initialize restaurantmanager
        restaurantManager = RestaurantManager.getInstance();

        // Get selected recipe and all ingredients from RestaurantManager
        ingredientsList = restaurantManager.getIngredients();
        recipes = restaurantManager.getRecipes();
        Recipe selectedRecipe = (Recipe) getIntent().getSerializableExtra("SelectedRecipe");
        recipeIndex = getIntent().getIntExtra("RecipeIndex", -1);

        String recipeName = selectedRecipe.getRecipeName();
        ArrayList<String> recipeIngredients = selectedRecipe.getRecipeIngredients();
        displayIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredientsList) {
            if (recipeIngredients.contains(ingredient.getIngredientName())) {
                displayIngredients.add(String.format("Name: %s\nAmount Owned: %s    Target Amount: %s", ingredient.getIngredientName(), ingredient.getAmountOwned(), ingredient.getTargetAmount()));
            }
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayIngredients);
        ListView ingredientsListView = findViewById(R.id.recipeIngredientsListView);
        ingredientsListView.setAdapter(adapter);
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