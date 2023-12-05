package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class DBTesting extends AppCompatActivity {
    public static ArrayList<Ingredient> ingredients;
    Button testAddIngredientButton;
    Button testDeleteIngredientsButton;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtesting);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("restaurant", Context.MODE_PRIVATE, null);
        dbHelper = new DBHelper(sqLiteDatabase);
        ingredients = dbHelper.readIngredients();

        testAddIngredientButton = findViewById(R.id.testAddIngredientButton);
        testAddIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("INFO", "User tapped button!");
//                Intent intent = new Intent(getApplicationContext(), addEditIngredientActivity.class);
//                startActivity(intent);
                String ingredientName = "Ingredient " + (ingredients.size() + 1);
                Ingredient newIngredient = new Ingredient(ingredientName, 0, 0);
                ingredients.add(newIngredient);
                Log.i("INFO", "New ingredient added: " + newIngredient.getIngredientName());
                dbHelper.insertIngredient(newIngredient);
            }
        });

        testDeleteIngredientsButton = findViewById(R.id.testDeleteIngredientButton);
        testDeleteIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredients.clear();
                Log.i("INFO", "All ingredients deleted");
                dbHelper.deleteAllIngredients();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.closeDatabase();
    }
}