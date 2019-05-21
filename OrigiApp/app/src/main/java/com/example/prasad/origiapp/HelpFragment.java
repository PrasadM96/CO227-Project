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
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {


    public HelpFragment() {
        // Required empty public constructor
    }

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button backbtn;
    private Button nextbtn;
    private int mCurrentPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView= inflater.inflate(R.layout.fragment_help, container, false);

        backbtn=fragView.findViewById(R.id.button1);
        nextbtn=fragView.findViewById(R.id.button2);
        viewPager=fragView.findViewById(R.id.slideViewPager);
        linearLayout=fragView.findViewById(R.id.dotsLayout);


        sliderAdapter=new SliderAdapter(getContext());
        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        viewPager.addOnPageChangeListener(viewListner);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage+1);
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(mCurrentPage-1);
            }
        });



        return fragView;
    }

    public void addDotsIndicator(int position){
        mDots=new TextView[4];
        linearLayout.removeAllViews();

        for(int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(getContext());
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentGrey));

            linearLayout.addView(mDots[i]);
        }

        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorGrey));
        }
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage=i;

            if(mCurrentPage==0){
                nextbtn.setEnabled(true);
                backbtn.setEnabled(false);
                backbtn.setVisibility(View.INVISIBLE);
                nextbtn.setText("Next");
                backbtn.setText("");
            }else if(i==mDots.length-1){
                nextbtn.setEnabled(true);
                backbtn.setEnabled(true);
                backbtn.setVisibility(View.VISIBLE);
                nextbtn.setVisibility(View.INVISIBLE);
                backbtn.setText("Back");
                nextbtn.setText("");
            }else{
                nextbtn.setEnabled(true);
                backbtn.setEnabled(true);
                backbtn.setVisibility(View.VISIBLE);
                nextbtn.setVisibility(View.VISIBLE);
                nextbtn.setText("Next");
                backbtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}
