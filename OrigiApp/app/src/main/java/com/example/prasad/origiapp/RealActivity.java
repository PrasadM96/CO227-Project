package com.example.prasad.origiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RealActivity extends AppCompatActivity {

    Button logout;
    TextView relay_status;

    String relay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real);

        logout = (Button) findViewById(R.id.logout);
        relay_status = (TextView) findViewById(R.id.relay_status);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent =new Intent(RealActivity.this,HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}
