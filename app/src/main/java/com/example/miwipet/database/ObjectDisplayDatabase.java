package com.example.miwipet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.ObjectModel;

import java.util.ArrayList;

public class ObjectDisplayDatabase extends SQLiteOpenHelper {
    private static final String id = "id";
    private static final String displayTable = "display_table";
    private static final String objectName = "object_name";

    private static final int DATABASE_VERSION = 1;

    public ObjectDisplayDatabase(@NonNull Context context) {
        super(context, "object_display.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + displayTable + " (" + id + " INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + objectName + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean generateTable(ObjectModel objectModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean result;

        contentValues.put(objectName, objectModel.getObjectName());

        long insert = db.insert(displayTable, null, contentValues);
        if (insert == -1) {
            result = false;
        } else {
            result = true;
        }

        db.close();

        return result;
    }

    public boolean updateDisplay(ObjectModel objectModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(objectName, objectModel.getObjectName());

        String whereClause = id + "=?";
        String[] whereArgs = {String.valueOf(objectModel.getId())};
        int result = db.update(displayTable, contentValues, whereClause, whereArgs);

        db.close();

        return result > 0;
    }

    public ArrayList<String> getDisplayList() {
        ArrayList<String> displayList = new ArrayList<>();
        String queryString = "SELECT * FROM " + displayTable;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String foodName = cursor.getString(1);
                displayList.add(foodName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return displayList;
    }

    public boolean clearDisplay() {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + displayTable;

        boolean result;

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            result = true;
        } else {
            result = false;
        }

        cursor.close();
        db.close();

        return result;
    }

    public boolean doesDataExist() {
        int count = 0;
        String queryString = "SELECT * FROM " + displayTable;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                count++;
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return count > 0;
    }
}
