package com.cs407.inventorizorapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Ingredient> ingredients;
    public static ArrayList<String> displayIngredients;
    ArrayAdapter adapter;
    Button addIngredientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use RestaurantManager to get ingredients
        restaurantManager restaurantManager = com.cs407.inventorizorapp.restaurantManager.getInstance();
        ingredients = restaurantManager.getIngredients();

//        uncomment if switching back to database
//        load ingredients from the database and prepare them as strings
//        Context context = getApplicationContext();
//        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("restaurant", Context.MODE_PRIVATE, null);
//        dbHelper = new DBHelper(sqLiteDatabase);
//        ingredients = dbHelper.readIngredients();

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

    public void goToRestaurantProfile(View view) {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }

}