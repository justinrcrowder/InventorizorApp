package com.cs407.inventorizorapp;

import java.util.ArrayList;

public class restaurantManager {
    private static restaurantManager instance;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Recipe> recipes;

    private restaurantManager() {
        // Private constructor to prevent instantiation
        ingredients = new ArrayList<>();
        recipes = new ArrayList<>();
    }

    public static synchronized restaurantManager getInstance() {
        if (instance == null) {
            instance = new restaurantManager();
        }
        return instance;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Recipe> getRecipes() { return recipes; }
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
}
