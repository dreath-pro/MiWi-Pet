package com.example.miwipet.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class PetDatabase extends SQLiteOpenHelper {
    public static final String id = "id";
    public static final String petTable = "pet_table";
    public static final String image = "image";
    public static final String name = "name";
    public static final String rarity = "rarity";
    public static final String age = "age";
    public static final String type = "type";
    public static final String maxExp = "max_exp";
    public static final String exp = "exp";

    public PetDatabase(@Nullable Context context) {
        super(context, "pet.db", null, 1);
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

    }

    public boolean addPet(PetModel petModel)
    {
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
        if(insert == -1)
        {
            result = false;
        }else
        {
            result = true;
        }

        db.close();

        return  result;
    }

    public ArrayList<PetModel> getPetList()
    {
        ArrayList<PetModel> petModels = new ArrayList<>();
        String queryString = "SELECT * FROM " + petTable;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            do
            {
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
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return petModels;
    }
}
