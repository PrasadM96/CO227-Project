package com.example.prasad.origiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class DailyConsumption extends AppCompatActivity {

    ListView dailyConsumption;
    SimpleAdapter ad;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_consumption);

        dailyConsumption = (ListView) findViewById(R.id.dailyConsumption);
        home = (Button) findViewById(R.id.home);

        List<Map<String,String>> myDataList = null;
        GetDailyConsumption getData = new GetDailyConsumption();
        myDataList = getData.getdata();
        String[] fromwhere = { "Date","Consumption"};

        int[] viewswhere = {R.id.Date, R.id.Cosumption};

        ad = new SimpleAdapter(DailyConsumption.this, myDataList,R.layout.list_template, fromwhere, viewswhere);

        dailyConsumption.setAdapter(ad);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent =new Intent(DailyConsumption.this, RealActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
