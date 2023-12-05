package com.cs407.inventorizorapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBHelper {
    static SQLiteDatabase sqLiteDatabase;

    public DBHelper(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public static void createTable() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS restaurant " + "(id INTEGER PRIMARY KEY, ingredientName TEXT, amountOwned INTEGER, targetAmount INTEGER)");
    }

    public ArrayList<Ingredient> readIngredients() {
        createTable();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM restaurant ", null);
        int ingredientNameIndex = c.getColumnIndex("ingredientName");
        int amountOwnedIndex = c.getColumnIndex("amountOwned");
        int targetAmountIndex = c.getColumnIndex("targetAmount");
        c.moveToFirst();
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        while (!c.isAfterLast()) {
            String ingredientName = c.getString(ingredientNameIndex);
            int amountOwned = Integer.parseInt(c.getString(amountOwnedIndex));
            int targetAmount = Integer.parseInt(c.getString(targetAmountIndex));

            Ingredient ingredient = new Ingredient(ingredientName, amountOwned, targetAmount);
            ingredientList.add(ingredient);
            c.moveToNext();
        }
        c.close();
        return ingredientList;
    }

    public void insertIngredient(Ingredient ingredient) {
        createTable();
        sqLiteDatabase.execSQL("INSERT INTO restaurant (ingredientName, amountOwned, targetAmount) VALUES (?, ?, ?)",
                new Object[]{ingredient.getIngredientName(), ingredient.getAmountOwned(), ingredient.getTargetAmount()});
    }

    public void deleteAllIngredients() {
        sqLiteDatabase.execSQL("DELETE FROM restaurant");
    }

    public void closeDatabase() {
        sqLiteDatabase.close();
    }

}
