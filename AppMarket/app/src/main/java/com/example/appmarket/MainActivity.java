package com.example.appmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_apps;
    private List<App> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        MyAdapter myAdapter = new MyAdapter(list,this);
        lv_apps.setAdapter(myAdapter);
    }

    private void init(){
        lv_apps = findViewById(R.id.lv_apps);

        list = new ArrayList<App>();
        list.add(new App("京东",R.drawable.icon1));
        list.add(new App("QQ",R.drawable.icon2));
        list.add(new App("淘宝",R.drawable.icon3));
        list.add(new App("哔哩哔哩",R.drawable.icon4));
        list.add(new App("天猫",R.drawable.icon5));
        list.add(new App("支付宝",R.drawable.icon6));
        list.add(new App("微信",R.drawable.icon7));
        list.add(new App("王者荣耀",R.drawable.icon8));
    }
}