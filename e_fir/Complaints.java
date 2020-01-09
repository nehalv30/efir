package com.example.e_fir;

import android.util.EventLogTags;

public class Complaints {
    String Id;
    String Description;
    String Type;
    int date;
    int Month;
    int Year;
    String User;
    String User_id;
    String Officer_incharge;
    String Status;
    Complaints(){}
    Complaints(String Id, String Description, String Type, int date, int Month, int Year, String User, String User_id){
        this.Id=Id;
        this.Description=Description;
        this.Type=Type;
        this.date=date;
        this.Month=Month;
        this.Year=Year;
        this.User=User;
        this.User_id=User_id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }
}
