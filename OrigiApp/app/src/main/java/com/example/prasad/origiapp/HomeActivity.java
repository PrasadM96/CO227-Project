package com.example.prasad.origiapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    EditText pas,usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        usr = (EditText) findViewById( R.id.et_username);
        pas = (EditText) findViewById(R.id.et_password);

    }

    public void login(View view){
        String user = usr.getText().toString();
        String pass = pas.getText().toString();

        background bg = new background(this);
        bg.execute(user,pass);
    }

    public void toreal(View view){
        Intent realIntent =new Intent(HomeActivity.this,RealActivity.class);
        startActivity(realIntent);
        finish();
    }

}
