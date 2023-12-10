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
    DBHelper dbHelper;
    ArrayAdapter adapter;
    Button addIngredientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        uncomment if switching back to database
//        load ingredients from the database and prepare them as strings
//        Context context = getApplicationContext();
//        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("restaurant", Context.MODE_PRIVATE, null);
//        dbHelper = new DBHelper(sqLiteDatabase);
//        ingredients = dbHelper.readIngredients();

        // fill ingredients from intent
        if (getIntent() != null) {
                Log.i("INFO", "Ingredients received!");
                ingredients = (ArrayList<Ingredient>) getIntent().getSerializableExtra("Ingredient_list");
        } else {
            ingredients = new ArrayList<>();
        }

        if (ingredients != null && ingredients.size() > 0) {
            findViewById(R.id.emptyMessage).setVisibility(View.GONE);
            displayIngredients = new ArrayList<>();
            for (Ingredient ingredient : ingredients) {
                displayIngredients.add(String.format("Name:%s\nAmount Owned:%s\tOwned:%s", ingredient.getIngredientName(), ingredient.getAmountOwned(), ingredient.getTargetAmount()));
            }

            // load displayIngredients into listview for display
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayIngredients);
            ListView ingredientsListView = findViewById(R.id.ingredientListView);
            ingredientsListView.setAdapter(adapter);

            ingredientsListView.setOnItemClickListener((adapterView, view, ingredientIndex, l) -> {
                Ingredient selectedIngredient = ingredients.get(ingredientIndex);
                goToIngredientInfo(selectedIngredient);
            });
        } else {
            findViewById(R.id.ingredientListView).setVisibility(View.GONE);
            findViewById(R.id.emptyMessage).setVisibility(View.VISIBLE);
        }

        addIngredientButton = findViewById(R.id.addIngredientButton);
        addIngredientButton.setOnClickListener(view -> {
            goToAddEditIngredient();
        });
    }

    public void goToAddEditIngredient() {
        Intent intent = new Intent(this, addEditIngredientActivity.class);
        intent.putExtra("Ingredient_list", ingredients);
        startActivity(intent);
    }

    public void goToIngredientInfo(Ingredient selectedIngredient) {
        Intent intent = new Intent(getApplicationContext(), IngredientInfo.class);
        intent.putExtra("ingredientName", selectedIngredient.getIngredientName());
        intent.putExtra("amountOwned", selectedIngredient.getAmountOwned());
        intent.putExtra("targetAmount", selectedIngredient.getTargetAmount());
        intent.putExtra("Ingredient_list", ingredients);
        startActivity(intent);
    }

    //Might not need this function-- depends on how the camera screen is setup
//    public void goToCameraMode() {
//
//    }

    // Adding a comment on Line 27 of MainActivity for testing Pushes
    public void goToRestaurantProfile(View view) {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.closeDatabase();
    }

}