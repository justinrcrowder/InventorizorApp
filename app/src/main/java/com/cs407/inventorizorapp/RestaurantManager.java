package com.cs407.inventorizorapp;

import java.util.ArrayList;

public class RestaurantManager {
    private static RestaurantManager instance;
    private ArrayList<Ingredient> ingredients;

    private RestaurantManager() {
        // Private constructor to prevent instantiation
        ingredients = new ArrayList<>();
    }

    public static synchronized RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
