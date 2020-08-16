package com.example.readsms;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_sms;
    private TextView tv_des;
    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_sms = findViewById(R.id.tv_sms);
        tv_des = findViewById(R.id.tv_des);
        queryAuthority();
    }

    public void readSMS(View view){
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri,new String[]{"_id","address","type","body","date"},null,null,null);
        List<SmsInfo> smsInfos = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0){
            tv_des.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()){
                int _id = cursor.getInt(0);
                String address = cursor.getString(1);
                int type = cursor.getInt(2);
                String body = cursor.getString(3);
                long date = cursor.getLong(4);
                SmsInfo smsInfo = new SmsInfo(_id,address,type,body,date);
                smsInfos.add(smsInfo);
            }
            cursor.close();
        }

        for (int i = 0;i < smsInfos.size();i++){
            text += "手机短信：" + smsInfos.get(i).getAddress() + "\n";
            text += "短信内容：" + smsInfos.get(i).getBody() + "\n\n";
        }
        tv_sms.setText(text);
    }

    private void queryAuthority() {
        int hasPermission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasPermission = checkSelfPermission(Manifest.permission.READ_SMS);
        }
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_SMS}, 123);
            }
            return;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    queryAuthority();
                } else {
                    Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }

    }
}