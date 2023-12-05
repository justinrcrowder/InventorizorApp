package com.cs407.inventorizorapp;

import java.util.ArrayList;

public class RestaurantProfile {
    private String restaurantName;
    private ArrayList<MemberProfile> restaurantMembers;

    //    private photo?
    public RestaurantProfile(String restaurantName, ArrayList<MemberProfile> restaurantMembers) {
        this.restaurantName = restaurantName;
        this.restaurantMembers = restaurantMembers;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public ArrayList<MemberProfile> getRestaurantMembers() {
        return restaurantMembers;
    }
}
