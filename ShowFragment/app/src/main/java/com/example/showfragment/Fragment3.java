package com.example.showfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    private String page = "页面3";

    public Fragment3() {

    }

    public String getPage() {
        return page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3,container,false);
        MainActivity activity = (MainActivity) getActivity();
        int image = activity.getImages()[2];
        ImageView iv_three = view.findViewById(R.id.iv_three);
        iv_three.setImageResource(image);
        return view;
    }
}