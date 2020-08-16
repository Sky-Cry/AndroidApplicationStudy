package com.example.orderedbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter1;
    private IntentFilter intentFilter2;
    private IntentFilter intentFilter3;
    private MyReceiverOne myReceiverOne;
    private MyReceiverTwo myReceiverTwo;
    private MyReceiverThree myReceiverThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiverTask();
    }

    private void receiverTask(){
        intentFilter1 = new IntentFilter();
        intentFilter1.addAction("MyReceiver");
        intentFilter1.setPriority(1000);
        myReceiverOne = new MyReceiverOne();
        registerReceiver(myReceiverOne,intentFilter1);

        intentFilter2 = new IntentFilter();
        intentFilter2.addAction("MyReceiver");
        intentFilter2.setPriority(200);
        myReceiverTwo = new MyReceiverTwo();
        registerReceiver(myReceiverTwo,intentFilter2);

        intentFilter3 = new IntentFilter();
        intentFilter3.addAction("MyReceiver");
        intentFilter3.setPriority(600);
        myReceiverThree = new MyReceiverThree();
        registerReceiver(myReceiverThree,intentFilter3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiverOne);
        unregisterReceiver(myReceiverTwo);
        unregisterReceiver(myReceiverThree);
    }

    public void send(View view){
        Intent intent = new Intent();
        intent.setAction("MyReceiver");
//        sendOrderedBroadcast(intent,null);
        MyReceiverTwo receiverTwo = new MyReceiverTwo();
        sendOrderedBroadcast(intent,null,receiverTwo,null,0,null,null);
    }
}