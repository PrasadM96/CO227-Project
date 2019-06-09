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
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener{

    // Declaring layout button, edit texts
    Button login;
    EditText serial,password;
    //ProgressBar progressBar;
    // End Declaring layout button, edit texts

    // Declaring connection variables
    Connection con;
    String un,pass,db,ip;
    static String seria,passwordd;
    String relay,monthlyConsumption;
    //End Declaring connection variables
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        //End Setting up the function when button login is

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        rem_userpass = (CheckBox)findViewById(R.id.checkBox);

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);

        serial.setText(sharedPreferences.getString(KEY_USERNAME,""));
        password.setText(sharedPreferences.getString(KEY_PASS,""));

        serial.addTextChangedListener(this);
        password.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();
    }
    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs(){
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERNAME, serial.getText().toString().trim());
            editor.putString(KEY_PASS, password.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
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
                v2.setTextColor(Color.RED);
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
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    con = connectionHelper.connectionclasss();        // Connect to database
                    if (con == null)
                    {
                        z = "Check Your Internet Access!";
                    }
                    else
                    {
                        String query = "SELECT * FROM [DB_A48F31_ceb].[dbo].[CustomerMeterRelation] where MSerial= '" + seria.toString() + "' and ConsumerAccountNo = '"+ passwordd.toString() +"' ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            z = "Login successful";
                            isSuccess=true;
                            Intent homeIntent =new Intent(HomeActivity.this,RealActivity.class);
                            homeIntent.putExtra("ConsumerNo",passwordd);
                            homeIntent.putExtra("MeterNo",seria);
                            List<Map<String,String>> myDataList1 = null;
                            GetMonthlyConsumption getMonthlyConsumption = new GetMonthlyConsumption();
                            myDataList1 = getMonthlyConsumption.getdata();

                            monthlyConsumption = (String)myDataList1.get(0).values().toArray()[0];
                            homeIntent.putExtra("monthlyConsumption",monthlyConsumption);

                            List<Map<String,String>> myDataList2 = null;
                            GetRelayStatus getData = new GetRelayStatus();
                            myDataList2 = getData.getdata();
                            String a=myDataList2.get(0).values().toString();
                            homeIntent.putExtra("relayStatus",a);

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

    public String getUsernam() {
        return seria;
    }

    public void toreal(View view){
        Intent realIntent =new Intent(HomeActivity.this,RealActivity.class);
        startActivity(realIntent);
        finish();
    }

    public String getRelay() {
        return relay;
    }

}
