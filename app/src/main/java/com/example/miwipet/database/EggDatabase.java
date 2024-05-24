package com.example.miwipet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.miwipet.models.EggModel;

import java.util.ArrayList;

public class EggDatabase extends SQLiteOpenHelper {
    private static final String id = "id";
    private static final String eggTable = "egg_table";
    private static final String eggImage = "egg_image";
    private static final String eggName = "egg_name";
    private static final String petName = "pet_name";
    private static final String petImage = "pet_image";
    private static final String petAge = "pet_age";
    private static final String petType = "pet_type";
    private static final String chipPrice = "chip_price";
    private static final String glazePrice = "glaze_price";
    private static final String second = "second";
    private static final String minute = "minute";
    private static final String hour = "hour";
    private static final String toHatch = "to_hatch";
    private static final String isSelected = "is_selected";
    private static final String rarityText = "rarity_text";
    private static final String eggPercentage = "egg_percentage";

    private static final int DATABASE_VERSION = 7;

    public EggDatabase(@Nullable Context context) {
        super(context, "egg.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + eggTable + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                eggImage + " TEXT, " + eggName + " TEXT, " + petName + " TEXT, " + petImage + " TEXT, " + petAge + " INT, " +
                petType + " INT, " + chipPrice + " INT, " + glazePrice + " INT, " + second + " INT, " + minute + " INT, " +
                hour + " INT, " + toHatch + " BOOL, " + isSelected + " BOOL, " + rarityText + " String, " + eggPercentage + ")";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < DATABASE_VERSION)
        {
            boolean columnExists = false;
            Cursor cursor = db.rawQuery("PRAGMA table_info(" + eggTable + ")", null);
            if(cursor != null)
            {
                while(cursor.moveToNext())
                {
                    String columnName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    if(columnName.equals(eggPercentage))
                    {
                        columnExists = true;
                        break;
                    }
                }
                cursor.close();
            }

            if(!columnExists)
            {
                db.execSQL("ALTER TABLE " + eggTable + " ADD COLUMN " + eggPercentage + " INT");
            }


            db.execSQL("ALTER TABLE " + eggTable + " RENAME TO " + eggTable + "_temp");

            String createTableStatement = "CREATE TABLE " + eggTable + " (" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    eggImage + " TEXT, " + eggName + " TEXT, " + petName + " TEXT, " + petImage + " TEXT, " + petAge + " INT, " +
                    petType + " INT, " + chipPrice + " INT, " + glazePrice + " INT, " + second + " INT, " + minute + " INT, " +
                    hour + " INT, " + toHatch + " BOOL, " + isSelected + " BOOL, " + rarityText + " String, " + eggPercentage + ")";
            db.execSQL(createTableStatement);

            db.execSQL("INSERT INTO " + eggTable + " (" + id + ", " + eggImage + ", " + eggName + ", " + petName + ", " +
                    petImage + ", " + petAge + ", " + petType + ", " + chipPrice + ", " + glazePrice + ", " + second + ", " +
                    minute + ", " + hour + ", " + toHatch + ", " + isSelected + ", " + rarityText + ", " + eggPercentage + ") " +
                    "SELECT " + id + ", " + eggImage + ", " + eggName + ", " + petName + ", " + petImage + ", " + petAge + ", " +
                    petType + ", " + chipPrice + ", " + glazePrice + ", " + second + ", " + minute + ", " + hour + ", " +
                    toHatch + ",  " + isSelected + ", " + rarityText + ", " + eggPercentage + " FROM " + eggTable + "_temp");

            db.execSQL("DROP TABLE IF EXISTS " + eggTable + "_temp");
        }

        db.setVersion(DATABASE_VERSION);
    }

    public boolean addEgg(EggModel eggModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean result;

        contentValues.put(eggImage, eggModel.getEggImage());
        contentValues.put(eggName, eggModel.getEggName());
        contentValues.put(petName, eggModel.getPetName());
        contentValues.put(petImage, eggModel.getPetImage());
        contentValues.put(petAge, eggModel.getAge());
        contentValues.put(petType, eggModel.getType());
        contentValues.put(chipPrice, eggModel.getChipPrice());
        contentValues.put(glazePrice, eggModel.getGlazePrice());
        contentValues.put(second, eggModel.getSecond());
        contentValues.put(minute, eggModel.getMinute());
        contentValues.put(hour, eggModel.getHour());
        contentValues.put(toHatch, eggModel.isToHatch());
        contentValues.put(isSelected, eggModel.isSelected());
        contentValues.put(rarityText, eggModel.getRarityText());
        contentValues.put(eggPercentage, eggModel.getEggPercentage());

        long insert = db.insert(eggTable, null, contentValues);
        if (insert == -1) {
            result = false;
        } else {
            result = true;
        }

        db.close();

        return result;
    }

    public boolean deleteEgg(EggModel eggModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + eggTable + " WHERE " + id + " = " + eggModel.getId();

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

    public boolean clearEgg() {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result;

        int rowsDeleted = db.delete(eggTable, null, null);
        if (rowsDeleted > 0) {
            result = true;
        } else {
            result = false;
        }

        db.close();
        return result;
    }

    public ArrayList<EggModel> getEggList() {
        ArrayList<EggModel> eggModels = new ArrayList<>();
        String queryString = "SELECT * FROM " + eggTable;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String eggImage = cursor.getString(1);
                String eggName = cursor.getString(2);
                String petName = cursor.getString(3);
                String petImage = cursor.getString(4);
                int petAge = cursor.getInt(5);
                int petType = cursor.getInt(6);
                int chipPrice = cursor.getInt(7);
                int glazePrice = cursor.getInt(8);
                int second = cursor.getInt(9);
                int minute = cursor.getInt(10);
                int hour = cursor.getInt(11);
                boolean toHatch = cursor.getInt(12) == 1;
                boolean isSelecsted = cursor.getInt(13) == 1;
                String rarityText = cursor.getString(14);
                int eggPercentage = cursor.getInt(15);

                EggModel eggModel = new EggModel(id, eggName, eggImage, petName, petImage, petAge,
                        petType, chipPrice, glazePrice, second, minute, hour, toHatch, isSelecsted,
                        rarityText, eggPercentage);

                eggModels.add(eggModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return eggModels;
    }
}
