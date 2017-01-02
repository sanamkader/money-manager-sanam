package com.example.y.moneymanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class YearlyreportsearchActivity extends AppCompatActivity {

    EditText et_year_yearlyreportsactivity;


    Button bt_search_yearlyreportsactivity;
    Button bt_calculateyearlyexpense;
    Button bt_clear_yearlyreportsactivity;

    TextView tv_showyearlyreport;
    TextView tv_totalyearlyexpense;
    TextView tv_yearlybalance;

    String search_year;
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
        setContentView(R.layout.activity_yearlyreportsearch);

        et_year_yearlyreportsactivity = (EditText) findViewById(R.id.et_year_yearlyreportsactivity);


        bt_search_yearlyreportsactivity = (Button) findViewById(R.id.bt_search_yearlyreportsactivity);
        bt_calculateyearlyexpense = (Button) findViewById(R.id.bt_calculateyearlyexpense);
        bt_clear_yearlyreportsactivity = (Button) findViewById(R.id.bt_clear_yearlyreportsactivity);

        bt_calculateyearlyexpense.setClickable(false);

        tv_showyearlyreport = (TextView) findViewById(R.id.tv_showyearlyreport);
        tv_totalyearlyexpense = (TextView) findViewById(R.id.tv_totalyearlyexpense);
        tv_yearlybalance = (TextView) findViewById(R.id.tv_yearlybalance);

        bt_search_yearlyreportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchyearlyinfo(view);
            }
        });
        bt_calculateyearlyexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalyearlyexpense(view);
                yearlybalance(view);

            }
        });
        bt_clear_yearlyreportsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearresults(view);
            }
        });
    }


    public void searchyearlyinfo(View view){
        if (et_year_yearlyreportsactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter Month", Toast.LENGTH_SHORT).show();
        }
        else {
            search_year = et_year_yearlyreportsactivity.getText().toString();

            userDbhelper = new UserDbhelper(getApplicationContext());
            sqLiteDatabase = userDbhelper.getReadableDatabase();
            cursor = userDbhelper.yearlyreport(search_year, sqLiteDatabase);
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
                tv_showyearlyreport.setText("NO RESULTS AVAILABLE");
                Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
                results = "";
            } else {
                tv_showyearlyreport.setText(results);
                Toast.makeText(this, "Hit clear before next search", Toast.LENGTH_SHORT).show();
                results = "";
                bt_calculateyearlyexpense.setClickable(true);
            }
        }


    }
    public void totalyearlyexpense(View view){

        if(tv_showyearlyreport.length()==0) {
            tv_totalyearlyexpense.setText("NO RESULTS AVAILABLE");
            Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
        }
        else{
            if (cursor.moveToFirst()){
                do {
                    total+= cursor.getInt(5);

                }while (cursor.moveToNext());
            }
            totalexpense=total;
            tv_totalyearlyexpense.setText("Total yearly Expense is "+totalexpense);
            total=0;
            Toast.makeText(this, "Hit clear before next search", Toast.LENGTH_SHORT).show();
        }

    }
    public void clearresults(View view){
        et_year_yearlyreportsactivity.setText("");
        tv_showyearlyreport.setText("");
        tv_totalyearlyexpense.setText("");
        results="";
        total=0;
        totalexpense=0;

    }

    public void yearlybalance(View view){
        String monthly_income="";
        float income=0;
        float total_income=0;
        sharedPreferences = getSharedPreferences("userincomeinfo", Context.MODE_PRIVATE);

            monthly_income = sharedPreferences.getString("januaryincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for january", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for january");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("februaryincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for february", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for february");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("marchincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for march", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for march");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("aprilincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for april", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for april");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("mayincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for may", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for may");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }

            monthly_income = sharedPreferences.getString("juneincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for june", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for june");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("julyincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for july", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for july");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("augustincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for august", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for august");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("septemberincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for september", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for september");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("octoberincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for october", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for october");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("novemberincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for november", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for november");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            monthly_income = sharedPreferences.getString("decemberincome","addincome");
        if(monthly_income.equals("addincome")){
            Toast.makeText(this, "Add Income for december", Toast.LENGTH_SHORT).show();
            tv_yearlybalance.setText("Add Income for december");
            return;
        }
        else{
            income= Float.parseFloat(monthly_income);
            total_income +=income;
        }
            float totalyearlyexpense = totalexpense;
        float totalyearlyincome = total_income;
            float yearly_savings = totalyearlyincome - totalyearlyexpense;
            if (yearly_savings > 0) {
                tv_yearlybalance.setText("You have saved " + yearly_savings + " Rs on your yearly limit");
                total_income=0;
                totalexpense=0;
            } else if (yearly_savings < 0) {
                float positive_yearly_savings = yearly_savings * -1;
                tv_yearlybalance.setText("You hav exceeded your limit and used " + positive_yearly_savings + " Rs more");
                total_income=0;
                totalexpense=0;
            }
        }
}
