package com.cs407.inventorizorapp;

import java.util.ArrayList;

public class RestaurantManager {
    private static RestaurantManager instance;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Recipe> recipes;
    private ArrayList<MemberProfile> members;
    private String restaurantName;

    private RestaurantManager() {
        // Private constructor to prevent instantiation
        ingredients = new ArrayList<>();
        recipes = new ArrayList<>();
        members = new ArrayList<>();
        restaurantName = "";
    }

    public static synchronized RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public ArrayList<Recipe> getRecipes() { return recipes; }
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
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
