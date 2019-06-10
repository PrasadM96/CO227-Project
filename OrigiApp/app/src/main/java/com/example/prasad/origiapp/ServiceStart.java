package com.example.prasad.origiapp;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class ServiceStart extends Service {

    private static final String CHANNEL_ID="Simplified_coding";
    private static final String CHANNEL_NAME="Simplified_coding";
    private static final String CHANNEL_DESC="Simplified_coding Notification";
    private Double warn;
    private Double consumption;
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    @Override
    public void onCreate() {
        warn=0.0;
        consumption=0.0;
        //creating notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager=getApplication().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this,"Started",Toast.LENGTH_LONG).show();
        checkWarning();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void displayNotification(String Title,String msg){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class); // Here pass your activity where you want to redirect.

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), (int) (Math.random() * 100), intent, 0);

        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(Title)
                        .setContentText(msg)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setDefaults(Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                        .setContentIntent(contentIntent);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(1,nBuilder.build());
    }


    ////////////////////////////////////////////////
    private void checkWarning() {
        try
        {
            ConnectionHelper conStr=new ConnectionHelper();
            connect =conStr.connectionclasss();        // Connect to database
            if (connect == null)
            {
                ConnectionResult = "Check Your Internet Access!";
            }
            else {
               // Toast.makeText(this,"fsfsf",Toast.LENGTH_LONG).show();

                String query = "SELECT Warning FROM [DB_A48F31_ceb].[dbo].[MeterWarning] WHERE MSerial = " +Integer.parseInt(HomeActivity.seria)+ "";
                String query2 = "SELECT kWh FROM [DB_A48F31_ceb].[dbo].[MonthlyConsumptionValidateTable] where MSerial = "+Integer.parseInt(HomeActivity.seria)+"";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                Statement stmt2 = connect.createStatement();
                ResultSet rs2 =stmt2.executeQuery(query2);

                if(rs2.next() && rs.next()){
                    warn=Double.parseDouble(rs.getString("Warning"));
                    consumption=Double.parseDouble(rs2.getString("kWh"));
                    if(consumption>warn){
                        displayNotification("Warning","Reduce usage");
                        //Toast.makeText(this,"ok "+warn+" "+consumption,Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
                }

                ConnectionResult = " successful";
                isSuccess=true;
                connect.close();

            }
        }catch (Exception ex){
            Toast.makeText(this,ex.toString(),Toast.LENGTH_LONG).show();
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }
    }

}
