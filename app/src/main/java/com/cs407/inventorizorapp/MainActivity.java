package com.cs407.inventorizorapp;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Ingredient> ingredients;
    public static ArrayList<String> displayIngredients;
    ArrayAdapter adapter;
    Button addIngredientButton;
    Button addRecipeButton;
    Button restaurantProfileButton;

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (!isGranted) {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    });

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return;
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
        NotificationHelper.getInstance().createNotificationChannel(getApplicationContext());

        restaurantProfileButton = findViewById(R.id.profileView);
        restaurantProfileButton.setOnClickListener(view -> goToRestaurantProfile());

        // Use RestaurantManager to get ingredients
        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        ingredients = restaurantManager.getIngredients();

        if (ingredients != null && ingredients.size() > 0) {
            findViewById(R.id.emptyMessage).setVisibility(View.GONE);
            displayIngredients = new ArrayList<>();
            for (Ingredient ingredient : ingredients) {
                displayIngredients.add(String.format("Name: %s\nAmount Owned: %s    Target Amount: %s", ingredient.getIngredientName(), ingredient.getAmountOwned(), ingredient.getTargetAmount()));
            }

            // load displayIngredients into listview for display
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayIngredients);
            ListView ingredientsListView = findViewById(R.id.ingredientListView);
            ingredientsListView.setAdapter(adapter);

            ingredientsListView.setOnItemClickListener((adapterView, view, ingredientIndex, l) -> {
                Ingredient selectedIngredient = ingredients.get(ingredientIndex);
                goToIngredientInfo(selectedIngredient, ingredientIndex);
            });
        } else {
            findViewById(R.id.ingredientListView).setVisibility(View.GONE);
            findViewById(R.id.emptyMessage).setVisibility(View.VISIBLE);
        }

        addRecipeButton = findViewById(R.id.addRecipeButton);
        addRecipeButton.setOnClickListener(view -> {
            goToAddRecipe();
        });

        addIngredientButton = findViewById(R.id.addIngredientButton);
        addIngredientButton.setOnClickListener(view -> {
            goToAddIngredient();
        });
    }

    public void goToAddIngredient() {
        Intent intent = new Intent(this, addIngredientActivity.class);
        startActivity(intent);
    }

    public void goToIngredientInfo(Ingredient selectedIngredient, int index) {
        Intent intent = new Intent(getApplicationContext(), IngredientInfo.class);
        intent.putExtra("SelectedIngredient", selectedIngredient);
        intent.putExtra("IngredientIndex", index);
        Log.i("INFO", index + " " + selectedIngredient.getIngredientName());
        startActivity(intent);
    }

    public void goToAddRecipe() {
        Intent intent = new Intent(this, addRecipeActivity.class);
        startActivity(intent);
    }

    public void goToRestaurantProfile() {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }

}