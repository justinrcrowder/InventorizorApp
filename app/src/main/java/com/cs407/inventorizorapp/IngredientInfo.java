package com.cs407.inventorizorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class IngredientInfo extends AppCompatActivity {
    Button backButton;
    public ArrayList<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_info);

        ingredients = (ArrayList<Ingredient>) getIntent().getSerializableExtra("Ingredient_list");

        // receive information about selected ingredient through intent for display
        Intent intent = getIntent();
        String ingredientName = intent.getStringExtra("ingredientName");
        int amountOwned = intent.getIntExtra("amountOwned", 0);
        int targetAmount = intent.getIntExtra("targetAmount", 0);

        Log.i("INFO", ingredientName + " " + amountOwned + " " + targetAmount);

        // show ingredient information from intent
        TextView ingredientNameTextView = findViewById(R.id.ingredientName);
        TextView ingredientThresholdTextView = findViewById(R.id.ingredientThreshold);
        ingredientNameTextView.setText(ingredientName);
        ingredientThresholdTextView.setText("Low amount threshold: " + targetAmount);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> goToMainActivity());
    }

    public void goToRecipeInfo() {

    }

    //Might need two activities for Adding and Editing recipes
    public void goToAddEditIngredient() {
        Intent intent = new Intent(this, addEditIngredientActivity.class);
        intent.putExtra("Ingredient_list", ingredients);
        startActivity(intent);
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Ingredient_list", ingredients);
        startActivity(intent);
    }

}