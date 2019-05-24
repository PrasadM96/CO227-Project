package com.example.prasad.origiapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {


    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragview=inflater.inflate(R.layout.fragment_about_us, container, false);
        // Inflate the layout for this fragment
        fragview.findViewById(R.id.fb_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://www.facebook.com/sandushi.dileka");
            }
        });

        fragview.findViewById(R.id.insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://www.facebook.com/sandushi.dileka");
            }
        });

        fragview.findViewById(R.id.twit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://www.facebook.com/priman.grashly?ref=br_rs");
            }
        });
        return fragview;
    }


    public void clicked_btn(String url){

        Intent facebookIntent=new Intent(Intent.ACTION_VIEW);
        // facebookIntent.addCategory(Intent.CATEGORY_BROWSABLE);
        facebookIntent.setData(Uri.parse(url));
        startActivity(facebookIntent);


    }
}





