package com.example.miwipet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.miwipet.models.ObjectModel;

import java.util.ArrayList;

public class ObjectDatabase extends SQLiteOpenHelper {
    private static final String id = "id";
    private static final String objectTable = "object_table";
    private static final String objectImage = "object_image";
    private static final String objectName = "object_name";
    private static final String rarity = "rarity";
    private static final String objectChance = "object_chance";
    private static final String chipPrice = "chip_price";
    private static final String glazePrice = "glaze_price";
    private static final String timeBuff = "time_buff";
    private static final String chipBuff = "chip_buff";
    private static final String glazeBuff = "glaze_buff";

    private static final int DATABASE_VERSION = 1;

    public ObjectDatabase(@Nullable Context context) {
        super(context, "object.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + objectTable + " (" + id + " INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + objectImage + " TEXT, " + objectName + " TEXT, " + rarity + " TEXT, " +
                objectChance + " INT, " + chipPrice + " INT, " + glazePrice + " INT, " + timeBuff + " INT, " +
                chipBuff + " INT, " + glazeBuff + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addObject(ObjectModel objectModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean result;

        contentValues.put(objectImage, objectModel.getObjectImage());
        contentValues.put(objectName, objectModel.getObjectName());
        contentValues.put(rarity, objectModel.getRarity());
        contentValues.put(objectChance, objectModel.getObjectChance());
        contentValues.put(chipPrice, objectModel.getChipPrice());
        contentValues.put(glazePrice, objectModel.getGlazePrice());
        contentValues.put(timeBuff, objectModel.getTimeBuff());
        contentValues.put(chipBuff, objectModel.getChipBuff());
        contentValues.put(glazeBuff, objectModel.getGlazeBuff());

        long insert = db.insert(objectTable, null, contentValues);
        if (insert == -1) {
            result = false;
        } else {
            result = true;
        }

        db.close();

        return result;
    }

    public boolean deleteObject(ObjectModel objectModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + objectTable + " WHERE " + id + " = " + objectModel.getId();

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

    public boolean clearObject() {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result;

        int rowsDeleted = db.delete(objectTable, null, null);
        if (rowsDeleted > 0) {
            result = true;
        } else {
            result = false;
        }

        db.close();
        return result;
    }

    public ArrayList<ObjectModel> getObjectList() {
        ArrayList<ObjectModel> objectModels = new ArrayList<>();
        String queryString = "SELECT * FROM " + objectTable;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String objectImage = cursor.getString(1);
                String objectName = cursor.getString(2);
                String rarity = cursor.getString(3);
                int objectChance = cursor.getInt(4);
                int chipPrice = cursor.getInt(5);
                int glazePrice = cursor.getInt(6);
                int timeBuff = cursor.getInt(7);
                int chipBuff = cursor.getInt(8);
                int glazeBuff = cursor.getInt(9);

                ObjectModel objectModel = new ObjectModel(id, objectImage, objectName, objectChance,
                        chipPrice, glazePrice, timeBuff, chipBuff, glazeBuff);
                objectModel.setRarity(rarity);

                objectModels.add(objectModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return objectModels;
    }
}
