package com.example.y.moneymanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DailyreportsearchActivity extends AppCompatActivity {
    EditText et_month_dailyreportsactivity;
    EditText et_week_dailyreportsactivity;
    EditText et_date_dailyreportsactivity;

    Button bt_search_dailyreportsactivity;
    Button bt_calculatedailyexpense;
    Button bt_clear_dailyreportsactivity;

    TextView tv_showdailyreport;
    TextView tv_totaldailyexpense;
    TextView tv_dailybalance;

    String search_month,search_week,search_date;
    String results = "";
    int total = 0;
    int totalexpense = 0;

    UserDbhelper userDbhelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyreportsearch);
        et_month_dailyreportsactivity = (EditText) findViewById(R.id.et_month_dailyreportsactivity);
        et_week_dailyreportsactivity = (EditText) findViewById(R.id.et_week_dailyreportsactivity);
        et_date_dailyreportsactivity = (EditText) findViewById(R.id.et_date_dailyreportsactivity);

        bt_search_dailyreportsactivity = (Button) findViewById(R.id.bt_search_dailyreportsactivity);
        bt_calculatedailyexpense = (Button) findViewById(R.id.bt_calculatedailyexpense);
        bt_clear_dailyreportsactivity=(Button) findViewById(R.id.bt_clear_dailyreportsactivity);
        bt_calculatedailyexpense.setClickable(false);

        tv_showdailyreport = (TextView) findViewById(R.id.tv_showdailyreport);
        tv_totaldailyexpense = (TextView) findViewById(R.id.tv_totaldailyexpense);
        tv_dailybalance = (TextView) findViewById(R.id.tv_dailybalance);




        bt_search_dailyreportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchdailyinfo(view);
            }
        });
        bt_calculatedailyexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totaldaailyexpense(view);
                dailybalance(view);

            }
        });
        bt_clear_dailyreportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearresults(view);

            }
        });


    }

    public void searchdailyinfo(View view){
        if(et_month_dailyreportsactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter Month", Toast.LENGTH_SHORT).show();
        }
        else if(et_week_dailyreportsactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter Week", Toast.LENGTH_SHORT).show();
        }
        else if(et_date_dailyreportsactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter Date", Toast.LENGTH_SHORT).show();
        }
        else {
            search_month = et_month_dailyreportsactivity.getText().toString();
            search_week = et_week_dailyreportsactivity.getText().toString();
            search_date = et_date_dailyreportsactivity.getText().toString();

            userDbhelper = new UserDbhelper(getApplicationContext());
            sqLiteDatabase = userDbhelper.getReadableDatabase();
            cursor = userDbhelper.dailyreport(search_month, search_week, search_date, sqLiteDatabase);
            if (cursor.moveToFirst()) {
                do {
                    results += cursor.getString(0);
                    results += " ";
                    results += cursor.getString(1);
                    results += " ";
                    results += cursor.getString(2);
                    results += " ";
                    results += cursor.getString(3);
                    results += " ";
                    results += cursor.getString(4);
                    results += "\n";
                } while (cursor.moveToNext());
            }
            if (results.length() == 0) {
                tv_showdailyreport.setText("NO RESULTS AVAILABLE");
                Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
                results = "";
            } else {
                tv_showdailyreport.setText(results);
                results = "";
                Toast.makeText(this, "Results available", Toast.LENGTH_SHORT).show();
                bt_calculatedailyexpense.setClickable(true);
            }
        }


    }
    public void totaldaailyexpense(View view){

        if(tv_showdailyreport.length()==0) {
            tv_totaldailyexpense.setText("NO RESULTS AVAILABLE");
            Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
        }
        else{
            if (cursor.moveToFirst()){
                do {
                    total+= cursor.getInt(4);

                }while (cursor.moveToNext());
            }
             totalexpense = total;
            total=0;
            tv_totaldailyexpense.setText("Total Daily Expense is "+totalexpense);
            Toast.makeText(this, "Hit clear before next search", Toast.LENGTH_SHORT).show();
        }

    }

    public void clearresults(View view){
        et_month_dailyreportsactivity.setText("");
        et_date_dailyreportsactivity.setText("");
        et_week_dailyreportsactivity.setText("");
        tv_showdailyreport.setText("");
        tv_totaldailyexpense.setText("");
        tv_dailybalance.setText("");
        results="";
        total=0;
        totalexpense=0;
    }
    public void dailybalance(View view){
        String monthly_income="";
        sharedPreferences = getSharedPreferences("userincomeinfo",Context.MODE_PRIVATE);
        if(search_month.equals("january")){
           monthly_income = sharedPreferences.getString("januaryincome","addincome");
        }
       else if(search_month.equals("february")){
            monthly_income = sharedPreferences.getString("februaryincome","addincome");
        }
       else if(search_month.equals("march")){
            monthly_income = sharedPreferences.getString("marchincome","addincome");
        }
       else if(search_month.equals("april")){
            monthly_income = sharedPreferences.getString("aprilincome","addincome");
        }
       else if(search_month.equals("may")){
            monthly_income = sharedPreferences.getString("mayincome","addincome");
        }
        else if(search_month.equals("june")){
            monthly_income = sharedPreferences.getString("juneincome","addincome");
        }
       else if(search_month.equals("july")){
            monthly_income = sharedPreferences.getString("julyincome","addincome");
        }
       else if(search_month.equals("august")){
            monthly_income = sharedPreferences.getString("augustincome","addincome");
        }
       else if(search_month.equals("september")){
            monthly_income = sharedPreferences.getString("septemberincome","addincome");
        }
       else if(search_month.equals("october")){
            monthly_income = sharedPreferences.getString("octoberincome","addincome");
        }
       else if(search_month.equals("november")){
            monthly_income = sharedPreferences.getString("novemberincome","addincome");
        }
       else if(search_month.equals("december")){
            monthly_income = sharedPreferences.getString("decemberincome","addincome");
        }
        if(monthly_income.equals("addincome")){
            tv_dailybalance.setText("ADD Income for this month to get the daily limit details");
            Toast.makeText(this, "Add Monthly income", Toast.LENGTH_SHORT).show();
        }
        else {

            float monthlyincome = Float.parseFloat(monthly_income);
            float totaldailyexpense = totalexpense;
            float dailyincome = monthlyincome / 30;
            float daily_savings = dailyincome - totaldailyexpense;
            if (daily_savings > 0) {
                tv_dailybalance.setText("You have saved " + daily_savings + " Rs on your daily limit");
            } else if (daily_savings < 0) {
                float positive_daily_savings = daily_savings * -1;
                tv_dailybalance.setText("You hav exceeded your limit and used " + positive_daily_savings + " Rs more");
            }
        }

    }



}
