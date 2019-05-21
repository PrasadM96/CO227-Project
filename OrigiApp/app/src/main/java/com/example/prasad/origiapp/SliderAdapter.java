package com.example.prasad.origiapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    public int[] slideImages={
            R.drawable.edithome,
            R.drawable.editconsumption,
            R.drawable.editlimit,
            R.drawable.editlocation
    };

    public String [] headings={
            "Home",
            "Consumption",
            "Warning",
            "Contact Us"
    };

    public  String [] description={
            "hello home",
            "hello consumption",
            "hello warning",
            "hello contact us"
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(RelativeLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageView=(ImageView)view.findViewById(R.id.image);
        TextView textView1=(TextView)view.findViewById(R.id.title);
        TextView textView2=(TextView)view.findViewById(R.id.para);

        imageView.setImageResource(slideImages[position]);
        textView1.setText(headings[position]);
        textView2.setText(description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
