package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class IngredientInfo extends AppCompatActivity {
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_info);

        // receive information about selected ingredient through intent for display
        Intent intent = getIntent();
        String ingredientName = intent.getStringExtra("ingredientName");
        int amountOwned = intent.getIntExtra("amountOwned", 0);
        int targetAmount = intent.getIntExtra("targetAmount", 0);

        Log.i("INFO", ingredientName + " " + amountOwned + " " + targetAmount);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> goToMainActivity());
    }

    public void goToRecipeInfo() {

    }

    //Might need two activities for Adding and Editing recipes
    public void goToAddEditIngredient() {
        Intent intent = new Intent(this, addEditIngredientActivity.class);
        startActivity(intent);
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}