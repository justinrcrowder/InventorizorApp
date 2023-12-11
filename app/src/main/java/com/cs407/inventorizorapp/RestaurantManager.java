package com.cs407.inventorizorapp;

import java.util.ArrayList;

public class RestaurantManager {
    private static RestaurantManager instance;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Recipe> recipes;
    private ArrayList<MemberProfile> members;

    private RestaurantManager() {
        // Private constructor to prevent instantiation
        ingredients = new ArrayList<>();
        recipes = new ArrayList<>();
    }

    public static synchronized RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<MemberProfile> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<MemberProfile> members) {
        this.members = members;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}