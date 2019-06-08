package com.example.prasad.origiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MonthlyConsumption extends AppCompatActivity {

    ListView monthlyConsumption;
    SimpleAdapter ad;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_consumption);

        home = (Button) findViewById(R.id.home);

        List<Map<String,String>> myDataList = null;
        GetMonthlyConsumption getMonthlyConsumption = new GetMonthlyConsumption();
        myDataList = getMonthlyConsumption.getdata();

        Toast.makeText(MonthlyConsumption.this,(String)myDataList.get(0).values().toArray()[0],Toast.LENGTH_LONG).show();

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", Double.parseDouble((String)myDataList.get(0).values().toArray()[0])));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        cartesian.data(data);

        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(cartesian);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent =new Intent(MonthlyConsumption.this, RealActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
