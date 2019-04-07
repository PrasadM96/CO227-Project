package com.example.prasad.origiapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    String ip,db,DBUserNameStr,DBPasswordStr;

    @SuppressLint("NewApi")
    public Connection connectionclasss()
    {
        // Declaring Server ip, username, database name and password
        ip = "sql7005.site4now.net";
        db = "DB_A46EC4_ceb";
        DBUserNameStr = "DB_A46EC4_ceb_admin";
        DBPasswordStr = "aab-4962672";
        // Declaring Server ip, username, database name and password


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip +";databaseName="+ db + ";user=" + DBUserNameStr+ ";password=" + DBPasswordStr + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
