package com.example.miwipet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.miwipet.models.InventoryModel;

import java.util.ArrayList;

public class CurrencyDatabase extends SQLiteOpenHelper {
    private static final String id = "id";
    private static final String currencyTable = "currency_table";
    private static final String chipToken = "chip_token";
    private static final String glazeToken = "glaze_token";

    public CurrencyDatabase(@NonNull Context context) {
        super(context, "currency.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + currencyTable + " (" + id + " INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + chipToken + " INT, " + glazeToken + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean generateTokens() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean result;

        contentValues.put(chipToken, 0);
        contentValues.put(glazeToken, 0);

        long insert = db.insert(currencyTable, null, contentValues);
        if (insert == -1) {
            result = false;
        } else {
            result = true;
        }

        db.close();

        return result;
    }

    public boolean updateToken(InventoryModel inventoryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(chipToken, inventoryModel.getChipToken());
        contentValues.put(glazeToken, inventoryModel.getGlazeToken());

        String whereClause = id + "=?";

        String[] whereArgs = {String.valueOf(1)};

        int result = db.update(currencyTable, contentValues, whereClause, whereArgs);

        return result > 0;
    }

    public boolean doesDataExist() {
        int count = 0;
        String queryString = "SELECT * FROM " + currencyTable;
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

    public int getChipToken() {
        String queryString = "SELECT * FROM " + currencyTable;
        SQLiteDatabase db = this.getWritableDatabase();
        int result = 0;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(1);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return result;
    }

    public int getGlazeToken() {
        String queryString = "SELECT * FROM " + currencyTable;
        SQLiteDatabase db = this.getWritableDatabase();
        int result = 0;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                result = cursor.getInt(2);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return result;
    }
}
