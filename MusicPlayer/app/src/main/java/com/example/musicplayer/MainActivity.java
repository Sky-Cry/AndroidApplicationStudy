package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_path;
    private TextView tv_play;
    private TextView tv_pause;

    MusicService.MyBinder myBinder;
    private MyConn myConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        et_path = findViewById(R.id.et_path);
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.tv_pause).setOnClickListener(this);

        queryAuthority();

    }

    @Override
    public void onClick(View view) {
        String pathway = et_path.getText().toString().trim();
//        File SDpath = Environment.getExternalStorageDirectory();
        File SDpath = new File("/storage/self/primary");
        File file = new File(SDpath,pathway);
        String path = file.getAbsolutePath();

        switch (view.getId()) {
            case R.id.tv_play:
                if (file.exists() && file.length() > 0) {
                    myBinder.play(path);
                } else {
                    Toast.makeText(this, "找不到音频文件", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_pause:
                myBinder.pause();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(myConn);
        super.onDestroy();
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (MusicService.MyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    private void queryAuthority() {
        int hasReadContactsPermission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasReadContactsPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (hasReadContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
            }
            return;
        }

        myConn = new MyConn();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, myConn, BIND_AUTO_CREATE);
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