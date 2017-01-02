package com.example.y.moneymanager;

/**
 * Created by Y on 26-11-2016.
 */

public class Dataprovider {
    private String year;
    private String month;
    private String week;
    private String date;
    private String category;
    private String amount;
    public Dataprovider(String year,String month,String week,String date,String category,String amount){
        this.year = year;
        this.month = month;
        this.week = week;
        this.date = date;
        this.category = category;
        this.amount = amount;

    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
