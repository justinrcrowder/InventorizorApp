package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EditMemberProfile extends AppCompatActivity {

    TextInputEditText memberNameInput;
    TextInputEditText memberPositionInput;
    TextInputEditText memberNumberInput;
    private String memberName;
    private String memberPosition;
    private String memberPhone;
    private ArrayList<MemberProfile> members;
    private Button confirmButton;
    private Button backButton;

    private int memberIndex;
    private RestaurantManager restaurantManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_member_profile);

        restaurantManager = RestaurantManager.getInstance();

        // Get members from RestaurantManager
        ArrayList<MemberProfile> members = restaurantManager.getMembers();
        memberIndex = getIntent().getIntExtra("memberIndex", -1);
        Log.i("INFO", "Index: " + memberIndex);
        MemberProfile selectedMember = (MemberProfile) getIntent().getSerializableExtra("SelectedMember");

        TextInputEditText memberNameEditText = findViewById(R.id.memberNameEditText);
        TextInputEditText memberPositionEditText = findViewById(R.id.memberPositionEditText);
        TextInputEditText memberPhoneEditText = findViewById(R.id.memberPhoneEditText);

        memberName = selectedMember.getMemberName();
        memberPosition = selectedMember.getMemberPosition();
        memberPhone = selectedMember.getMemberPhone();

        memberNameEditText.setText(memberName);
        memberPositionEditText.setText(memberPosition);
        memberPhoneEditText.setText(memberPhone);


        Button saveMemberButton = findViewById(R.id.saveMemberButton);
        saveMemberButton.setOnClickListener(view -> {
            Log.i("INFO", "Save button pressed");

            memberName = memberNameEditText.getText().toString().trim();

            if (memberName.isEmpty()) {
                showToast("Ingredient name cannot be blank");
                return;
            }

            selectedMember.setMemberName(memberName);
            selectedMember.setMemberPosition(memberPosition);
            selectedMember.setMemberPhone(memberPhone);

            members.set(memberIndex, selectedMember);

            // Update the members in RestaurantManager
            restaurantManager.setMembers(members);

            goToRestaurantProfile();
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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