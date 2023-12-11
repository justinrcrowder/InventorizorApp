package com.cs407.inventorizorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class addIngredientActivity extends AppCompatActivity {
    private Button saveIngredientButton;
    private TextInputEditText ingredientNameEditText;
    private TextView amountOwnedText;
    private TextView targetAmountText;
    private ImageButton incrAmountOwnedButton;
    private ImageButton decrAmountOwnedButton;
    private ImageButton incrTargetAmountButton;
    private ImageButton decrTargetAmountButton;
    private String ingredientName;
    private int amountOwned;
    private int targetAmount;
    private ArrayList<Ingredient> ingredients;

    // Use the RestaurantManager to manage ingredients
    private com.cs407.inventorizorapp.restaurantManager restaurantManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        // Initialize the RestaurantManager instance
        restaurantManager = com.cs407.inventorizorapp.restaurantManager.getInstance();

        // Get ingredients from RestaurantManager
        ingredients = restaurantManager.getIngredients();

        ingredientNameEditText = findViewById(R.id.ingredientNameEditText);
        amountOwnedText = findViewById(R.id.amountOwnedText);
        targetAmountText = findViewById(R.id.targetAmountText);
        incrAmountOwnedButton = findViewById(R.id.incrAmountOwned);
        decrAmountOwnedButton = findViewById(R.id.decrAmountOwned);
        incrTargetAmountButton = findViewById(R.id.incrTargetAmount);
        decrTargetAmountButton = findViewById(R.id.decrTargetAmount);

        ingredientName = "";
        amountOwned = 5;
        targetAmount = 5;

        ingredientNameEditText.setText(ingredientName);
        amountOwnedText.setText(String.valueOf(amountOwned));
        targetAmountText.setText(String.valueOf(targetAmount));

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

        saveIngredientButton = findViewById(R.id.saveIngredientButton);
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

            Ingredient newIngredient = new Ingredient(ingredientName, targetAmount, amountOwned);

            if (ingredients != null) {
                ingredients.add(newIngredient);
                Log.i("INFO", "New ingredient added: " + newIngredient.getIngredientName());
            } else {
                ingredients = new ArrayList<>();
                ingredients.add(newIngredient);
                Log.i("INFO", "New ingredient added: " + newIngredient.getIngredientName());
            }

            // Update the ingredients in RestaurantManager
            restaurantManager.setIngredients(ingredients);

            goToMainActivity();
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
