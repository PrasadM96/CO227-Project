package com.example.prasad.origiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeActivity extends AppCompatActivity {

    // Declaring layout button, edit texts
    Button login;
    EditText serial,password;
    //ProgressBar progressBar;
    // End Declaring layout button, edit texts

    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    String seria,passwordd;
    String relay;
    //End Declaring connection variables

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Getting values from button, texts and progress bar
        login = (Button) findViewById(R.id.login);
        serial = (EditText) findViewById(R.id.etSerial);
        password = (EditText) findViewById(R.id.etAccNo);

        // Setting up the function when button login is clicked
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                seria = serial.getText().toString();
                passwordd = password.getText().toString();
                CheckLogin checkLogin = new CheckLogin();// this is the Asynctask, which is used to process in background to reduce load on app process
                checkLogin.execute("");
            }
        });
        //End Setting up the function when button login is clicked
    }

    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute()

        {
            //progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r)
        {
            //progressBar.setVisibility(View.GONE);
            Toast toast = Toast.makeText(HomeActivity.this, r, Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.show();

            if(isSuccess)
            {
                Toast toast2 = Toast.makeText(HomeActivity.this , "Login Successfull" , Toast.LENGTH_LONG);
                TextView v2 = (TextView) toast2.getView().findViewById(android.R.id.message);
                v2.setTextColor(Color.rgb(0,100,0));
                toast2.show();
                //finish();
            }
        }
        @Override
        protected String doInBackground(String... params)
        {

            if(seria.trim().equals("")|| passwordd.trim().equals(""))
                z = "Please enter Serial and AccNo";
            else
            {
                try
                {
                    con = connectionclass(un, pass, db, ip);        // Connect to database
                    if (con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {
                        String query = "SELECT * FROM [DB_A46EC4_ceb].[dbo].[CustomerMeterRelation] where MSerial= '" + seria.toString() + "' and ConsumerAccountNo = '"+ passwordd.toString() +"' ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            z = "Login successful";
                            isSuccess=true;
                            Intent homeIntent =new Intent(HomeActivity.this,RealActivity.class);
                            startActivity(homeIntent);
                            finish();
                            con.close();
                        }
                        else
                        {
                            z = "Invalid Credentials!";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = ex.getMessage();
                }
            }
            return z;
        }
    }


    @SuppressLint("NewApi")
    public Connection connectionclass(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            ConnectionURL = "jdbc:jtds:sqlserver://sql7005.site4now.net;database=DB_A46EC4_ceb;user=DB_A46EC4_ceb_admin;password=aab-4962672";
            //ConnectionURL = "jdbc:jtds:sqlserver://192.168.1.9;database=msss;instance=SQLEXPRESS;Network Protocol=NamedPipes" ;

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
    public String getUsernam() {
        return seria;
    }

<<<<<<< HEAD
    public void toreal(View view){
        Intent realIntent =new Intent(HomeActivity.this,RealActivity.class);
        startActivity(realIntent);
        finish();
    }

=======
    public String getRelay() {
        return relay;
    }


>>>>>>> 9ddadb96824e4de582a424001f44b3317ec3e5f8
}
