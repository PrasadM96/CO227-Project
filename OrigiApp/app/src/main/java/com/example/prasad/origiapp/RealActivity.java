package com.example.prasad.origiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RealActivity extends AppCompatActivity {

    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

/*
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent =new Intent(RealActivity.this,HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });*/
    }

    private   BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;

                    switch(menuItem.getItemId()){
                        case R.id.navi_home:
                            selectedFragment =new HomeFragment();
                            break;
                        case R.id.navi_consumption:
                            selectedFragment =new ConsumptionFragment();
                            break;
                        case R.id.navi_control:
                            selectedFragment =new ControlFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.settings,menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id =menuItem.getItemId();

        if(id==R.id.logout){
            startActivity(new Intent(RealActivity.this,HomeActivity.class));
            finish();
        }

        return true;

    }




}
