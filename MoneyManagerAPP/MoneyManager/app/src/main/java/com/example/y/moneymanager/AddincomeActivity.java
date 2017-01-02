package com.example.y.moneymanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddincomeActivity extends AppCompatActivity {
    EditText et_monthinput;
    EditText et_incomeinput;
    Button bt_addincome;
    TextView tv_statusincome;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addincome);
        et_monthinput = (EditText) findViewById(R.id.et_monthinput);
        et_incomeinput = (EditText) findViewById(R.id.et_incomeinput);
        bt_addincome = (Button) findViewById(R.id.bt_addincome);
        tv_statusincome = (TextView) findViewById(R.id.tv_statusincome);

        sharedpreferences = getSharedPreferences("userincomeinfo",Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        bt_addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomeinput(view);
            }
        });


    }

    public void incomeinput(View view){
       if(et_monthinput.getText().toString().length()==0) {
           Toast.makeText(this, "Enter Month", Toast.LENGTH_SHORT).show();
       }
       else if(et_incomeinput.getText().toString().length()==0) {
            Toast.makeText(this, "Enter Income", Toast.LENGTH_SHORT).show();
        }
        else{
          String month = et_monthinput.getText().toString();
           String income = et_incomeinput.getText().toString();
           Log.e("AddincomeActivity","month "+month+" income "+income);

           if (month.equals("january")){
           editor.putString("january",month) ;
               editor.putString("januaryincome",income);
               editor.apply();
               Toast.makeText(this, " January, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("January , Income Saved");


           }
           else if (month.equals("february")){
               editor.putString("february",month) ;
               editor.putString("februaryincome",income);
               editor.apply();
               Toast.makeText(this, "February, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("February, Income Saved");


           }
          else if (month.equals("march")){
               editor.putString("march",month) ;
               editor.putString("marchincome",income);
               editor.apply();
               Toast.makeText(this, "March, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("March, Income Saved");


           }
          else if (month.equals("april")){
               editor.putString("april",month) ;
               editor.putString("aprilincome",income);
               editor.apply();
               Toast.makeText(this, "April, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("April, Income Saved");


           }
          else if (month.equals("may")){
               editor.putString("may",month) ;
               editor.putString("mayincome",income);
               editor.apply();
               Toast.makeText(this, "May, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("May, Income Saved");

           }
          else if (month.equals("june")){
               editor.putString("june",month) ;
               editor.putString("juneincome",income);
               editor.apply();
               Toast.makeText(this, "June, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("June, Income Saved");


           }
          else if (month.equals("july")){
               editor.putString("july",month) ;
               editor.putString("julyincome",income);
               editor.apply();
               Toast.makeText(this, "July, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("July, Income Saved");


           }
          else if (month.equals("august")){
               editor.putString("august",month) ;
               editor.putString("augustincome",income);
               editor.apply();
               Toast.makeText(this, "August, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("August, Income Saved");


           }
          else if (month.equals("september")){
               editor.putString("september",month) ;
               editor.putString("septemberincome",income);
               editor.apply();
               Toast.makeText(this, "September, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("September, Income Saved");


           }
          else if (month.equals("october")){
               editor.putString("october",month) ;
               editor.putString("octoberincome",income);
               editor.apply();
               Toast.makeText(this, "October, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("October, Income Saved");


           }
          else if (month.equals("november")){
               editor.putString("november",month) ;
               editor.putString("novemberincome",income);
               editor.apply();
               Toast.makeText(this, "November, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("November, Income Saved");


           }
          else if (month.equals("december")){
               editor.putString("december",month) ;
               editor.putString("decemberincome",income);
               editor.apply();
               Toast.makeText(this, "December, Income Saved", Toast.LENGTH_SHORT).show();
               tv_statusincome.setVisibility(View.VISIBLE);
               tv_statusincome.setText("December, Income Saved");


           }

           }

       }



}
