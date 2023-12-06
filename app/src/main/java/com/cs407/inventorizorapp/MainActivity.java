package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}