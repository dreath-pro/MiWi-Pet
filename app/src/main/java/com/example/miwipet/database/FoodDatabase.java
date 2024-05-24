package com.example.miwipet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.miwipet.models.FoodModel;

import java.util.ArrayList;

public class FoodDatabase extends SQLiteOpenHelper {
    private static final String id = "id";
    private static final String foodTable = "food_table";
    private static final String foodImage = "food_image";
    private static final String foodName = "food_name";
    private static final String rarity = "rarity";
    private static final String expReward = "exp_reward";
    private static final String foodPercentage = "food_percentage";
    private static final String chipPrice = "chip_price";
    private static final String glazePrice = "glaze_price";

    private static final int DATABASE_VERSION = 2;

    public FoodDatabase(@Nullable Context context) {
        super(context, "food.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + foodTable + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                foodImage + " TEXT, " + foodName + " TEXT, " + rarity + " TEXT, " + expReward + " INT, " +
                foodPercentage + " INT, " + chipPrice + " INT, " + glazePrice + " INT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < DATABASE_VERSION)
        {
            boolean columnExists = false;
            Cursor cursor = db.rawQuery("PRAGMA table_info(" + foodTable + ")", null);
            if(cursor != null)
            {
                while(cursor.moveToNext())
                {
                    String columnName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    if(columnName.equals(rarity))
                    {
                        columnExists = true;
                        break;
                    }
                }
                cursor.close();
            }

            if(!columnExists)
            {
                db.execSQL("ALTER TABLE " + foodTable + " ADD COLUMN " + rarity + " TEXT");
            }


            db.execSQL("ALTER TABLE " + foodTable + " RENAME TO " + foodTable + "_temp");

            String createTableStatement = "CREATE TABLE " + foodTable + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    foodImage + " TEXT, " + foodName + " TEXT, " + rarity + " TEXT, " + expReward + " INT, " +
                    foodPercentage + " INT, " + chipPrice + " INT, " + glazePrice + " INT)";
            db.execSQL(createTableStatement);

            db.execSQL("INSERT INTO " + foodTable + " (" + id + ", " + foodImage + ", " + foodName + ", " + rarity + ", " +
                    expReward + ", " + foodPercentage + ", " + chipPrice + ", " + glazePrice + ") SELECT " + id + ", " +
                    foodImage + ", " + foodName + ", " + rarity + ", " + expReward + ", " + foodPercentage + ", " +
                    chipPrice + ", " + glazePrice + " FROM " + foodTable + "_temp");

            db.execSQL("DROP TABLE IF EXISTS " + foodTable + "_temp");
        }

        db.setVersion(DATABASE_VERSION);
    }

    public boolean addFood(FoodModel foodModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean result;

        contentValues.put(foodImage, foodModel.getFoodImage());
        contentValues.put(foodName, foodModel.getFoodName());
        contentValues.put(rarity, foodModel.getRarity());
        contentValues.put(expReward, foodModel.getExpReward());
        contentValues.put(foodPercentage, foodModel.getFoodPercentage());
        contentValues.put(chipPrice, foodModel.getChipPrice());
        contentValues.put(glazePrice, foodModel.getGlazePrice());

        long insert = db.insert(foodTable, null, contentValues);
        if (insert == -1) {
            result = false;
        } else {
            result = true;
        }

        db.close();

        return result;
    }

    public boolean deleteFood(FoodModel foodModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + foodTable + " WHERE " + id + " = " + foodModel.getId();

        boolean result;

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            result = true;
        } else {
            result = false;
        }

        db.close();

        return result;
    }

    public boolean clearFood() {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result;

        int rowsDeleted = db.delete(foodTable, null, null);
        if (rowsDeleted > 0) {
            result = true;
        } else {
            result = false;
        }

        db.close();
        return result;
    }

    public ArrayList<FoodModel> getFoodList() {
        ArrayList<FoodModel> foodModels = new ArrayList<>();
        String queryString  = "SELECT * FROM " + foodTable;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String foodImage = cursor.getString(1);
                String foodName = cursor.getString(2);
                String rarity = cursor.getString(3);
                int expReward = cursor.getInt(4);
                int foodPercentage = cursor.getInt(5);
                int chipPrice = cursor.getInt(6);
                int glazePrice = cursor.getInt(7);

                FoodModel foodModel = new FoodModel(id, foodImage, foodName, expReward, foodPercentage, chipPrice, glazePrice);
                foodModel.setRarity(rarity);

                foodModels.add(foodModel);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return foodModels;
    }
}
