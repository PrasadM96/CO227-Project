package com.example.prasad.origiapp;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class HomeFragment extends Fragment {

    TextView relay_status,consumption,usage1,usage2,usage3,current,last,projected;
    RingProgressBar ringProgressBar;
    String monthlyConsumption,relayStatus;

    //int limit = Integer.parseInt(monthlyConsumption);
    //int value = (Integer.parseInt(HomeActivity.MonthlyConsumption)/limit)*100;
    int progress = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                if(progress < 100){
                    progress=10;
                    ringProgressBar.setProgress(progress);
                }
            }
        }
    };

    public HomeFragment(){

    }

    @SuppressLint("ValidFragment")
    public HomeFragment(String monthlyConsumption, String relayStatus){
        this.monthlyConsumption = monthlyConsumption;
        this.relayStatus = relayStatus;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        relay_status = (TextView) view.findViewById(R.id.relayStatus) ;
        consumption = (TextView) view.findViewById(R.id.consumption);
        current = (TextView) view.findViewById(R.id.textView3);
        last = (TextView) view.findViewById(R.id.textView4);
        projected = (TextView) view.findViewById(R.id.textView5);
        ringProgressBar = (RingProgressBar) view.findViewById(R.id.progress_bar);
        usage1 = (TextView) view.findViewById(R.id.usage1);
        usage2 = (TextView) view.findViewById(R.id.usage2);
        usage3 = (TextView) view.findViewById(R.id.usage3);

        /*ringProgressBar.setOnProgressListener(new RingProgressBar.OnProgressListener()
        {

            @Override
            public void progressToComplete()
            {
                // Progress reaches the maximum callback default Max value is 100
                Toast.makeText(getActivity(), "complete", Toast.LENGTH_SHORT).show();
            }
        });*/

        /*List<Map<String,String>> myDataList1 = null;
        GetMonthlyConsumption getMonthlyConsumption = new GetMonthlyConsumption();
        myDataList1 = getMonthlyConsumption.getdata();

        monthlyConsumption = (String)myDataList1.get(0).values().toArray()[0];

        List<Map<String,String>> myDataList2 = null;
        GetRelayStatus getData = new GetRelayStatus();
        myDataList2 = getData.getdata();
        String a=myDataList2.get(0).values().toString();*/
        if(relayStatus.equals("[1]")){
            relay_status.setText("Relay Status : ON");
            consumption.setText(monthlyConsumption+" kWh");
        }else{
            relay_status.setText("Relay Status : OFF");
            consumption.setText(monthlyConsumption+" kWh");
        }

        usage1.setText(" Total Usage:    "+monthlyConsumption);
        usage2.setText("Load: 0.09kw");
        usage3.setText("P.F:   1.00 ");
        current.setText(monthlyConsumption);
        last.setText(monthlyConsumption);
        projected.setText(monthlyConsumption);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    try {
                        Thread.sleep(100);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return view;
    }
}
