package com.example.weatherxml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_city;
    private TextView tv_weather;
    private TextView tv_temp;
    private TextView tv_wind;
    private TextView tv_pm;
    private ImageView iv_icon;
    private Map<String,WeatherInfo> weatherInfoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        try {
            List<WeatherInfo> infos = WeatherService.getInfosFromXML(this.getResources().openRawResource(R.raw.weather1));
            weatherInfoMap = new HashMap<>();
            for (WeatherInfo info : infos){
                weatherInfoMap.put(info.getId(),info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getMap("bj");

    }

    private void init(){
        tv_city = findViewById(R.id.textView);
        tv_weather = findViewById(R.id.textView2);
        tv_temp = findViewById(R.id.textView5);
        tv_wind = findViewById(R.id.textView4);
        tv_pm = findViewById(R.id.textView3);
        iv_icon = findViewById(R.id.imageView);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                getMap("bj");
                break;
            case R.id.button2:
                getMap("sh");
                break;
            case R.id.button3:
                getMap("gz");
                break;
        }
    }

    private void getMap(String id){
        WeatherInfo weatherInfo = weatherInfoMap.get(id);
        tv_city.setText(weatherInfo.getName());
        tv_weather.setText(weatherInfo.getWeather());
        tv_temp.setText("温度："+weatherInfo.getTemp());
        tv_wind.setText("风力："+weatherInfo.getWind());
        tv_pm.setText("pm:"+weatherInfo.getPm());

        switch (weatherInfo.getWeather()){
            case "晴天":
                iv_icon.setImageResource(R.drawable.sun);
                break;
            case "多云":
                iv_icon.setImageResource(R.drawable.clouds);
                break;
            case "晴天多云":
                iv_icon.setImageResource(R.drawable.cloud_sun);
                break;
        }
    }


}