package com.example.prasad.origiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    EditText et_username,et_password;
    Button login;
    DatabaseReference dreff;
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //FirebaseApp.initializeApp(HomeActivity.this);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.login);
        member = new Member();
        dreff = FirebaseDatabase.getInstance().getReference().child("Member");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setUsername(et_username.getText().toString().trim());
                member.setPassword(et_password.getText().toString());

                dreff.push().setValue(member);
                Intent homeIntent =new Intent(HomeActivity.this,RealActivity.class);
                startActivity(homeIntent);
                finish();
                Toast.makeText(HomeActivity.this,"data successfully added",Toast.LENGTH_LONG).show();
            }
        });
        //Toast.makeText(HomeActivity.this,"Firebase connection succes",Toast.LENGTH_LONG).show();
    }


}
