package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class addRestaurantActivity extends AppCompatActivity {
    TextInputEditText restaurantNameInput;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantNameInput = findViewById(R.id.nameRestaurant);


        confirmButton = findViewById(R.id.confirmButton);
        findViewById(R.id.confirmButton).setOnClickListener(view -> {
            String restaurantName = restaurantNameInput.getText().toString().trim();
            if (restaurantName.length() > 0) {
                restaurantManager.setRestaurantName(restaurantName);
                goToMainActivity();
            }
        });
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}