package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class addEditIngredientActivity extends AppCompatActivity {
    Button saveIngredientButton;
    public static ArrayList<Ingredient> ingredients;

//    uncomment if switching back to database
//    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);
        ingredients = new ArrayList<>();

//        fill ingredients from intent if exists
        if (getIntent() != null) {
            Log.i("INFO", "There are existing ingredients!");
            ingredients = (ArrayList<Ingredient>) getIntent().getSerializableExtra("Ingredient_list");
        } else {
            Log.i("INFO", "No ingredients were passed over...");
        }

//        uncomment code if switching back to database
//        Context context = getApplicationContext();
//        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("restaurant", Context.MODE_PRIVATE, null);
//        dbHelper = new DBHelper(sqLiteDatabase);
//        ingredients = dbHelper.readIngredients();

        saveIngredientButton = findViewById(R.id.saveIngredientButton);
        saveIngredientButton.setOnClickListener(view -> {
            Log.i("INFO", "Save button pressed");
            if (ingredients != null) {
                String ingredientName = "Ingredient " + (ingredients.size() + 1);
                Ingredient newIngredient = new Ingredient(ingredientName, 0, 0);
                ingredients.add(newIngredient);
                Log.i("INFO", "New ingredient added: " + newIngredient.getIngredientName());
//                dbHelper.insertIngredient(newIngredient);
            } else {
                ingredients = new ArrayList<>();
                String ingredientName = "Ingredient " + (ingredients.size() + 1);
                Ingredient newIngredient = new Ingredient(ingredientName, 0, 0);
                ingredients.add(newIngredient);
                Log.i("INFO", "New ingredient added: " + newIngredient.getIngredientName());
            }
            goToMainActivity();
        });
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Ingredient_list", ingredients);
        startActivity(intent);
    }
}