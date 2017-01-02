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

public class WeeklyreportsearchActivity extends AppCompatActivity {
    EditText et_month_weeklyreportsactivity;
    EditText et_week_weeklyreportsactivity;


    Button bt_search_weeklyreportsactivity;
    Button bt_calculateweeklyexpense;
    Button bt_clear_weeklyreportsactivity;

    TextView tv_showweeklyreport;
    TextView tv_totalweeklyexpense;
    TextView tv_weeklybalance;

    String search_month,search_week;
    String results = "";
    int total = 0;
    int totalexpense=0;

    UserDbhelper userDbhelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeklyreportsearch);

        et_month_weeklyreportsactivity = (EditText) findViewById(R.id. et_month_weeklyreportsactivity);
        et_week_weeklyreportsactivity = (EditText) findViewById(R.id.et_week_weeklyreportsactivity);

        bt_search_weeklyreportsactivity = (Button) findViewById(R.id.bt_search_weeklyreportsactivity);
        bt_calculateweeklyexpense = (Button) findViewById(R.id.bt_calculateweeklyexpense);
        bt_clear_weeklyreportsactivity = (Button) findViewById(R.id.bt_clear_weeklyreportsactivity);
        bt_clear_weeklyreportsactivity.setClickable(false);

        tv_showweeklyreport = (TextView) findViewById(R.id.tv_showweeklyreport);
        tv_totalweeklyexpense = (TextView) findViewById(R.id.tv_totalweeklyexpense);
        tv_weeklybalance= (TextView) findViewById(R.id.tv_weeklybalance);

       bt_search_weeklyreportsactivity.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               searchweeklyinfo(view);
           }
       });
        bt_calculateweeklyexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalweeklyexpense(view);
                weeklybalance(view);

            }
        });

        bt_clear_weeklyreportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearresults(view);
            }
        });

    }

    public void searchweeklyinfo(View view){
        if(et_month_weeklyreportsactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter Month", Toast.LENGTH_SHORT).show();
        }
       else if(et_week_weeklyreportsactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter Week", Toast.LENGTH_SHORT).show();
        }
        else {
            search_month = et_month_weeklyreportsactivity.getText().toString();
            search_week = et_week_weeklyreportsactivity.getText().toString();

            userDbhelper = new UserDbhelper(getApplicationContext());
            sqLiteDatabase = userDbhelper.getReadableDatabase();
            cursor = userDbhelper.weeklyreport(search_month, search_week, sqLiteDatabase);
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
                tv_showweeklyreport.setText("NO RESULTS AVAILABLE");
                Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
            } else {
                tv_showweeklyreport.setText(results);
                results = "";
                Toast.makeText(this, "Results available", Toast.LENGTH_SHORT).show();
                bt_calculateweeklyexpense.setClickable(true);
            }
        }


    }
    public void totalweeklyexpense(View view){

        if(tv_showweeklyreport.length()==0) {
            tv_totalweeklyexpense.setText("NO RESULTS AVAILABLE");
            Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
        }
        else{
            if (cursor.moveToFirst()){
                do {
                    total+= cursor.getInt(4);

                }while (cursor.moveToNext());
            }
            totalexpense = total;
            tv_totalweeklyexpense.setText("Total Weekly Expense is "+totalexpense);
            total=0;
            Toast.makeText(this, "Results", Toast.LENGTH_SHORT).show();
        }

    }
    public void clearresults(View view){
        et_month_weeklyreportsactivity.setText("");
        et_week_weeklyreportsactivity.setText("");
        tv_showweeklyreport.setText("");
        tv_totalweeklyexpense.setText("");
        results="";
        total=0;
        totalexpense=0;
    }

    public void weeklybalance(View view){
        String monthly_income="";
        sharedPreferences = getSharedPreferences("userincomeinfo", Context.MODE_PRIVATE);
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
            tv_weeklybalance.setText("ADD Income for this month to get the weekly limit details");
            Toast.makeText(this, "Add Monthly income", Toast.LENGTH_SHORT).show();
        }
        else {

            float monthlyincome = Float.parseFloat(monthly_income);
            float totalweeklyexpense = totalexpense;
            float dailyincome = monthlyincome / 4;
            float weekly_savings = dailyincome - totalweeklyexpense;
            if (weekly_savings > 0) {
                tv_weeklybalance.setText("You have saved " + weekly_savings + " Rs on your weekly limit");
            } else if (weekly_savings < 0) {
                float positive_weekly_savings = weekly_savings * -1;
                tv_weeklybalance.setText("You hav exceeded your limit and used " + positive_weekly_savings + " Rs more");
            }
        }

    }



}
