package com.arturofilio.bo_g.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "bog.db";
    private static final String TABLE_NAME = "budgets_table";
    public static final String COL0 = "ID";
    public static final String COL1 = "Initial_Date";
    public static final String COL2 = "Ending_Date";
    public static final String COL3 = "Todays_Date";
    public static final String COL4 = "Budget";
    public static final String COL5 = "Daily_Exp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +
                TABLE_NAME + " ( " +
                COL0 + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + "TEXT, " +
                COL2 + "TEXT, " +
                COL3 + "TEXT, " +
                COL4 + "INTEGER, " +
                COL5 + "INTEGER) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Insert a new budget on to the database
     *
     */

    //Missing a lot of code lol

}
