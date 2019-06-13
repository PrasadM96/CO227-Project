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
public class MonthlyFragment extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener {



    private static final String TAG = "MonthlyFragment";
    private LineChart lineChart;
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

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG,"onValueSelected: "+e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG,"onNothingSelected: ");}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         fragView=inflater.inflate(R.layout.fragment_monthly, container, false);


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
        lineChart = (LineChart) fragView.findViewById(R.id.linechart);
        List<Map<String,String>> myDataList = null;
        GetData getMonthlyConsumption = new GetData();
        myDataList = getMonthlyConsumption.getMonthlyConsumption();

        lineChart.setOnChartGestureListener(this);
        lineChart.setOnChartValueSelectedListener(this);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setTouchEnabled(true);
        lineChart.setOnChartValueSelectedListener(this);
        lineChart.setScaleEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.getLegend().setEnabled(false);
        lineChart.animateXY(1500,1500);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0,Float.parseFloat((String)myDataList.get(0).values().toArray()[0])));
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

        LineDataSet set1 = new LineDataSet(yValues,"");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        LineData data = new LineData(dataSets);

        lineChart.setData(data);

        set1.setFillAlpha(110);
        set1.setColor(Color.BLUE);
        set1.setValueTextSize(10f);
        set1.setLineWidth(3.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setCircleColor(Color.RED);
        set1.setHighLightColor(Color.WHITE);

        ArrayList<String> xVals = new ArrayList<>();
        xVals.add("Jan");
        xVals.add("Feb");
        xVals.add("Mar");
        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");
        xVals.add("Jul");
        xVals.add("Aug");
        xVals.add("Sep");
        xVals.add("Oct");
        xVals.add("Nov");
        xVals.add("Dec");
        //String[] values = new String[] {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelCount(12);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
        xAxis.setTextSize(12f);
        xAxis.setGranularity(1);
    }


}
