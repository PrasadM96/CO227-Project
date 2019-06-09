package com.example.prasad.origiapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertData {

    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;
    int MSerial,Warning,Limit;

    public InsertData(int mserial, int warning, int limit){
        this.MSerial = mserial;
        this.Warning = warning;
        this.Limit = limit;
        this.insertdata(MSerial,Warning,Limit);
    }

    public void insertdata(int MSerial, int Warning, int Limit) {

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
                String query = "SELECT * FROM [DB_A48F31_ceb].[dbo].[MeterWarning] where MSerial = "+MSerial+"";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(!rs.next()) {
                    // Change below query according to your own database.
                    String query2 = "INSERT INTO [DB_A48F31_ceb].[dbo].[MeterWarning] VALUES ( "+MSerial+","+Warning+","+Limit+")";
                    Statement stmt2 = connect.createStatement();
                    stmt2.executeUpdate(query2);
                }else{
                    String query2 = "UPDATE [DB_A48F31_ceb].[dbo].[MeterWarning] SET Warning = "+Warning+", Limit = "+Limit+" WHERE MSerial = "+MSerial;
                    //String query2 = "DELETE * FROM [DB_A48F31_ceb].[dbo].[MeterWarning]";
                    Statement stmt2 = connect.createStatement();
                    stmt2.executeUpdate(query2);
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
    }

}
