package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class addRestaurantActivity extends AppCompatActivity {
    TextInputEditText restaurantNameInput;
    TextInputEditText memberNameInput;
    TextInputEditText memberPositionInput;
    TextInputEditText memberNumberInput;
    private ArrayList<MemberProfile> members;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantNameInput = findViewById(R.id.nameRestaurant);
        memberNameInput = findViewById(R.id.memberName);
        memberPositionInput = findViewById(R.id.memberPosition);
        memberNumberInput = findViewById(R.id.memberNumber);

        confirmButton = findViewById(R.id.confirmButton);

        findViewById(R.id.confirmButton).setOnClickListener(view -> {
            String restaurantName = restaurantNameInput.getText().toString().trim();
            String memberName = memberNameInput.getText().toString().trim();
            String memberPosition = memberPositionInput.getText().toString().trim();
            String memberNumber = memberNumberInput.getText().toString().trim();
            if (restaurantName.length() > 0) {
                restaurantManager.setRestaurantName(restaurantName);
                members = new ArrayList<MemberProfile>();
                members.add(new MemberProfile(memberName, memberPosition, memberNumber));
                restaurantManager.setMembers(members);
                goToMainActivity();
            }
        });
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}