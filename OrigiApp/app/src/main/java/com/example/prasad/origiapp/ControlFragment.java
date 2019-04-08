package com.example.prasad.origiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ControlFragment extends Fragment {

    Button dailyConsumption,monthlyConsumption,profile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_control,container,false);

        dailyConsumption = (Button) view.findViewById(R.id.dailyConsumption);
        monthlyConsumption = (Button) view.findViewById(R.id.monthlyConsumption);
        profile =(Button) view.findViewById(R.id.profile);

        dailyConsumption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent =new Intent(getActivity(),DailyConsumption.class);
                startActivity(homeIntent);
            }
        });

        monthlyConsumption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent =new Intent(getActivity(),MonthlyConsumption.class);
                startActivity(homeIntent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent =new Intent(getActivity(),Profile.class);
                startActivity(homeIntent);
            }
        });

        return view;
    }
}

/**Button logout;
    ListView listData;
    SimpleAdapter ad;
    String relay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        listData = (ListView) findViewById(R.id.listData);
        //relay_status = (TextView) findViewById(R.id.relay);

        List<Map<String,String>> myDataList = null;
        GetDailyConsumption getData = new GetDailyConsumption();
        myDataList = getData.getdata();
        String[] fromwhere = { "Date","Consumption"};

        int[] viewswhere = {R.id.Date, R.id.Consumption};

        ad = new SimpleAdapter(RealActivity.this, myDataList,R.layout.list_template, fromwhere, viewswhere);

        listData.setAdapter(ad);
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

    }**/