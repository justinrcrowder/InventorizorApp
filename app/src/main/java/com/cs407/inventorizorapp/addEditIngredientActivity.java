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
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class addEditIngredientActivity extends AppCompatActivity {
    Button saveIngredientButton;
    public static ArrayList<Ingredient> ingredients;
    private String ingredientName;
    private int amountOwned;
    private int targetAmount;


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

        TextInputEditText ingredientNameEditText = findViewById(R.id.ingredientNameEditText);
        TextView amountOwnedText = findViewById(R.id.amountOwnedText);
        TextView targetAmountText = findViewById(R.id.targetAmountText);

        ingredientName = "";
        amountOwned = 5;
        targetAmount = 5;

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


//        uncomment code if switching back to database
//        Context context = getApplicationContext();
//        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("restaurant", Context.MODE_PRIVATE, null);
//        dbHelper = new DBHelper(sqLiteDatabase);
//        ingredients = dbHelper.readIngredients();

        saveIngredientButton = findViewById(R.id.saveIngredientButton);
        saveIngredientButton.setOnClickListener(view -> {
            Log.i("INFO", "Save button pressed");
            if (ingredients != null) {
                ingredientName = ingredientNameEditText.getText().toString();
                Ingredient newIngredient = new Ingredient(ingredientName, targetAmount, amountOwned);
                ingredients.add(newIngredient);
                Log.i("INFO", "New ingredient added: " + newIngredient.getIngredientName());
//                dbHelper.insertIngredient(newIngredient);
            } else {
                ingredients = new ArrayList<>();
                ingredientName = ingredientNameEditText.getText().toString();
                Ingredient newIngredient = new Ingredient(ingredientName, targetAmount, amountOwned);
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