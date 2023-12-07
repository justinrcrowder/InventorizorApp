package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddEditMemberProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_member_profile);
    }

    public void goToRestaurantProfile(View view) {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent); // finish()?
    }

    public void goBackToRestaurantProfile(View view) {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }
}