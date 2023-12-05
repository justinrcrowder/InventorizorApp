package com.cs407.inventorizorapp;

import java.util.ArrayList;

public class Recipe {
    private String recipeName;
    private String recipeInstructions;
    private ArrayList<Ingredient> recipeIngredients;

    public Recipe(String recipeName, String recipeInstructions, ArrayList<Ingredient> recipeIngredients) {
        this.recipeName = recipeName;
        this.recipeInstructions = recipeInstructions;
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeName() {
        return recipeName;
    }
    public String getRecipeInstructions() {
        return recipeInstructions;
    }
    public ArrayList<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }
}
