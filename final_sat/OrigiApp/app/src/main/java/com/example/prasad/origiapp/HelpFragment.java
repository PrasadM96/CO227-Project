package com.example.prasad.origiapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {


    public HelpFragment() {
        // Required empty public constructor
    }

    private TextView mytxtvw1, mytxtvw2, mytxtvw3, mytxtvw4, mytxtvw5, mytxtvw6;
    private ImageButton myButton1, myButton2, myButton3, myButton4, myButton5, myButton6;
    boolean iscolor1 = true;
    boolean iscolor2 = true;
    boolean iscolor3 = true;
    boolean iscolor4 = true;
    boolean iscolor5 = true;
    boolean iscolor6 = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragview = inflater.inflate(R.layout.fragment_help, container, false);

        super.onCreate(savedInstanceState);
        //setContentView(R.fragment_help);
        mytxtvw1 = (TextView) fragview.findViewById(R.id.myTextView1);
        myButton1 = (ImageButton) fragview.findViewById(R.id.mybtn1);
        myButton1.setOnClickListener(myhandler);

        mytxtvw2 = (TextView) fragview.findViewById(R.id.myTextView2);
        myButton2 = (ImageButton) fragview.findViewById(R.id.mybtn2);
        myButton2.setOnClickListener(myhandler);

        mytxtvw3 = (TextView) fragview.findViewById(R.id.myTextView3);
        myButton3 = (ImageButton) fragview.findViewById(R.id.mybtn3);
        myButton3.setOnClickListener(myhandler);

        mytxtvw4 = (TextView) fragview.findViewById(R.id.myTextView4);
        myButton4 = (ImageButton) fragview.findViewById(R.id.mybtn4);
        myButton4.setOnClickListener(myhandler);

        mytxtvw5 = (TextView) fragview.findViewById(R.id.myTextView5);
        myButton5 = (ImageButton) fragview.findViewById(R.id.mybtn5);
        myButton5.setOnClickListener(myhandler);

        mytxtvw6 = (TextView) fragview.findViewById(R.id.myTextView6);
        myButton6 = (ImageButton) fragview.findViewById(R.id.mybtn6);
        myButton6.setOnClickListener(myhandler);
        return fragview;

    }


    View.OnClickListener myhandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.mybtn1:
                    // do your code
                    if (iscolor1) {
                        myButton1.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        iscolor1 = false;
                    } else {
                        myButton1.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        iscolor1 = true;
                    }


                    mytxtvw1.setVisibility((mytxtvw1.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);
                    break;

                case R.id.mybtn2:
                    // do your code
                    if (iscolor2) {
                        myButton2.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        iscolor2 = false;
                    } else {
                        myButton2.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        iscolor2 = true;
                    }


                    mytxtvw2.setVisibility((mytxtvw2.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);
                    break;

                case R.id.mybtn3:
                    // do your code
                    if (iscolor3) {
                        myButton3.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        iscolor3 = false;
                    } else {
                        myButton3.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        iscolor3 = true;
                    }


                    mytxtvw3.setVisibility((mytxtvw3.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);
                    break;

                case R.id.mybtn4:
                    // do your code
                    if (iscolor4) {
                        myButton4.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        iscolor4 = false;
                    } else {
                        myButton4.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        iscolor4 = true;
                    }


                    mytxtvw4.setVisibility((mytxtvw4.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);
                    break;

                case R.id.mybtn5:
                    // do your code
                    if (iscolor5) {
                        myButton5.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        iscolor5 = false;
                    } else {
                        myButton5.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        iscolor5 = true;
                    }


                    mytxtvw5.setVisibility((mytxtvw5.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);
                    break;

                case R.id.mybtn6:
                    // do your code
                    if (iscolor6) {
                        myButton6.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        iscolor6 = false;
                    } else {
                        myButton6.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        iscolor6 = true;
                    }


                    mytxtvw6.setVisibility((mytxtvw6.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);
                    break;


            }

        }

    /*public void onBtnClick()

    {

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(iscolor)
                {
                    myButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    iscolor = false;
                }
                else
                {
                    myButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    iscolor = true;
                }


                mytxtvw.setVisibility((mytxtvw.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.VISIBLE);


            }
        });



    }*/
    };
}