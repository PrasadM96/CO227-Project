package com.example.prasad.origiapp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.jar.Manifest;

import static android.content.Context.*;
import static android.support.v4.app.ActivityCompat.*;


public class ContactUsFragment extends Fragment implements LocationListener{

    private static final int REQUEST_CALL = 1;
    private EditText meditText;
    private LocationManager locationManager;
    private TextView locationText;

    private static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 1;
    private static final int  MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private Button getLocationBtn;
    private AutoCompleteTextView atcv;
    private double longitude, latitude;
    private Map<String,String> map;

    private static final String[] CITIES = new String[]{
            "Alfkfs","Awkfjnsjk","Mirigama","Colombo"
    };

    public ContactUsFragment(){
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View fragview = inflater.inflate(R.layout.fragment_contact_us, container, false);
        locationText=fragview.findViewById(R.id.locationText);

        map =new HashMap<String,String>();
        map.put("Mirigama","0377910499");
        map.put("Veyangoda","123");
        map.put("Kandy","0769374789");


        map.put("Kandy","09876545");


        map.put("Kandy","09876545");

        //calling
        meditText = fragview.findViewById(R.id.edit_text);
        ImageView imgCall = fragview.findViewById(R.id.phone_call);
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makecall();
            }
        });

        //location
        getLocationBtn = fragview.findViewById(R.id.getLocationBtn);


        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        /////////////////////////////////////////
        String[] countries = getResources().getStringArray(R.array.countries);

        atcv = fragview.findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.custom_list_item, R.id.text_view_list_item, countries);
        atcv.setAdapter(adapter);
        //editText.setThreshold(1);

        atcv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phoneNumber=map.get(s.toString());
                meditText.setText(phoneNumber);
            }
        });
        //////////////////////////////////////


       return fragview;
    }



    /////////////////////////////////////////
    private void getLocation() {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {


        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

      // locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());


        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
      // locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
         /*   locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));*/

           // locationText.setText(locationText.getText() + "\n"+
                   // addresses.get(0).getLocality());
            atcv.setText(addresses.get(0).getSubAdminArea());

        }catch(Exception e)
        {

        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
       /* Toast.makeText(getContext()
                , "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();*/


            new AlertDialog.Builder(getContext())
                    .setTitle("Required GPS Permission")
                    .setMessage("You have to give this permission to access the feature")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

                        }
                    })
                   /* .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })*/
                    .create()
                    .show();
        }

//////////////////////////////////making a call/////////////////////////////////
    private void makecall(){
        if (ContextCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(),
                    android.Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(getContext())
                        .setTitle("Required Call Permission")
                        .setMessage("You have to give this permission to access the feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions((Activity) getContext(),
                                        new String[]{android.Manifest.permission.CALL_PHONE},
                                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions((Activity) getContext(),
                        new String[]{android.Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Intent callIntent =new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+meditText.getText().toString()));
            if (ActivityCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) getContext(),
                        new String[]{android.Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }

            startActivity(callIntent);
        }

    }


}
