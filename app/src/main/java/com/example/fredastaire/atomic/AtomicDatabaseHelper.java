package com.example.fredastaire.atomic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by Fred Astaire on 11/25/2017.
 */

public class AtomicDatabaseHelper extends SQLiteOpenHelper{

    //Constants for db name and version
    public static final String DB_NAME = "Atomic.SQLite";
    public static final int DB_VERSION = 1;

    //Constants for table and column identifiers
    public static String ATOMIC_ASSET_TABLE = "AssetTable";
    public static String AM_NUMBER = "AMNumber";
    public static String MAN_AND_MODEL = "ManAndModel";
    public static String IN_SERVICE_DATE = "InServiceDate";
    public static String REFRESH_DATE = "RefreshDate";
    public static String LOCATION = "Location";
    public static String USER_ID = "UserID";

    //Final string for all columns
    public static final String[] ALL_COLUMNS =
            {AM_NUMBER, MAN_AND_MODEL, IN_SERVICE_DATE, REFRESH_DATE, LOCATION, USER_ID};

    //Final string to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE "+ ATOMIC_ASSET_TABLE
                    + " ("
                    + AM_NUMBER + " nvarchar(10) primary key not null,"
                    + MAN_AND_MODEL + " nvarchar(20),"
                    + IN_SERVICE_DATE + " integer,"
                    + REFRESH_DATE + " integer,"
                    + LOCATION + " nvarchar(10),"
                    + USER_ID + " varchar(7)"
                    + ");";

    public AtomicDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase atomicDB) {

        Log.d("Asset Database", TABLE_CREATE);

        atomicDB.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
