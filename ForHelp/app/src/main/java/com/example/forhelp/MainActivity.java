package com.example.forhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private HelpReceiver helpReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiverTask();
    }

    private void receiverTask() {

        intentFilter =new IntentFilter();
        intentFilter.addAction("sos");//为自定义的广播添加一个行为，需要注册什么广播就添加什么行为
        helpReceiver =new HelpReceiver();
        registerReceiver(helpReceiver, intentFilter);//register  注册  receiver 接收者    注册一个动态广播

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(helpReceiver);
    }

    public void send(View view){
        Intent intent = new Intent();
        intent.setAction("sos");
        sendBroadcast(intent);
    }
}