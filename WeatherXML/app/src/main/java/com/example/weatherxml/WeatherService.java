package com.example.weatherxml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {

    public static List<WeatherInfo> getInfosFromXML(InputStream is) throws Exception{
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is,"utf-8");
        List<WeatherInfo> weatherInfos = null;
        WeatherInfo weatherInfo = null;

        int type = parser.getEventType();

        while (type != XmlPullParser.END_DOCUMENT){
            switch (type){
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())){
                        weatherInfos = new ArrayList<WeatherInfo>();
                    }else if ("city".equals(parser.getName())){
                        weatherInfo = new WeatherInfo();
                        weatherInfo.setId(parser.getAttributeValue(0));
                    }else if ("temp".equals(parser.getName())){
                        weatherInfo.setTemp(parser.nextText());
                    }else if ("weather".equals(parser.getName())){
                        weatherInfo.setWeather(parser.nextText());
                    }else if ("name".equals(parser.getName())){
                        weatherInfo.setName(parser.nextText());
                    }else if ("pm".equals(parser.getName())){
                        weatherInfo.setPm(parser.nextText());
                    }else if ("wind".equals(parser.getName())){
                        weatherInfo.setWind(parser.nextText());
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ("city".equals(parser.getName())){
                        weatherInfos.add(weatherInfo);
                    }
                    break;
            }
            type = parser.next();
        }
        return weatherInfos;
    }
}
