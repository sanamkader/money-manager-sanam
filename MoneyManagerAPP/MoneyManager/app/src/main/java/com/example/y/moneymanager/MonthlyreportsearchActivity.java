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

public class MonthlyreportsearchActivity extends AppCompatActivity {
    EditText et_month_monthlyreportsactivity;
    EditText et_year_monthlyreportsactivity;


    Button bt_search_monthlyreportsactivity;
    Button bt_calculatemonthlyexpense;
    Button bt_clear_monthlyreportsactivity;

    TextView tv_showmonthlyreport;
    TextView tv_totalmonthlyexpense;
    TextView tv_monthlybalance;

    String search_month,search_year;
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
        setContentView(R.layout.activity_monthlyreportsearch);

        et_month_monthlyreportsactivity = (EditText) findViewById(R.id.et_month_monthlyreportsactivity);
        et_year_monthlyreportsactivity = (EditText) findViewById(R.id.et_year_monthlyreportsactivity);


        bt_search_monthlyreportsactivity = (Button) findViewById(R.id.bt_search_monthlyreportsactivity);
        bt_calculatemonthlyexpense = (Button) findViewById(R.id.bt_calculatemonthlyexpense);
        bt_clear_monthlyreportsactivity = (Button) findViewById(R.id.bt_clear_monthlyreportsactivity);

        bt_calculatemonthlyexpense.setClickable(false);

        tv_showmonthlyreport = (TextView) findViewById(R.id.tv_showmonthlyreport);
        tv_totalmonthlyexpense = (TextView) findViewById(R.id.tv_totalmonthlyexpense);
        tv_monthlybalance = (TextView) findViewById(R.id.tv_monthlybalance);

        bt_search_monthlyreportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchmonthlyinfo(view);
            }
        });
        bt_calculatemonthlyexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalmonthlyexpense(view);
                monthlybalance(view);

            }
        });
        bt_clear_monthlyreportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearresults(view);
            }
        });
    }

    public void searchmonthlyinfo(View view){
        if (et_year_monthlyreportsactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter Month", Toast.LENGTH_SHORT).show();
        }
        else if (et_month_monthlyreportsactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter Month", Toast.LENGTH_SHORT).show();
        }
        else {
            search_year = et_year_monthlyreportsactivity.getText().toString();
            search_month = et_month_monthlyreportsactivity.getText().toString();

            userDbhelper = new UserDbhelper(getApplicationContext());
            sqLiteDatabase = userDbhelper.getReadableDatabase();
            cursor = userDbhelper.monthlyreport(search_year,search_month, sqLiteDatabase);
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
                    results += " ";
                    results += cursor.getString(5);
                    results += "\n";
                } while (cursor.moveToNext());
            }
            if (results.length() == 0) {
                tv_showmonthlyreport.setText("NO RESULTS AVAILABLE");
                Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
                results = "";
            } else {
                tv_showmonthlyreport.setText(results);
                Toast.makeText(this, "Hit clear before next search", Toast.LENGTH_SHORT).show();
                results = "";
                bt_calculatemonthlyexpense.setClickable(true);
            }
        }


    }
    public void totalmonthlyexpense(View view){

        if(tv_showmonthlyreport.length()==0) {
            tv_totalmonthlyexpense.setText("NO RESULTS AVAILABLE");
            Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
        }
        else{
            if (cursor.moveToFirst()){
                do {
                    total+= cursor.getInt(5);

                }while (cursor.moveToNext());
            }
            totalexpense=total;
            tv_totalmonthlyexpense.setText("Total Monthly Expense is "+totalexpense);
            total=0;
            Toast.makeText(this, "Hit clear before next search", Toast.LENGTH_SHORT).show();
        }

    }
    public void clearresults(View view){
        et_month_monthlyreportsactivity.setText("");
        tv_showmonthlyreport.setText("");
        tv_totalmonthlyexpense.setText("");
        results="";
        total=0;
        totalexpense=0;

    }

    public void monthlybalance(View view){
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
            tv_monthlybalance.setText("ADD Income for this month to get the monthly limit details");
            Toast.makeText(this, "Add Monthly income", Toast.LENGTH_SHORT).show();
        }
        else {

            float monthlyincome = Float.parseFloat(monthly_income);
            float totalmonthlyexpense = totalexpense;
            float monthly_savings = monthlyincome - totalmonthlyexpense;
            if (monthly_savings > 0) {
                tv_monthlybalance.setText("You have saved " + monthly_savings + " Rs on your monthly limit");
            } else if (monthly_savings < 0) {
                float positive_monthly_savings = monthly_savings * -1;
                tv_monthlybalance.setText("You hav exceeded your limit and used " + positive_monthly_savings + " Rs more");
            }
        }

    }




}
