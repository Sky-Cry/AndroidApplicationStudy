package com.example.showfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Fragment1 extends Fragment {

    private String page = "页面1";

    public Fragment1() {

    }

    public String getPage() {
        return page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1,container,false);
        MainActivity activity = (MainActivity) getActivity();
        int image = activity.getImages()[0];
        ImageView iv_one = view.findViewById(R.id.iv_one);
        iv_one.setImageResource(image);
        return view;
    }
}