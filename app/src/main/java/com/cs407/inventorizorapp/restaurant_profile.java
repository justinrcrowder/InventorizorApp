package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class restaurant_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
    }

    //Might end up needing two activities for Adding a recipe and Editing a recipe
    public void goToAddEditRecipe(View view) {
        Intent intent = new Intent(this, addRecipeActivity.class);
        startActivity(intent);
    }

    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToAddEditMemberProfile(View view) {
        Intent intent = new Intent(this, AddEditMemberProfile.class);
        startActivity(intent);
    }

    public void goToRecipeList(View view) {
        Intent intent = new Intent(this, recipesListActivity.class);
        startActivity(intent);
    }
}