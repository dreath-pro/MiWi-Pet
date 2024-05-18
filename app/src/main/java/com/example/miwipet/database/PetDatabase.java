package com.example.miwipet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class PetDatabase extends SQLiteOpenHelper {
    private static final String id = "id";
    private static final String petTable = "pet_table";
    private static final String image = "image";
    private static final String name = "name";
    private static final String rarity = "rarity";
    private static final String age = "age";
    private static final String type = "type";
    private static final String maxExp = "max_exp";
    private static final String exp = "exp";

    private static final int DATABASE_VERSION = 4;

    public PetDatabase(@Nullable Context context) {
        super(context, "pet.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + petTable + " (" + id + " INTEGER PRIMARY KEY " +
                "AUTOINCREMENT," + image + " INT, " + name + " TEXT, " + rarity + " TEXT, " + age + " INT, " +
                type + " INT, " + maxExp + " INT, " + exp + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < DATABASE_VERSION)
        {
            // Rename the old table
            db.execSQL("ALTER TABLE " + petTable + " RENAME TO " + petTable + "_temp");

            // Create the new table with the modified column data type
            String createTableStatement = "CREATE TABLE " + petTable + " (" + id + " INTEGER PRIMARY KEY " +
                    "AUTOINCREMENT," + image + " TEXT, " + name + " TEXT, " + rarity + " TEXT, " + age + " INT, " +
                    type + " INT, " + maxExp + " INT, " + exp + " INT)";
            db.execSQL(createTableStatement);

            // Copy data from the temporary table to the new table
            db.execSQL("INSERT INTO " + petTable + " (" + id + ", " + name + ", " + rarity + ", " + age + ", " +
                    type + ", " + maxExp + ", " + exp + ") SELECT " + id + ", " + name + ", " + rarity + ", " +
                    age + ", " + type + ", " + maxExp + ", " + exp + " FROM " + petTable + "_temp");

            // Drop the temporary table
            db.execSQL("DROP TABLE IF EXISTS " + petTable + "_temp");
        }

        if(oldVersion < DATABASE_VERSION)
        {
            // Revert back from version 3 to version 2
            // Rename the current table
            db.execSQL("ALTER TABLE " + petTable + " RENAME TO " + petTable + "_temp");

            // Create the new table with the previous schema
            String createTableStatement = "CREATE TABLE " + petTable + " (" + id + " INTEGER PRIMARY KEY " +
                    "AUTOINCREMENT," + image + " INT, " + name + " TEXT, " + rarity + " TEXT, " + age + " INT, " +
                    type + " INT, " + maxExp + " INT, " + exp + " INT)";
            db.execSQL(createTableStatement);

            // Copy data from the temporary table to the new table
            db.execSQL("INSERT INTO " + petTable + " (" + id + ", " + name + ", " + rarity + ", " + age + ", " +
                    type + ", " + maxExp + ", " + exp + ") SELECT " + id + ", " + name + ", " + rarity + ", " +
                    age + ", " + type + ", " + maxExp + ", " + exp + " FROM " + petTable + "_temp");

            // Drop the temporary table
            db.execSQL("DROP TABLE IF EXISTS " + petTable + "_temp");
        }

        db.setVersion(DATABASE_VERSION);
    }

    public boolean addPet(PetModel petModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean result;

        contentValues.put(image, petModel.getPetImage());
        contentValues.put(name, petModel.getPetName());
        contentValues.put(rarity, petModel.getRarity());
        contentValues.put(age, petModel.getAge());
        contentValues.put(type, petModel.getType());
        contentValues.put(maxExp, petModel.getMaxExp());
        contentValues.put(exp, petModel.getExp());

        long insert = db.insert(petTable, null, contentValues);
        if (insert == -1) {
            result = false;
        } else {
            result = true;
        }

        db.close();

        return result;
    }

    public boolean clearPet() {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result;

        int rowsDeleted = db.delete(petTable, null, null);
        if (rowsDeleted > 0) {
            result = true;
        } else {
            result = false;
        }

        db.close();
        return result;
    }

    public ArrayList<PetModel> getPetList() {
        ArrayList<PetModel> petModels = new ArrayList<>();
        String queryString = "SELECT * FROM " + petTable;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int image = cursor.getInt(1);
                String name = cursor.getString(2);
                String rarity = cursor.getString(3);
                int age = cursor.getInt(4);
                int type = cursor.getInt(5);
                int maxExp = cursor.getInt(6);
                int exp = cursor.getInt(7);

                PetModel petModel = new PetModel(name, image, age, type, rarity, maxExp, exp);
                petModels.add(petModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return petModels;
    }
}
