package com.example.prasad.origiapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetControlData {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    public boolean getdata(int MSerial) {

        try
        {
            ConnectionHelper conStr=new ConnectionHelper();
            connect =conStr.connectionclasss();        // Connect to database
            if (connect == null)
            {
                ConnectionResult = "Check Your Internet Access!";
            }
            else
            {
                // Change below query according to your own database.
                String query = "SELECT * FROM [DB_A48F31_ceb].[dbo].[MeterWarning] where MSerial = "+MSerial+"";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next()){
                    isSuccess=true;
                }

                ConnectionResult = " successful";
                connect.close();
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }

        return isSuccess;
    }
}
