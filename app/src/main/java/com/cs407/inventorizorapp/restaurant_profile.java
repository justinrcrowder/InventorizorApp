package com.cs407.inventorizorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class restaurant_profile extends AppCompatActivity {

    private TextView restaurantNameTextView;
    private ArrayList<MemberProfile> members;
    private ArrayList<String> displayMembers;
    private ListView membersList;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        members = restaurantManager.getMembers();

        restaurantNameTextView = findViewById(R.id.restaurantName);
        restaurantNameTextView.setText(restaurantManager.getRestaurantName());

        displayMembers = new ArrayList<>();
        for (MemberProfile member : members) {
            displayMembers.add(String.format("%s\n%s", member.getMemberName(), member.getMemberPosition()));
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayMembers);
        ListView membersList = findViewById(R.id.membersListView);
        membersList.setAdapter(adapter);

        membersList.setOnItemClickListener((adapterView, view, memberIndex, l) -> {
            MemberProfile selectedMember = members.get(memberIndex);
            goToEditMemberInfo(selectedMember, memberIndex);
        });


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

    public void goToAddMemberProfile(View view) {
        Intent intent = new Intent(this, AddEditMemberProfile.class);
        startActivity(intent);
    }

    public void goToEditMemberInfo(MemberProfile member, int index) {
        Intent intent = new Intent(getApplicationContext(), EditMemberProfile.class);
        intent.putExtra("SelectedMember", member);
        intent.putExtra("memberIndex", index);
        Log.i("INFO", index + " " + member.getMemberName());
        startActivity(intent);
    }

    public void goToRecipeList(View view) {
        Intent intent = new Intent(this, recipesListActivity.class);
        startActivity(intent);
    }
}