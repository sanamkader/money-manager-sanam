package com.example.y.moneymanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateexpenseActivity extends AppCompatActivity {
    String search_category;
    String search_date;
    String search_month;

    UserDbhelper userdbhelper;
    SQLiteDatabase sqlitedatabase;

    EditText et_searchcategory_updateexpenseactivity;
    EditText et_searchdate_updateexpenseactivity;
    EditText et_searchmonth_updateexpenseactivity;


    EditText et_newmonth_updateexpenseactivity;
    EditText et_newweek_updateexpenseactivity;
    EditText et_newdate_updateexpenseactivity;
    EditText et_newcategory_updateexpenseactivity;
    EditText et_newamount_updateexpenseactivity;
    EditText et_newyear_updateexpenseactivity;

    Button bt_search_updateexpenseaactivity;
    Button bt_update_updateexpenseaactivity;
    Button bt_clear_updateexpenseactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateexpense);
        et_searchcategory_updateexpenseactivity = (EditText) findViewById(R.id.et_searchcategory_updateexpenseactivity);
        et_searchdate_updateexpenseactivity = (EditText) findViewById(R.id.et_searchdate_updateexpenseactivity);
        et_searchmonth_updateexpenseactivity = (EditText) findViewById(R.id.et_searchmonth_updateexpenseactivity);

        et_newmonth_updateexpenseactivity = (EditText) findViewById(R.id.et_newmonth_updateexpenseactivity);
        et_newweek_updateexpenseactivity = (EditText) findViewById(R.id.et_newweek_updateexpenseactivity);
        et_newdate_updateexpenseactivity = (EditText) findViewById(R.id.et_newdate_updateexpenseactivity);
        et_newcategory_updateexpenseactivity = (EditText) findViewById(R.id.et_newcategory_updateexpenseactivity);
        et_newamount_updateexpenseactivity = (EditText) findViewById(R.id.et_newamount_updateexpenseactivity);
        et_newyear_updateexpenseactivity = (EditText) findViewById(R.id.et_newyear_updateexpenseactivity);

        bt_search_updateexpenseaactivity = (Button) findViewById(R.id.bt_search_updateexpenseactivity);
        bt_update_updateexpenseaactivity = (Button) findViewById(R.id.bt_update_updateexpenseactivity);
        bt_clear_updateexpenseactivity = (Button) findViewById(R.id.bt_clear_updateexpenseactivity);

        bt_search_updateexpenseaactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersearch(view);
            }
        });

        bt_update_updateexpenseaactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateinfo(view);
            }
        });
        bt_clear_updateexpenseactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearresults(view);
            }
        });

    }

    public void usersearch(View view) {
        if (et_searchcategory_updateexpenseactivity.getText().toString().length() == 0) {
            Toast.makeText(this, "enter search category", Toast.LENGTH_SHORT).show();
            return;
        } else if (et_searchdate_updateexpenseactivity.getText().toString().length() == 0) {
            Toast.makeText(this, "enter search date", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(et_searchmonth_updateexpenseactivity.getText().toString().length()==0){
            Toast.makeText(this, "enter search month", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            search_month = et_searchmonth_updateexpenseactivity.getText().toString();
             search_category = et_searchcategory_updateexpenseactivity.getText().toString();
             search_date = et_searchdate_updateexpenseactivity.getText().toString();

            userdbhelper = new UserDbhelper(getApplicationContext());
            sqlitedatabase = userdbhelper.getReadableDatabase();
            Cursor cursor = userdbhelper.search(search_category,search_date,search_month,sqlitedatabase);
            if(cursor.moveToFirst()){
                String year = cursor.getString(0);
               String month = cursor.getString(1);
                String week = cursor.getString(2);
                String date = cursor.getString(3);
                String category = cursor.getString(4);
                String amount  = cursor.getString(5);
                et_newyear_updateexpenseactivity.setText(year);
                et_newmonth_updateexpenseactivity.setText(month);
                et_newweek_updateexpenseactivity.setText(week);
                et_newdate_updateexpenseactivity.setText(date);
                et_newcategory_updateexpenseactivity.setText(category);
                et_newamount_updateexpenseactivity.setText(amount);
                Toast.makeText(this, "Results", Toast.LENGTH_SHORT).show();
            }
            else{
                et_newyear_updateexpenseactivity.setText("");
                et_newmonth_updateexpenseactivity.setText("");
                et_newweek_updateexpenseactivity.setText("");
                et_newdate_updateexpenseactivity.setText("");
                et_newcategory_updateexpenseactivity.setText("");
                et_newamount_updateexpenseactivity.setText("");
                Toast.makeText(this, "No Results Available", Toast.LENGTH_SHORT).show();

            }


        }


    }

    public void updateinfo(View view){
        if(et_newyear_updateexpenseactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter new year", Toast.LENGTH_SHORT).show();
            return;
        }

        else if(et_newmonth_updateexpenseactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter new month", Toast.LENGTH_SHORT).show();
            return;
        }
       else if(et_newweek_updateexpenseactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter new week", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(et_newdate_updateexpenseactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter new date", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (et_newcategory_updateexpenseactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter new category", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(et_newamount_updateexpenseactivity.getText().toString().length()==0){
            Toast.makeText(this, "Enter new amount", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            userdbhelper = new UserDbhelper(getApplicationContext());
            sqlitedatabase = userdbhelper.getWritableDatabase();
            String year,month, week, date, category, amount;
            year = et_newyear_updateexpenseactivity.getText().toString();
            month = et_newmonth_updateexpenseactivity.getText().toString();
            week = et_newweek_updateexpenseactivity.getText().toString();
            date = et_newdate_updateexpenseactivity.getText().toString();
            category = et_newcategory_updateexpenseactivity.getText().toString();
            amount = et_newamount_updateexpenseactivity.getText().toString();
            int count = userdbhelper.updateinformation(search_category, search_date,year, month, week, date, category, amount, sqlitedatabase);
            Toast.makeText(this, "" + count + " Row Updated", Toast.LENGTH_SHORT).show();
        }
    }
    public void clearresults(View view){
        et_newyear_updateexpenseactivity.setText("");
       et_searchmonth_updateexpenseactivity.setText("");
        et_searchcategory_updateexpenseactivity.setText("");
        et_searchdate_updateexpenseactivity.setText("");
        et_newcategory_updateexpenseactivity.setText("");
        et_newdate_updateexpenseactivity.setText("");
        et_newweek_updateexpenseactivity.setText("");
        et_newamount_updateexpenseactivity.setText("");
        et_newmonth_updateexpenseactivity.setText("");
    }
}

