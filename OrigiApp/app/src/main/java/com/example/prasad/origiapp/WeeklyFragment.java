package com.example.prasad.origiapp;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyFragment<OnChartValueSelectedListene> extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener{
    private static final String TAG = "WeeklyFragment";
    private LineChart dailyChart;
    View fragView;


    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

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


    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG,"onValueSelected: "+e.toString());
    }


    public void onNothingSelected() {
        Log.i(TAG,"onNothingSelected: ");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         fragView= inflater.inflate(R.layout.fragment_weekly, container, false);

        Button button1 =  fragView.findViewById(R.id.bweekly);
        Button button2 =  fragView.findViewById(R.id.bdaily);
        Button button3 =  fragView.findViewById(R.id.bmonthly);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = new WeeklyFragment();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();// Do something in response to button click
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = new MonthlyFragment();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();// Do something in response to button click
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = new ConsumptionFragment();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.commit();// Do something in response to button click
            }
        });
        drawchart();
        return fragView;

    }

    public void drawchart(){
        dailyChart = (LineChart) fragView.findViewById(R.id.linechart);
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
        yValues.add(new Entry(1,388.36f));
        yValues.add(new Entry(2,421.25f));
        yValues.add(new Entry(3,407.69f));
        yValues.add(new Entry(4,371.58f));

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

