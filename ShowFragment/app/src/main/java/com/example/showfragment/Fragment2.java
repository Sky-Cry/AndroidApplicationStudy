package com.example.showfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private String page = "页面2";

    public Fragment2() {

    }

    public String getPage() {
        return page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2,container,false);
        MainActivity activity = (MainActivity) getActivity();
        int image = activity.getImages()[1];
        ImageView iv_two = view.findViewById(R.id.iv_two);
        iv_two.setImageResource(image);
        return view;
    }
}