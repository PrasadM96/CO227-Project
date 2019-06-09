package com.example.prasad.origiapp;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment {

    String ConsumerNo,MeterNo;
    TextView consumerNo,meterNo,mail,phone,others;

    @SuppressLint("ValidFragment")
    public ProfileFragment(String consumerNo, String meterNo) {
        // Required empty public constructor
        ConsumerNo=consumerNo;
        MeterNo=meterNo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        consumerNo = (TextView) view.findViewById(R.id.consumerNo);
        meterNo = (TextView) view.findViewById(R.id.meterNo);
        mail = (TextView) view.findViewById(R.id.mail);
        phone = (TextView) view.findViewById(R.id.phone);
        others = (TextView) view.findViewById(R.id.others);

        consumerNo.setText(ConsumerNo);
        meterNo.setText(MeterNo);
        mail.setText("sandushidileka2@gmail.com");
        phone.setText("0702451805");
        others.setText("In a realationship with DA");

        return view;
    }

}
