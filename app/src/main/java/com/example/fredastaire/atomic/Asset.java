package com.example.fredastaire.atomic;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Fred Astaire on 11/25/2017.
 */

public class Asset implements Comparable<Asset>{

    private String amNumber;
    private String manAndModel;
    private Date inServiceDate;
    private Date refreshDate;
    private String location;
    private String userID;

    /**Used for onboarding a new asset with no other values.  **/
    public Asset(String amNumber, String manAndModel, Date inServiceDate) {
        this.amNumber = amNumber;
        this.manAndModel = manAndModel;
        this.inServiceDate = inServiceDate;
        setRefreshDate(inServiceDate);
    }

    /**Used for asset updates once the update is place in location with a user **/
    public Asset(String amNumber, String manAndModel, Date inServiceDate, String location, String userID) {
        this.amNumber = amNumber;
        this.manAndModel = manAndModel;
        this.inServiceDate = inServiceDate;
        setRefreshDate(inServiceDate);
        this.location = location;
        this.userID = userID;
    }

    /**Sets all values for the view for already created entries in the database **/
    public Asset(String amNumber, String manAndModel, long inServiceDate, long refreshDate, String location, String userID) {
        this.amNumber = amNumber;
        this.manAndModel = manAndModel;
        setServiceDateEpoch(inServiceDate);
        setRefreshDateEpoch(refreshDate);
        this.location = location;
        this.userID = userID;
    }

    public String getAmNumber() {
        return amNumber;
    }

    public void setAmNumber(String amNumber) {
        this.amNumber = amNumber;
    }

    public String getManAndModel() {
        return manAndModel;
    }

    public void setManAndModel(String manAndModel) {
        this.manAndModel = manAndModel;
    }

    public Date getInServiceDate() {
        return inServiceDate;
    }

    public void setInServiceDate(Date inServiceDate) {
        this.inServiceDate = inServiceDate;
    }

    public long getServiceDateEpoch() {
        return inServiceDate.getTime() / 1000;
    }

    public void setServiceDateEpoch(long seconds) {
        inServiceDate = new Date(seconds * 1000);
    }

    public long getRefreshDateEpoch() {
        return refreshDate.getTime() / 1000;
    }

    public void setRefreshDateEpoch(long seconds) {
        refreshDate = new Date(seconds * 1000);
    }

    public Date getRefreshDate() {
        return refreshDate;
    }

    public void setRefreshDate(Date refreshDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getInServiceDate());
        cal.add(Calendar.YEAR, 4);
        this.refreshDate = cal.getTime();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        String result;

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

        result = " " + amNumber
                + "    " + manAndModel
                + "   " + df.format(inServiceDate)
                + "    " + df.format(refreshDate)
                + "    " + location
                + "   " + userID;

        return result;
    }

    @Override
    public boolean equals(Object that) {
        Asset asset = (Asset) that;

        return this.amNumber.equals(asset.amNumber);
    }

    @Override
    public int compareTo(Asset that) {
        int difference;

        difference = this.amNumber.compareTo(that.amNumber);
        return difference;
    }


}
