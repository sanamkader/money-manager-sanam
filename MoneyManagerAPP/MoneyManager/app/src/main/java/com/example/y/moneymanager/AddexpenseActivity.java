package com.example.y.moneymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddexpenseActivity extends AppCompatActivity {
    EditText et_enteryear;
    EditText et_entermonth;
    EditText et_enterweek;
    EditText et_enterdate;
    EditText et_entercategory;
    EditText et_enterexpense;
    Button bt_addexpenseactivitybutton;
    Context context = this;
    UserDbhelper userDbhelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);

        et_enteryear = (EditText) findViewById(R.id.et_enteryear);
        et_entermonth = (EditText) findViewById(R.id.et_entermonth);
        et_enterweek = (EditText) findViewById(R.id.et_enterweek);
        et_enterdate = (EditText) findViewById(R.id.et_enterdate);
        et_entercategory = (EditText) findViewById(R.id.et_entercategory);
        et_enterexpense = (EditText) findViewById(R.id.et_enterexpense);
        bt_addexpenseactivitybutton = (Button) findViewById(R.id.bt_addexpenseactivitybutton);

        bt_addexpenseactivitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addexpense(view);
            }
        });


    }
    public void addexpense(View view){
        if(et_enteryear.getText().toString().length()==0){
            Toast.makeText(context, "enter year", Toast.LENGTH_SHORT).show();
            return;
        }
      else if(et_entermonth.getText().toString().length()==0){
          Toast.makeText(context, "enter month", Toast.LENGTH_SHORT).show();
          return;
      }
       else if(et_enterweek.getText().toString().length()==0){
            Toast.makeText(context, "enter week", Toast.LENGTH_SHORT).show();
            return;
        }
       else if(et_enterdate.getText().toString().length()==0){
            Toast.makeText(context, "enter date", Toast.LENGTH_SHORT).show();
            return;
        }
       else if(et_entercategory.getText().toString().length()==0){
            Toast.makeText(context, "enter category", Toast.LENGTH_SHORT).show();
            return;
        }
       else if(et_enterexpense.getText().toString().length()==0){
            Toast.makeText(context, "enter expense", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            String year = et_enteryear.getText().toString();
            String month = et_entermonth.getText().toString();
            String week = et_enterweek.getText().toString();
            String date = et_enterdate.getText().toString();
            String category = et_entercategory.getText().toString();
            String expense = et_enterexpense.getText().toString();

            userDbhelper = new UserDbhelper(context);
            sqLiteDatabase = userDbhelper.getWritableDatabase();
            userDbhelper.addinformation(year,month, week, date, category, expense, sqLiteDatabase);
            Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show();
            userDbhelper.close();
        }
    }


}
