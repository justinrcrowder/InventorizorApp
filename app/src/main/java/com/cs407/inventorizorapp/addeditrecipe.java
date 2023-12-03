package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class addeditrecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addeditrecipe);
    }

    public void goToRestaurantProfile(String s) {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }

}