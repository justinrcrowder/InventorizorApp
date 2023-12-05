package com.cs407.inventorizorapp;

public class Ingredient {
    private String ingredientName;
    private int amountOwned;
    private int targetAmount;
//    private photo?

    public Ingredient(String ingredientName, int amountOwned, int targetAmount) {
        this.ingredientName = ingredientName;
        this.amountOwned = amountOwned;
        this.targetAmount = targetAmount;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public int getAmountOwned() {
        return amountOwned;
    }

    public int getTargetAmount() {
        return targetAmount;
    }
}
