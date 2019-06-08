package com.example.prasad.origiapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMonthlyConsumption {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;

    public List<Map<String,String>> getdata() {

        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
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
                String query = "SELECT Month,kWh FROM [DB_A48F31_ceb].[dbo].[MonthlyConsumptionValidateTable]";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Map<String,String> datanum=new HashMap<String,String>();
                    datanum.put("Month",rs.getString("Month"));
                    datanum.put("Consumption",rs.getString("kWh"));
                    data.add(datanum);
                }

                ConnectionResult = " successful";
                isSuccess=true;
                connect.close();
            }
        }
        catch (Exception ex)
        {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }

        return data;
    }
}
