package com.example.y.moneymanager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_addexpense_mainactivity;
    Button bt_showexpense_mainactivity;
    Button bt_showreports_mainactivity;
    Button bt_searchanddelete_mainactivity;
    Button bt_updateexpense_mainactivity;
    Button bt_income_mainactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Money Manager");
        actionBar.setLogo(R.drawable.moneymanagerlogo);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        bt_addexpense_mainactivity = (Button) findViewById(R.id.bt_addexpense_mainactivity);
        bt_showexpense_mainactivity = (Button) findViewById(R.id.bt_showexpense_mainactivity);
        bt_showreports_mainactivity = (Button) findViewById(R.id.bt_showreports_mainactivity);
        bt_searchanddelete_mainactivity = (Button) findViewById(R.id.bt_searchanddelete_mainactivity);
        bt_updateexpense_mainactivity = (Button) findViewById(R.id.bt_updateexpense_mainactivity);
        bt_income_mainactivity = (Button) findViewById(R.id.bt_income_mainactivity);

        bt_addexpense_mainactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddexpenseActivity.class);
                startActivity(intent);
            }
        });

        bt_showexpense_mainactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DatalistActivity.class);
                startActivity(intent);
            }
        });

        bt_searchanddelete_mainactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SearchanddeleteActivity.class);
                startActivity(intent);

            }
        });

        bt_updateexpense_mainactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UpdateexpenseActivity.class);
                startActivity(intent);
            }
        });
        bt_showreports_mainactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ReportsActivity.class);
                startActivity(intent);
            }
        });
        bt_income_mainactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddincomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
