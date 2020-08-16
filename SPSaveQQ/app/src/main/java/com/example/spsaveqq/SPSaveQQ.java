package com.example.spsaveqq;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPSaveQQ {
    public static boolean saveUserInfo(Context context, String number, String password){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("number",number);
        editor.putString("password",password);
        editor.commit();
        return true;
    }

    public static Map<String,String> getUserInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String number = sp.getString("number","");
        String password = sp.getString("password","");

        Map<String,String> userMap = new HashMap<>();
        userMap.put("number",number);
        userMap.put("password",password);

        return userMap;
    }
}
