package com.example.y.moneymanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchanddeleteActivity extends AppCompatActivity {
    EditText et_searchcategory;
    EditText et_searchmonth;

    EditText et_searchdate;
    TextView tv_searchresults;

    Button bt_searchbutton;
    Button bt_deletebutton;
    Button bt_clear_searchanddeleteactivity;
    String searchcategory;
    String searchmonth,searchdate;
    String results ="";
    UserDbhelper userDbhelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchanddelete);
        et_searchcategory = (EditText) findViewById(R.id.et_searchcategory);
        et_searchmonth = (EditText) findViewById(R.id.et_searchmonth);
        et_searchdate = (EditText) findViewById(R.id.et_searchdate);

        tv_searchresults = (TextView) findViewById(R.id.tv_searchresults);

        bt_searchbutton = (Button) findViewById(R.id.bt_searchcategory);
        bt_deletebutton = (Button) findViewById(R.id.bt_deletecategory);
        bt_clear_searchanddeleteactivity= (Button) findViewById(R.id.bt_clear_searchanddeleteactivity);

        bt_searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchinfo(view);
            }
        });
        bt_deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteinfo(view);
            }
        });
        bt_clear_searchanddeleteactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearresults(view);
            }
        });

    }

    public void searchinfo(View view){
        if(et_searchmonth.getText().toString().length()==0) {
            Toast.makeText(this, "Enter Month", Toast.LENGTH_SHORT).show();
        }
       else if(et_searchcategory.getText().toString().length()==0) {
            Toast.makeText(this, "Enter Category", Toast.LENGTH_SHORT).show();
        }
       else if(et_searchdate.getText().toString().length()==0) {
            Toast.makeText(this, "Enter Date", Toast.LENGTH_SHORT).show();
        }
        else {
            searchmonth = et_searchmonth.getText().toString();
            searchcategory = et_searchcategory.getText().toString();
            searchdate = et_searchdate.getText().toString();


            userDbhelper = new UserDbhelper(getApplicationContext());
            sqLiteDatabase = userDbhelper.getReadableDatabase();
            Cursor cursor = userDbhelper.searchanddelete(searchmonth, searchdate, searchcategory, sqLiteDatabase);
            if (cursor.moveToFirst()) {
                do {
                    results += cursor.getString(0);
                    results += " ";
                    results += cursor.getString(1);
                    results += " ";
                    results += cursor.getString(2);
                    results += " ";
                    results += cursor.getString(3);
                    results += "\n";
                } while (cursor.moveToNext());
            }
            if (results.length() == 0) {
                tv_searchresults.setVisibility(View.VISIBLE);
                tv_searchresults.setText("No results");
                Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
            } else {
                tv_searchresults.setVisibility(View.VISIBLE);
                tv_searchresults.setText(results);
                results="";
                Toast.makeText(this, "Results available", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void deleteinfo(View view){
        userDbhelper = new UserDbhelper(getApplicationContext());
        sqLiteDatabase = userDbhelper.getReadableDatabase();
        userDbhelper.deleteinformation(searchcategory,sqLiteDatabase);
        tv_searchresults.setText(" ");
        Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
    }
    public void clearresults(View view){
        et_searchmonth.setText("");
        et_searchcategory.setText("");
        et_searchdate.setText("");
        tv_searchresults.setText("");
    }



}
