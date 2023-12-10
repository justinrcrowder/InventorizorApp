package com.cs407.inventorizorapp;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String ingredientName;
    private int amountOwned;
    private int targetAmount;
//    private photo?

    public Ingredient(String ingredientName, int amountOwned, int targetAmount) {
        this.ingredientName = ingredientName;
        this.amountOwned = amountOwned;
        this.targetAmount = targetAmount;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setAmountOwned(int amountOwned) {
        this.amountOwned = amountOwned;
    }

    public void setTargetAmount(int targetAmount) {
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
