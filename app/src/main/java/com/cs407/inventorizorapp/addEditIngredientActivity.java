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

import java.util.ArrayList;

public class addEditIngredientActivity extends AppCompatActivity {
    Button saveIngredientButton;
    public static ArrayList<Ingredient> ingredients;
    DBHelper dbHelper;
    int currAmountOwned = 0;
    int currTargetAmount = 0;
    private TextView amountOwnedText;
    private TextView targetAmountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("restaurant", Context.MODE_PRIVATE, null);
        dbHelper = new DBHelper(sqLiteDatabase);
        ingredients = dbHelper.readIngredients();

        saveIngredientButton = findViewById(R.id.saveIngredientButton);
        saveIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("INFO", "Save button pressed");
                String ingredientName = "Ingredient " + (ingredients.size() + 1);
                Ingredient newIngredient = new Ingredient(ingredientName, 0, 0);
                ingredients.add(newIngredient);
                Log.i("INFO", "New ingredient added: " + newIngredient.getIngredientName());
                dbHelper.insertIngredient(newIngredient);
                goToMainActivity();
            }
        });

        // Code for incrementing and decrementing values
        amountOwnedText = findViewById(R.id.amountOwned);
        targetAmountText = findViewById(R.id.targetAmount);

        ImageButton incrAmountOwnedButton = findViewById(R.id.itemIncrementButton1);
        incrAmountOwnedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currAmountOwned++;
                amountOwnedText.setText(String.valueOf(currAmountOwned));
            }
        });
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void decrAmountOwned() {
        currAmountOwned--;
        amountOwnedText.setText(String.valueOf(currAmountOwned));
    }

    public void incrTargetAmount() {
        currTargetAmount++;
        targetAmountText.setText(String.valueOf(currTargetAmount));
    }

    public void decrTargetAmount() {
        currTargetAmount--;
        targetAmountText.setText(String.valueOf(currTargetAmount));
    }
}