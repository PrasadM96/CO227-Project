package com.example.prasad.origiapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    TextView relay_status,consumption,usage1,current,last,projected;
    RingProgressBar ringProgressBar;
    String monthlyConsumption;

    int limit = 1000;
    //int value = (Integer.parseInt(monthlyConsumption)/limit)*100;
    int progress = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                if(progress < 100){
                    progress=5;
                    ringProgressBar.setProgress(progress);
                }
            }
        }
    };

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
        /*ringProgressBar.setOnProgressListener(new RingProgressBar.OnProgressListener()
        {

            @Override
            public void progressToComplete()
            {
                // Progress reaches the maximum callback default Max value is 100
                Toast.makeText(getActivity(), "complete", Toast.LENGTH_SHORT).show();
            }
        });*/

        List<Map<String,String>> myDataList1 = null;
        GetMonthlyConsumption getMonthlyConsumption = new GetMonthlyConsumption();
        myDataList1 = getMonthlyConsumption.getdata();

        monthlyConsumption = (String)myDataList1.get(0).values().toArray()[0];

        List<Map<String,String>> myDataList2 = null;
        GetRelayStatus getData = new GetRelayStatus();
        myDataList2 = getData.getdata();
        String a=myDataList2.get(0).values().toString();
        if(a.equals("[1]")){
            relay_status.setText("Relay Status : ON");
            consumption.setText(monthlyConsumption+" kWh");
        }else{
            relay_status.setText("Relay Status : OFF");
            consumption.setText(monthlyConsumption+" kWh");
        }

        usage1.setText("Total Usage:    "+monthlyConsumption+"     |     Load: 0.09kw      |     P.F:   1.00");
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
