package com.example.y.moneymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Y on 25-11-2016.
 */

public class UserDbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USEREXPENSE.DB";
    private static final int DATABSE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ usercontract.newuserinfo.TABLE_NAME+"("+usercontract.newuserinfo.EXPENSE_YEAR+" TEXT,"+ usercontract.newuserinfo.EXPENSE_MONTH+" TEXT,"+ usercontract.newuserinfo.EXPENSE_WEEK+" TEXT,"+ usercontract.newuserinfo.EXPENSE_DATE+" TEXT,"+ usercontract.newuserinfo.EXPENSE_CATEGORY+" TEXT,"+ usercontract.newuserinfo.EXPENSE_AMOUNT+" TEXT);";

    public UserDbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
        Log.e("Database Operation","Database created or opened");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("Database Operation","Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+usercontract.newuserinfo.TABLE_NAME);
        Log.e("Database Operation","Table Dropped");
        onCreate(db);

    }

    public void addinformation(String year,String month,String week,String date,String category,String amount,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(usercontract.newuserinfo.EXPENSE_YEAR,year);
        contentValues.put(usercontract.newuserinfo.EXPENSE_MONTH,month);
        contentValues.put(usercontract.newuserinfo.EXPENSE_WEEK,week);
        contentValues.put(usercontract.newuserinfo.EXPENSE_DATE,date);
        contentValues.put(usercontract.newuserinfo.EXPENSE_CATEGORY,category);
        contentValues.put(usercontract.newuserinfo.EXPENSE_AMOUNT,amount);
        db.insert(usercontract.newuserinfo.TABLE_NAME,null,contentValues);
        Log.e("Database Operation","One row inserted");
    }

    public Cursor getinformaton(SQLiteDatabase db){
        Cursor cursor;
        String [] projections = {usercontract.newuserinfo.EXPENSE_YEAR,usercontract.newuserinfo.EXPENSE_MONTH, usercontract.newuserinfo.EXPENSE_WEEK, usercontract.newuserinfo.EXPENSE_DATE, usercontract.newuserinfo.EXPENSE_CATEGORY, usercontract.newuserinfo.EXPENSE_AMOUNT};
        cursor = db.query(usercontract.newuserinfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }
    public Cursor searchinformation(String search_category,SQLiteDatabase db){
        String [] projections = {usercontract.newuserinfo.EXPENSE_YEAR,usercontract.newuserinfo.EXPENSE_MONTH,usercontract.newuserinfo.EXPENSE_WEEK,usercontract.newuserinfo.EXPENSE_DATE,usercontract.newuserinfo.EXPENSE_AMOUNT};
        String selection = usercontract.newuserinfo.EXPENSE_CATEGORY+" LIKE ?";
        String[] selection_args ={search_category};
        Cursor cursor = db.query(usercontract.newuserinfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public void deleteinformation(String search_category,SQLiteDatabase db){
        String selection = usercontract.newuserinfo.EXPENSE_CATEGORY+" LIKE ?";
        String[] selection_args ={search_category};
        db.delete(usercontract.newuserinfo.TABLE_NAME,selection,selection_args);


    }

    public Cursor search(String search_category,String search_date,String search_month,SQLiteDatabase db){
        String [] projections = {usercontract.newuserinfo.EXPENSE_YEAR,usercontract.newuserinfo.EXPENSE_MONTH,usercontract.newuserinfo.EXPENSE_WEEK,usercontract.newuserinfo.EXPENSE_DATE,usercontract.newuserinfo.EXPENSE_CATEGORY,usercontract.newuserinfo.EXPENSE_AMOUNT};
        String selection = usercontract.newuserinfo.EXPENSE_CATEGORY+" LIKE ? AND "+ usercontract.newuserinfo.EXPENSE_DATE+" LIKE ? AND "+ usercontract.newuserinfo.EXPENSE_MONTH+" LIKE ?";
        String[] selection_args ={search_category,search_date,search_month};
        Cursor cursor = db.query(usercontract.newuserinfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }
    public Cursor searchanddelete(String search_month,String search_date,String search_category,SQLiteDatabase db){
        String [] projections = {usercontract.newuserinfo.EXPENSE_MONTH,usercontract.newuserinfo.EXPENSE_WEEK,usercontract.newuserinfo.EXPENSE_DATE,usercontract.newuserinfo.EXPENSE_CATEGORY,usercontract.newuserinfo.EXPENSE_AMOUNT};
        String selection = usercontract.newuserinfo.EXPENSE_MONTH+" LIKE ? AND "+usercontract.newuserinfo.EXPENSE_DATE+" LIKE ? AND "+usercontract.newuserinfo.EXPENSE_CATEGORY+" LIKE ?";
        String[] selection_args ={search_month,search_date,search_category};
        Cursor cursor = db.query(usercontract.newuserinfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }


    public Cursor dailyreport(String search_month,String search_week,String search_date,SQLiteDatabase db){
        String [] projections = {usercontract.newuserinfo.EXPENSE_MONTH,usercontract.newuserinfo.EXPENSE_WEEK,usercontract.newuserinfo.EXPENSE_DATE,usercontract.newuserinfo.EXPENSE_CATEGORY,usercontract.newuserinfo.EXPENSE_AMOUNT};
        String selection = usercontract.newuserinfo.EXPENSE_MONTH+" LIKE ? AND "+ usercontract.newuserinfo.EXPENSE_WEEK+" LIKE ? AND "+usercontract.newuserinfo.EXPENSE_DATE+" LIKE ?";
        String[] selection_args ={search_month,search_week,search_date};
        Cursor cursor = db.query(usercontract.newuserinfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public Cursor weeklyreport(String search_month,String search_week,SQLiteDatabase db){
        String [] projections = {usercontract.newuserinfo.EXPENSE_MONTH,usercontract.newuserinfo.EXPENSE_WEEK,usercontract.newuserinfo.EXPENSE_DATE,usercontract.newuserinfo.EXPENSE_CATEGORY,usercontract.newuserinfo.EXPENSE_AMOUNT};
        String selection = usercontract.newuserinfo.EXPENSE_MONTH+" LIKE ? AND "+ usercontract.newuserinfo.EXPENSE_WEEK+" LIKE ?";
        String[] selection_args ={search_month,search_week};
        Cursor cursor = db.query(usercontract.newuserinfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public Cursor monthlyreport(String search_year,String search_month,SQLiteDatabase db){
        String [] projections = {usercontract.newuserinfo.EXPENSE_YEAR,usercontract.newuserinfo.EXPENSE_MONTH,usercontract.newuserinfo.EXPENSE_WEEK,usercontract.newuserinfo.EXPENSE_DATE,usercontract.newuserinfo.EXPENSE_CATEGORY,usercontract.newuserinfo.EXPENSE_AMOUNT};
        String selection = usercontract.newuserinfo.EXPENSE_YEAR+" LIKE ? AND "+ usercontract.newuserinfo.EXPENSE_MONTH+" LIKE ?";
        String[] selection_args ={search_year,search_month};
        Cursor cursor = db.query(usercontract.newuserinfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public Cursor yearlyreport(String search_year,SQLiteDatabase db){
        String [] projections = {usercontract.newuserinfo.EXPENSE_YEAR,usercontract.newuserinfo.EXPENSE_MONTH,usercontract.newuserinfo.EXPENSE_WEEK,usercontract.newuserinfo.EXPENSE_DATE,usercontract.newuserinfo.EXPENSE_CATEGORY,usercontract.newuserinfo.EXPENSE_AMOUNT};
        String selection = usercontract.newuserinfo.EXPENSE_YEAR+" LIKE ?";
        String[] selection_args ={search_year};
        Cursor cursor = db.query(usercontract.newuserinfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }


    public int updateinformation(String oldcategory,String olddate,String newyear,String newmonth,String newweek,String newdate,String newcategory,String newamount,SQLiteDatabase db ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(usercontract.newuserinfo.EXPENSE_YEAR,newyear);
        contentValues.put(usercontract.newuserinfo.EXPENSE_MONTH,newmonth);
        contentValues.put(usercontract.newuserinfo.EXPENSE_WEEK,newweek);
        contentValues.put(usercontract.newuserinfo.EXPENSE_DATE,newdate);
        contentValues.put(usercontract.newuserinfo.EXPENSE_CATEGORY,newcategory);
        contentValues.put(usercontract.newuserinfo.EXPENSE_AMOUNT,newamount);

        String selection = usercontract.newuserinfo.EXPENSE_CATEGORY+" LIKE ? AND "+ usercontract.newuserinfo.EXPENSE_DATE+" LIKE ?";
        String [] selection_args = {oldcategory,olddate};
        int count = db.update(usercontract.newuserinfo.TABLE_NAME,contentValues,selection,selection_args);
        return count;
    }







}





