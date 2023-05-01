package com.example.appnasa.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Asteroid implements Parcelable {

    private String name;
    private double absoluteMagnitudeH;
    private double estimatedDiameter;
    private String is_potentially_hazardous_asteroid;
    private int userId;

    public Asteroid() {
    }

    public Asteroid(String name, double absoluteMagnitudeH, double estimatedDiameter, String is_potentially_hazardous_asteroid, int userId) {

        this.name = name;
        this.absoluteMagnitudeH = absoluteMagnitudeH;
        this.estimatedDiameter = estimatedDiameter;
        this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
        this.userId = userId;
    }

    protected Asteroid(Parcel in) {

        name = in.readString();
        absoluteMagnitudeH = in.readDouble();
        estimatedDiameter = in.readDouble();
        is_potentially_hazardous_asteroid = in.readString();
        userId = in.readInt();
    }

    public static final Creator<Asteroid> CREATOR = new Creator<Asteroid>() {
        @Override
        public Asteroid createFromParcel(Parcel in) {
            return new Asteroid(in);
        }

        @Override
        public Asteroid[] newArray(int size) {
            return new Asteroid[size];
        }
    };


    public String getIs_potentially_hazardous_asteroid() {
        return is_potentially_hazardous_asteroid;
    }

    public void setIs_potentially_hazardous_asteroid(String is_potentially_hazardous_asteroid) {
        this.is_potentially_hazardous_asteroid = is_potentially_hazardous_asteroid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAbsoluteMagnitudeH() {
        return absoluteMagnitudeH;
    }

    public void setAbsoluteMagnitudeH(double absoluteMagnitudeH) {
        this.absoluteMagnitudeH = absoluteMagnitudeH;
    }

    public double getEstimatedDiameter() {
        return estimatedDiameter;
    }

    public void setEstimatedDiameter(double estimatedDiameter) {
        this.estimatedDiameter = estimatedDiameter;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeDouble(absoluteMagnitudeH);
        dest.writeDouble(estimatedDiameter);
        dest.writeString(is_potentially_hazardous_asteroid);
        dest.writeInt(userId);
    }
}
