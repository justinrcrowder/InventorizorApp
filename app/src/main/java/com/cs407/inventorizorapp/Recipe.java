package com.cs407.inventorizorapp;

import java.util.ArrayList;

public class Recipe {
    private String recipeName;
    private String recipeInstructions;
    private ArrayList<String> recipeIngredients;

    public Recipe() {}
    public Recipe(String recipeName, String recipeInstructions, ArrayList<String> recipeIngredients) {
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
    public ArrayList<String> getRecipeIngredients() {
        return recipeIngredients;
    }
}
