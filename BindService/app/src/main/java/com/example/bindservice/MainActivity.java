package com.example.bindservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyService.MyBinder myBinder;
    private MyConn myConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bind(View view){
        if (myConn == null){
            myConn = new MyConn();
        }
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,myConn,BIND_AUTO_CREATE);
    }

    public void call(View view){
        myBinder.callMethodInService();
    }

    public void unbind(View view){
        if (myConn != null){
            unbindService(myConn);
            myConn = null;
        }
    }

    private class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (MyService.MyBinder)iBinder;
            Log.i("BindService","服务绑定成功，内存地址为："+myBinder.toString());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}