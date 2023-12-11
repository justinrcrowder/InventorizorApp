package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class editIngredientActivity extends AppCompatActivity {
    private String ingredientName;
    private int amountOwned;
    private int targetAmount;
    private int ingredientIndex;

    // Use the RestaurantManager to manage ingredients
    private com.cs407.inventorizorapp.restaurantManager restaurantManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ingredient);

        // Initialize the RestaurantManager instance
        restaurantManager = com.cs407.inventorizorapp.restaurantManager.getInstance();

        // Get ingredients from RestaurantManager
        ArrayList<Ingredient> ingredients = restaurantManager.getIngredients();
        ingredientIndex = getIntent().getIntExtra("IngredientIndex", -1);
        Log.i("INFO", "Index: " + ingredientIndex);
        Ingredient selectedIngredient = (Ingredient) getIntent().getSerializableExtra("SelectedIngredient");

        TextInputEditText ingredientNameEditText = findViewById(R.id.ingredientNameEditText);
        TextView amountOwnedText = findViewById(R.id.amountOwnedText);
        TextView targetAmountText = findViewById(R.id.targetAmountText);

        ingredientName = selectedIngredient.getIngredientName();
        amountOwned = selectedIngredient.getAmountOwned();
        targetAmount = selectedIngredient.getTargetAmount();

        ingredientNameEditText.setText(ingredientName);
        amountOwnedText.setText(String.valueOf(amountOwned));
        targetAmountText.setText(String.valueOf(targetAmount));

        ImageButton incrAmountOwnedButton = findViewById(R.id.incrAmountOwned);
        ImageButton decrAmountOwnedButton = findViewById(R.id.decrAmountOwned);
        ImageButton incrTargetAmountButton = findViewById(R.id.incrTargetAmount);
        ImageButton decrTargetAmountButton = findViewById(R.id.decrTargetAmount);

        incrAmountOwnedButton.setOnClickListener(v -> {
            amountOwned++;
            amountOwnedText.setText(String.valueOf(amountOwned));
        });

        decrAmountOwnedButton.setOnClickListener(v -> {
            if (amountOwned > 0) {
                amountOwned--;
                amountOwnedText.setText(String.valueOf(amountOwned));
            }
        });

        incrTargetAmountButton.setOnClickListener(v -> {
            targetAmount++;
            targetAmountText.setText(String.valueOf(targetAmount));
        });

        decrTargetAmountButton.setOnClickListener(v -> {
            if (targetAmount > 0) {
                targetAmount--;
                targetAmountText.setText(String.valueOf(targetAmount));
            }
        });

        Button saveIngredientButton = findViewById(R.id.saveIngredientButton);
        saveIngredientButton.setOnClickListener(view -> {
            Log.i("INFO", "Save button pressed");

            ingredientName = ingredientNameEditText.getText().toString().trim();

            if (ingredientName.isEmpty()) {
                showToast("Ingredient name cannot be blank");
                return;
            }

            if (targetAmount <= 0 || amountOwned <= 0) {
                showToast("Quantities must be greater than zero");
                return;
            }

            selectedIngredient.setIngredientName(ingredientName);
            selectedIngredient.setTargetAmount(targetAmount);
            selectedIngredient.setAmountOwned(amountOwned);

            ingredients.set(ingredientIndex, selectedIngredient);

            // Update the ingredients in RestaurantManager
            restaurantManager.setIngredients(ingredients);

            goToMainActivity();
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
