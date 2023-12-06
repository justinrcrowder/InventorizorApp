package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Ingredient> ingredients;
    Button addIngredientButton;
    DBHelper dbHelper;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("restaurant", Context.MODE_PRIVATE, null);
        dbHelper = new DBHelper(sqLiteDatabase);
        ingredients = dbHelper.readIngredients();

        ArrayList<String> displayIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            displayIngredients.add(String.format("Name:%s\nAmount Owned:%s\tOwned:%s", ingredient.getIngredientName(), ingredient.getAmountOwned(), ingredient.getTargetAmount()));
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayIngredients);
        ListView ingredientsListView = findViewById(R.id.ingredientListView);
        ingredientsListView.setAdapter(adapter);

        addIngredientButton = findViewById(R.id.addIngredientButton);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("INFO", "addIngredientButton pressed!");

//                The code below is for adding an ingredient to the database

//                String ingredientName = "Ingredient " + (ingredients.size() + 1);
//                Ingredient newIngredient = new Ingredient(ingredientName, 0, 0);
//                ingredients.add(newIngredient);
//                Log.i("INFO", "New ingredient added: " + newIngredient.getIngredientName());
//                dbHelper.insertIngredient(newIngredient);

                goToAddEditIngredient();
            }
        });
    }

    public void goToAddEditIngredient() {
        Intent intent = new Intent(this, addEditIngredientActivity.class);
        startActivity(intent);
    }

    public void goToIngredientInfo(String s) {

    }

    //Might not need this function-- depends on how the camera screen is setup
    public void goToCameraMode() {

    }
    // Adding a comment on Line 27 of MainActivity for testing Pushes
    public void goToRestaurantProfile() {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.closeDatabase();
    }

}