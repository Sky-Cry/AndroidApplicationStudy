package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    class MyBinder extends Binder{
        public void callMethodInService(){
            methodInService();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("BindService","onBind()");
        return new MyBinder();
    }

    public void methodInService(){
        Log.i("BindService","自定义方法methodInService()被调用");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("BindService","onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("BindService","onDestroy()");
    }
}
