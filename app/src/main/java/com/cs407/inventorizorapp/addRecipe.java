package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class addRecipe extends AppCompatActivity {
    public static ArrayList<Ingredient> ingredients;
    public static ArrayList<Recipe> recipes;
    Button doneButton;
    Button backButton;
    EditText recipeNameEditText;
    EditText addInstructionsField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        ingredients = restaurantManager.getIngredients();
        recipes = restaurantManager.getRecipes();

        recipeNameEditText = findViewById(R.id.recipeNameEditText);
        addInstructionsField = findViewById(R.id.addintructionsfield);

        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewRecipe();
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRestaurantProfile();
            }
        });
    }

    private void addNewRecipe() {
        String recipeName = recipeNameEditText.getText().toString().trim();
        String instructions = addInstructionsField.getText().toString().trim();

        if (recipeName.isEmpty() || instructions.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Ingredient> recipeIngredients = new ArrayList<>();

        Recipe newRecipe = new Recipe(recipeName, instructions, recipeIngredients);

        // Add ingredients logic

        recipes.add(newRecipe);

        Toast.makeText(this, "Recipe added successfully", Toast.LENGTH_SHORT).show();
        goToRestaurantProfile();
    }

    public void goToRestaurantProfile() {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }

}