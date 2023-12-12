package com.cs407.inventorizorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class IngredientInfo extends AppCompatActivity {
    private int ingredientIndex;
    private Button backButton;
    private Button editButton;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Recipe> recipes;

    // Use the RestaurantManager to manage ingredients
    private RestaurantManager restaurantManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_info);

        // Initialize the RestaurantManager instance
        restaurantManager = RestaurantManager.getInstance();

        // Get ingredients and selectedIngredient from RestaurantManager
        ingredients = restaurantManager.getIngredients();
        recipes = restaurantManager.getRecipes();

        Ingredient selectedIngredient = (Ingredient) getIntent().getSerializableExtra("SelectedIngredient");
        ingredientIndex = getIntent().getIntExtra("IngredientIndex", -1);
        Log.i("INFO", "got index: " + ingredientIndex);

        String ingredientName = selectedIngredient.getIngredientName();
        int amountOwned = selectedIngredient.getAmountOwned();
        int targetAmount = selectedIngredient.getTargetAmount();

        // show ingredient information from intent
        TextView ingredientNameTextView = findViewById(R.id.ingredientName);
        TextView ingredientThresholdTextView = findViewById(R.id.ingredientThreshold);
        ingredientNameTextView.setText(ingredientName);
        ingredientThresholdTextView.setText("Low amount threshold: " + targetAmount);

        // show recipes that use this ingredient
        ArrayList<String> recipeNames = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getRecipeIngredients().contains(ingredientName)) {
                recipeNames.add(recipe.getRecipeName());
            }
        }
        ListView recipeListView = findViewById(R.id.recipeListView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeNames);
        recipeListView.setAdapter(adapter);

        editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(view -> goToEditIngredient(ingredients, selectedIngredient));

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> goToMainActivity(ingredients));
    }

    private void goToEditIngredient(ArrayList<Ingredient> ingredients, Ingredient selectedIngredient) {
        Intent intent = new Intent(getApplicationContext(), editIngredientActivity.class);
        intent.putExtra("IngredientIndex", ingredientIndex);
        intent.putExtra("SelectedIngredient", selectedIngredient);
        startActivity(intent);
    }

    private void goToMainActivity(ArrayList<Ingredient> ingredients) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
