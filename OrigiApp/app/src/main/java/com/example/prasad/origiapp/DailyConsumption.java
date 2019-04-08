package com.example.prasad.origiapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DailyConsumption extends AppCompatActivity implements OnChartValueSelectedListener,OnChartGestureListener{

    LineChart dailyChart;
    private static final String TAG = "DailyConsumption";

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG,"onChartGestureStart: X "+me.getX()+"Y: "+me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG,"onChartGestureEnd: X "+lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG,"onChartLongPressed");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG,"onChartDoubleTapped");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG,"onChartSingleTapped");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG,"onChartFling: veloX: "+velocityX+"veloY: "+velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG,"onChartScale: scaleX: "+scaleX+"scaleY: "+scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG,"onChartTranslate: dX: "+dX+"dY: "+dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG,"onValueSelected: "+e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG,"onNothingSelected: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_consumption);

        dailyChart = (LineChart) findViewById(R.id.dailyChart);

        List<Map<String,String>> myDataList = null;
        GetDailyConsumption getData = new GetDailyConsumption();
        myDataList = getData.getdata();

        dailyChart.setOnChartGestureListener(this);
        dailyChart.setOnChartValueSelectedListener(this);

        dailyChart.setDragEnabled(true);
        dailyChart.setScaleEnabled(false);
        dailyChart.getAxisRight().setEnabled(false);
        dailyChart.getXAxis().setDrawGridLines(false);
        dailyChart.getAxisLeft().setDrawGridLines(false);
        dailyChart.getAxisRight().setDrawGridLines(false);
        dailyChart.getDescription().setEnabled(false);
        dailyChart.setTouchEnabled(true);
        dailyChart.setOnChartValueSelectedListener(this);
        dailyChart.setScaleEnabled(true);
        dailyChart.setDragEnabled(true);
        dailyChart.setPinchZoom(true);
        dailyChart.getLegend().setEnabled(false);
        dailyChart.animateXY(1500,1500);

        ArrayList<Entry> yValues = new ArrayList<>();
        //yValues.add(new Entry(0,Float.parseFloat((String)myDataList.get(0).values().toArray()[0])));
        yValues.add(new Entry(1,500f));
        yValues.add(new Entry(2,700f));
        yValues.add(new Entry(3,300f));
        yValues.add(new Entry(4,500f));
        yValues.add(new Entry(5,600f));
        yValues.add(new Entry(6,650f));
        yValues.add(new Entry(7,250f));
        yValues.add(new Entry(8,1100f));
        yValues.add(new Entry(9,800f));
        yValues.add(new Entry(10,1150f));
        yValues.add(new Entry(11,100f));
        yValues.add(new Entry(12,500f));
        yValues.add(new Entry(13,700f));
        yValues.add(new Entry(14,300f));
        yValues.add(new Entry(15,500f));
        yValues.add(new Entry(16,600f));
        yValues.add(new Entry(17,650f));
        yValues.add(new Entry(18,250f));
        yValues.add(new Entry(19,1100f));
        yValues.add(new Entry(20,800f));
        yValues.add(new Entry(21,1150f));
        yValues.add(new Entry(22,100f));
        yValues.add(new Entry(23,500f));
        yValues.add(new Entry(24,700f));
        yValues.add(new Entry(25,300f));
        yValues.add(new Entry(26,500f));
        yValues.add(new Entry(27,600f));
        yValues.add(new Entry(28,650f));
        yValues.add(new Entry(29,250f));
        yValues.add(new Entry(30,1100f));
        yValues.add(new Entry(31,1100f));

        LineDataSet set1 = new LineDataSet(yValues,"");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);

        dailyChart.setData(data);
        dailyChart.setVisibleXRangeMaximum(15);

        set1.setFillAlpha(110);
        set1.setColor(Color.BLUE);
        set1.setValueTextSize(10f);
        set1.setLineWidth(3.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setCircleColor(Color.RED);
        set1.setHighLightColor(Color.WHITE);

        XAxis xAxis = dailyChart.getXAxis();
        xAxis.setLabelCount(12);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);

    }
}
