package com.example.miwipet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.miwipet.models.TimeModel;

public class TimeDatabase extends SQLiteOpenHelper {
    private static final String id = "id";
    private static final String timeTable = "time_table";
    private static final String lastTimeLogin = "last_time_login";
    private static final String lastDayLogin = "last_day_login";
    private static final String lastMonthLogin = "last_month_login";
    private static final String lastYearLogin = "last_year_login";
    private static final String loginStreak = "login_streak";
    private static final String loggedIn = "logged_in";

    private static final int DATABASE_VERSION = 1;

    public TimeDatabase(@NonNull Context context) {
        super(context, "time.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + timeTable + " (" + id + " INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + lastTimeLogin + " TEXT, " + lastDayLogin + " TEXT, " + lastMonthLogin +
                " TEXT, " + lastYearLogin + " TEXT, " + loginStreak + " INT, " + loggedIn + " BOOL)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean generateTable(TimeModel timeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        boolean result;

        contentValues.put(lastTimeLogin, timeModel.getLastTimeLogin());
        contentValues.put(lastDayLogin, timeModel.getLastDayLogin());
        contentValues.put(lastMonthLogin, timeModel.getLastMonthLogin());
        contentValues.put(lastYearLogin, timeModel.getLastYearLogin());
        contentValues.put(loginStreak, timeModel.getLoginStreak());
        contentValues.put(loggedIn, timeModel.isLoggedIn());

        long insert = db.insert(timeTable, null, contentValues);
        if (insert == -1) {
            result = false;
        } else {
            result = true;
        }

        db.close();

        return result;
    }

    public boolean updateTime(TimeModel timeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(lastTimeLogin, timeModel.getLastTimeLogin());
        contentValues.put(lastDayLogin, timeModel.getLastDayLogin());
        contentValues.put(lastMonthLogin, timeModel.getLastMonthLogin());
        contentValues.put(lastYearLogin, timeModel.getLastYearLogin());
        contentValues.put(loginStreak, timeModel.getLoginStreak());
        contentValues.put(loggedIn, timeModel.isLoggedIn());

        String whereClause = id + "=?";
        String[] whereArgs = {String.valueOf(timeModel.getId())};
        int result = db.update(timeTable, contentValues, whereClause, whereArgs);

        return result > 0;
    }

    public TimeModel getTimeRecord() {
        TimeModel timeModel = null;
        String queryString = "SELECT * FROM " + timeTable;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String lastTimeLogin = cursor.getString(1);
                String lastDayLogin = cursor.getString(2);
                String lastMonthLogin = cursor.getString(3);
                String lastYearLogin = cursor.getString(4);
                int loginStreak = cursor.getInt(5);
                boolean rewardRefreshed = cursor.getInt(6) == 1;

                timeModel = new TimeModel(id, lastTimeLogin, lastDayLogin, lastMonthLogin, lastYearLogin, loginStreak, rewardRefreshed);
            } while (cursor.moveToNext());
        }

        return timeModel;
    }

    public boolean doesDataExist() {
        int count = 0;
        String queryString = "SELECT * FROM " + timeTable;
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
