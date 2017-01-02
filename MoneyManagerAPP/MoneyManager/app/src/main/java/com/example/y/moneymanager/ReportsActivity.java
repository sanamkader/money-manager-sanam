package com.example.y.moneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReportsActivity extends AppCompatActivity {
    Button bt_dailyreportsearch_reportactivity;
    Button bt_weeklyreportsearch_reportactivity;
    Button bt_monthlyreportsearch_reportactivity;
    Button bt_yearlyreportsearch_reportactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        bt_dailyreportsearch_reportactivity = (Button) findViewById(R.id.bt_dailyreportsearch_reportactivity);
        bt_monthlyreportsearch_reportactivity = (Button) findViewById(R.id.bt_monthlyreportsearch_reportactivity);
        bt_weeklyreportsearch_reportactivity = (Button) findViewById(R.id.bt_weeklyreportsearch_reportactivity);
        bt_yearlyreportsearch_reportactivity = (Button) findViewById(R.id.bt_yearlyreportsearch_reportactivity);
        bt_dailyreportsearch_reportactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportsActivity.this,DailyreportsearchActivity.class);
                startActivity(intent);

            }
        });
        bt_weeklyreportsearch_reportactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(ReportsActivity.this,WeeklyreportsearchActivity.class);
                startActivity(intent);
            }
        });
        bt_monthlyreportsearch_reportactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportsActivity.this,MonthlyreportsearchActivity.class);
                startActivity(intent);
            }
        });

        bt_yearlyreportsearch_reportactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportsActivity.this,YearlyreportsearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
