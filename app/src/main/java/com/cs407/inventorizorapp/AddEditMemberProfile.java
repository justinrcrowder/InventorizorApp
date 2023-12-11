package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddEditMemberProfile extends AppCompatActivity {

    TextInputEditText memberNameInput;
    TextInputEditText memberPositionInput;
    TextInputEditText memberNumberInput;
    private ArrayList<MemberProfile> members;
    private Button confirmButton;
    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_member_profile);

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        members = restaurantManager.getMembers();

        memberNameInput = findViewById(R.id.memberNameEditText);
        memberPositionInput = findViewById(R.id.memberPositionEditText);
        memberNumberInput = findViewById(R.id.memberPhoneEditText);

        findViewById(R.id.saveMemberButton).setOnClickListener(view -> {
            Log.i("INFO", "Save button pressed");

            String memberName = memberNameInput.getText().toString().trim();
            String memberPosition = memberPositionInput.getText().toString().trim();
            String memberNumber = memberNumberInput.getText().toString().trim();
            Log.i("INFO", "Name: " + memberName + " Position: " + memberPosition + " Number: " + memberNumber);
            if (memberName.length() > 0 && memberPosition.length() > 0) {
                MemberProfile newMember = new MemberProfile(memberName, memberPosition, memberNumber);
                members.add(newMember);
                restaurantManager.setMembers(members);
                Log.i("INFO", members.toString());
                goToRestaurantProfile();
            }
        });

        findViewById(R.id.backButton).setOnClickListener(v -> {
            goBackToRestaurantProfile();
        });


    }

    public void goToRestaurantProfile() {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }

    public void goBackToRestaurantProfile() {
        Intent intent = new Intent(this, restaurant_profile.class);
        startActivity(intent);
    }
}