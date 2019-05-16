package org.pursuit.restaurantgrades.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private String camis,dba,boro,building,street,zipcode,critical_flag,cuisine_description,grade,grade_date,inspection_date,phone,record_date,score,violation_description;

    protected Restaurant(Parcel in) {
        camis = in.readString();
        dba = in.readString();
        boro = in.readString();
        building = in.readString();
        street = in.readString();
        zipcode = in.readString();
        critical_flag = in.readString();
        cuisine_description = in.readString();
        grade = in.readString();
        grade_date = in.readString();
        inspection_date = in.readString();
        phone = in.readString();
        record_date = in.readString();
        score = in.readString();
        violation_description = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getCamis() {
        return camis;
    }

    public void setCamis(String camis) {
        this.camis = camis;
    }

    public String getDba() {
        return dba;
    }

    public String getName() {
        return dba;
    }

    public void setDba(String dba) {
        this.dba = dba;
    }

    public String getBoro() {
        return boro;
    }

    public void setBoro(String boro) {
        this.boro = boro;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCritical_flag() {
        return critical_flag;
    }

    public void setCritical_flag(String critical_flag) {
        this.critical_flag = critical_flag;
    }

    public String getCuisine_description() {
        return cuisine_description;
    }

    public void setCuisine_description(String cuisine_description) {
        this.cuisine_description = cuisine_description;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade_date() {
        return grade_date;
    }

    public void setGrade_date(String grade_date) {
        this.grade_date = grade_date;
    }

    public String getInspection_date() {
        return inspection_date;
    }

    public void setInspection_date(String inspection_date) {
        this.inspection_date = inspection_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecord_date() {
        return record_date;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getViolation_description() {
        return violation_description;
    }

    public void setViolation_description(String violation_description) {
        this.violation_description = violation_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(camis);
        dest.writeString(dba);
        dest.writeString(boro);
        dest.writeString(building);
        dest.writeString(street);
        dest.writeString(zipcode);
        dest.writeString(critical_flag);
        dest.writeString(cuisine_description);
        dest.writeString(grade);
        dest.writeString(grade_date);
        dest.writeString(inspection_date);
        dest.writeString(phone);
        dest.writeString(record_date);
        dest.writeString(score);
        dest.writeString(violation_description);
    }
}
