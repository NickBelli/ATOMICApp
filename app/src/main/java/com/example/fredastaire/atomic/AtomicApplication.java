package com.example.fredastaire.atomic;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.example.fredastaire.atomic.AtomicDatabaseHelper.*;

/**
 * Created by Fred Astaire on 12/4/2017.
 * Most of the information in this java file was altered from previous projects,
 * most notably coming from MyBowlingScoresApplication in the majority.
 */

public class AtomicApplication extends Application {

    private ArrayList<Asset> allAssets;
    private ArrayList<Asset> searchAssets;
    private SQLiteDatabase atomicDB;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("Instance", "Getting Database");
        AtomicDatabaseHelper databaseHelper = new AtomicDatabaseHelper(this);
        atomicDB = databaseHelper.getWritableDatabase();

        Log.d("Instance", "I got the database");
        readAssetsFromDB();
    }

    private void readAssetsFromDB() {
        allAssets = new ArrayList<Asset>();

        Cursor assetCursor;

        assetCursor = atomicDB.query(ATOMIC_ASSET_TABLE,
                ALL_COLUMNS,
                null, null, null, null, REFRESH_DATE);

        assetCursor.moveToFirst();
        Asset tempAM;

        if (!assetCursor.isAfterLast()) {
            do {
                String amNumber = assetCursor.getString(0);
                String manAndModel = assetCursor.getString(1);
                long inServiceDate = assetCursor.getLong(2);
                long refreshDate = assetCursor.getLong(3);
                String location = assetCursor.getString(4);
                String userID = assetCursor.getString(5);

                tempAM = new Asset(amNumber, manAndModel, inServiceDate, refreshDate, location, userID);

                allAssets.add(tempAM);

                Log.d("Bowling Database", tempAM.toString());
            } while (assetCursor.moveToNext());
        }
        assetCursor.close();
    }

    //Create Add Method to insert to Database
    public void addAsset(Asset asset) {
        assert asset != null;

        ContentValues cv = new ContentValues();
        cv.put(AtomicDatabaseHelper.AM_NUMBER, asset.getAmNumber());
        cv.put(AtomicDatabaseHelper.MAN_AND_MODEL, asset.getManAndModel());
        cv.put(AtomicDatabaseHelper.IN_SERVICE_DATE, asset.getServiceDateEpoch());
        cv.put(AtomicDatabaseHelper.REFRESH_DATE, asset.getRefreshDateEpoch());
        cv.put(AtomicDatabaseHelper.LOCATION, asset.getLocation());
        cv.put(AtomicDatabaseHelper.USER_ID, asset.getUserID());

        Log.d("Asset Database", "Before Inserting a Record " + asset);

        atomicDB.insert(AtomicDatabaseHelper.ATOMIC_ASSET_TABLE, null, cv);

        Log.d("Asset Database", "After Inserting a Record " + asset);
        allAssets.add(asset);

    }
    //Create Delete Method to insert to Database
    public void deleteAsset(Asset toDelete) {
        Log.d("Database", toDelete.toString());
        atomicDB.delete(ATOMIC_ASSET_TABLE,
                AM_NUMBER + "='" + toDelete.getAmNumber() + "'", null);
        //Log.d("DATABASE", "Deleted: " + count + "record." + " " + toDelete);

        allAssets.remove(toDelete);
    }

    //Create query method to return only assets by search string
//    public ArrayList<Asset> searchAssetByAMNumber(String searchAMNumber){
//        allAssets = new ArrayList<Asset>();
//
//        Cursor assetCursor;
//
//        assetCursor = atomicDB.query(ATOMIC_ASSET_TABLE,
//                ALL_COLUMNS,
//                AM_NUMBER + "='" + searchAMNumber + "'", null, null, null, REFRESH_DATE);
//
//        assetCursor.moveToFirst();
//        Asset tempAM;
//
//        if (!assetCursor.isAfterLast()) {
//            do {
//                String amNumber = assetCursor.getString(0);
//                String manAndModel = assetCursor.getString(1);
//                long inServiceDate = assetCursor.getLong(2);
//                long refreshDate = assetCursor.getLong(3);
//                String location = assetCursor.getString(4);
//                String userID = assetCursor.getString(5);
//
//                tempAM = new Asset(amNumber, manAndModel, inServiceDate, refreshDate, location, userID);
//
//                allAssets.add(tempAM);
//
//                Log.d("Bowling Database", tempAM.toString());
//            } while (assetCursor.moveToNext());
//        }
//        assetCursor.close();
//        return allAssets;
//    }

    public ArrayList<Asset> searchAssetByUserID(String userID){
        return allAssets;
    }

    public ArrayList<Asset> getAllAssets() {
        return allAssets;
    }

    private void setAllAssets(ArrayList<Asset> allAssets) {
        this.allAssets =  allAssets;
    }
}
