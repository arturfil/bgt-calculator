package com.arturofilio.bo_g.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "bog.db";
    private static final String TABLE_NAME = "budgets_table";
    public static final String COL0 = "ID";
    public static final String COL1 = "MONTHLY_BUDGET";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + "DOUBLE) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Save Monthly Budget;
     *
     */
    public boolean addBudget(double monthly_budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, monthly_budget);

        Log.d(TAG, "addBudget: Adding " + monthly_budget + " to " + TABLE_NAME);

        double result = db.insert(TABLE_NAME, null, contentValues);

        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getPayment() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPaymentID(Double payment) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + payment + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateMonthlyBudget(Double newBudgt, int id, Double oldBudgt) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 +
                " = '" + newBudgt + "' WHERE " + COL0 + " = '" + id + "'" +
                " AND " + COL1 + " = '" + oldBudgt + "'";
        Log.d(TAG, "updateMonthlyBudget: query: " + query);
        db.execSQL(query);
    }

}
