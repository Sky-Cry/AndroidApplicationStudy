package com.example.forhelp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HelpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Receiver","自定义的广播接收者，收到了求救广播");
        Log.i("Receiver",intent.getAction());
    }
}
