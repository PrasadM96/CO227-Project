package com.example.prasad.origiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

import java.util.List;
import java.util.Map;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class RealActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Button dailyConsumption,monthlyConsumption;
    //TextView relay_status;
    String relay,ConsumerNo,MeterNo,monthlyConsumption,relayStatus,warning;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real);

        Intent in = getIntent();
        ConsumerNo = in.getStringExtra("ConsumerNo");
        MeterNo = in.getStringExtra("MeterNo");
        monthlyConsumption = in.getStringExtra("monthlyConsumption");
        relayStatus = in.getStringExtra("relayStatus");
        warning = in.getStringExtra("warning");

        homeFragment=new HomeFragment(monthlyConsumption,relayStatus);

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent=new Intent(this,ServiceStart.class);
        startService(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    private   BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                Fragment selectedFragment=null;
                String str=null;
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch(menuItem.getItemId()){
                        case R.id.navi_home:
                            str="Home";
                            selectedFragment =homeFragment;
                            break;
                        case R.id.navi_consumption:
                            str="Consumption";
                            selectedFragment =new ConsumptionFragment();
                            break;

                        case R.id.navi_control:
                            str="Control";
                            selectedFragment =new ControlFragment(MeterNo);
                            break;

                    }

                    toolbar.setTitle(str);
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
    Fragment selectedFragment1=null;
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_draw_home) {
            toolbar.setTitle("Home");
            bottomNavigationView.setVisibility(VISIBLE);
            selectedFragment1 = homeFragment;
        } else if (id == R.id.nav_draw_profile) {
            toolbar.setTitle("Profile");
            bottomNavigationView.setVisibility(INVISIBLE);
            selectedFragment1 =new ProfileFragment(ConsumerNo,MeterNo);
        } else if (id == R.id.nav_draw_contact) {
            toolbar.setTitle("Contact");
            bottomNavigationView.setVisibility(INVISIBLE);
            selectedFragment1 =new ContactUsFragment();
        } else if (id == R.id.nav_draw_help) {
            toolbar.setTitle("Help");
            bottomNavigationView.setVisibility(INVISIBLE);
            selectedFragment1 =new HelpFragment();
        }
        else if (id == R.id.nav_draw_about_us) {
            toolbar.setTitle("About Us");
            bottomNavigationView.setVisibility(INVISIBLE);
            selectedFragment1 = new AboutUsFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment1).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
