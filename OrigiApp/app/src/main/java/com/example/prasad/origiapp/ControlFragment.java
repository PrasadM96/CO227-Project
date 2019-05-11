package com.example.prasad.origiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;


public class ControlFragment extends Fragment {

    private static final String CHANNEL_ID="Simplified_coding";
    private static final String CHANNEL_NAME="Simplified_coding";
    private static final String CHANNEL_DESC="Simplified_coding Notification";
    private EditText warning;
    private EditText limit;
    private int consumption;
    private String warningValue=null;
    private String limiValue=null;
    private Button btn;
    private int warn,limit1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_control,container,false);
        consumption=500;//databse
        warn =0;
        limit1=0;

        warning = view.findViewById(R.id.warning);
        limit = view.findViewById(R.id.limit);
        btn =view.findViewById(R.id.submitbtn);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager=getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warningValue=warning.getText().toString();
                limiValue=limit.getText().toString();


                warn = Integer.parseInt("125");
                limit1 = Integer.parseInt(limiValue);
              //  Toast.makeText(getContext(),,Toast.LENGTH_SHORT).show();


            }
        });

        warn =100;

        if(warn<consumption && warn!=0){
            displayNotification("Warning","Reduce usage");
        }


        return view;
    }

    //notification display
    private void displayNotification(String Title,String msg){
        Intent intent = new Intent(getContext(), HomeActivity.class); // Here pass your activity where you want to redirect.

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), (int) (Math.random() * 100), intent, 0);

        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(getContext(),CHANNEL_ID)
                        .setSmallIcon(R.drawable.app)
                        .setContentTitle(Title)
                        .setContentText(msg)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setDefaults(Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                        .setContentIntent(contentIntent);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(1,nBuilder.build());
    }
}
