package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class addRecipeActivity extends AppCompatActivity {

    private EditText recipeNameEditText;
    private String recipeName;
    private EditText recipeInstructionsEditText;
    private String recipeInstructions;
    private EditText recipeIngredientsEditText;
    private String recipeIngredientsCommaSeparated;
    private ArrayList<String> recipeIngredients;
    Recipe recipe;
    Button saveRecipeButton;
    private RestaurantManager restaurantManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // Initialize restaurant manager
        restaurantManager = RestaurantManager.getInstance();

        // Get recipe name from user input
        recipeNameEditText = findViewById(R.id.recipeNameEditText);

        // Get recipe instructions from user input
        recipeInstructionsEditText = findViewById(R.id.addintructionsfield);

        // Get recipe ingredients from user input
        recipeIngredientsEditText = findViewById(R.id.addingredientedittext);

        saveRecipeButton = findViewById(R.id.saveRecipeButton);
        saveRecipeButton.setOnClickListener(view -> {
            // Check for valid recipe name and instruction inputs
            try {
                recipeName = recipeNameEditText.getText().toString();
                recipeInstructions = recipeInstructionsEditText.getText().toString();
                recipeIngredientsCommaSeparated = recipeIngredientsEditText.getText().toString();
            } catch (NullPointerException e) {
                showToast("Enter a valid recipe name, ingredients, or instructions");
                return;
            }

            // Check if ingredient list is comma-separated
            if (!recipeIngredientsCommaSeparated.matches("^[^,]+(,[^,]+)*$")) {
                // String not comma-separated
                showToast("Ingredients list is not comma-separated");
                return;
            }

            // Convert comma-separated ingredients to arraylist
            String[] ingredientsArray = recipeIngredientsCommaSeparated.split(",");
            recipeIngredients = new ArrayList<String>(Arrays.asList(ingredientsArray));

            // Create Recipe instance
            recipe = new Recipe(recipeName, recipeInstructions, recipeIngredients);

            // Add recipe to restaurant manager class
            restaurantManager.addRecipe(recipe);

            // give push notification that new recipe has been added
            NotificationHelper.getInstance().setNotificationContext("New Recipe Added", recipeName + " has been added to your recipes list");
            NotificationHelper.getInstance().showNotification(getApplicationContext());


            goToRecipesList(view);
        });
    }

    public void goToRecipesList(View view) {
        Intent intent = new Intent(this, recipesListActivity.class);
        startActivity(intent);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}