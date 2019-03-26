package com.example.prasad.origiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    CardView cardview = (CardView) findViewById(R.id.cardView); // creating a CardView and assigning a value.

    cardview.OnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent homeIntent =new Intent(HomeActivity.this,RealActivity.class);
            startActivity(homeIntent);
            finish();
        }
    });





}
