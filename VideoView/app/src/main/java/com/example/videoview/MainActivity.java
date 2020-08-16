package com.example.videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_path;
    private ImageView bt_play;
    private VideoView videoView;
    private MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_path = findViewById(R.id.et_path);
        bt_play = findViewById(R.id.bt_play);
        videoView = findViewById(R.id.videoView);
        bt_play.setOnClickListener(this);

        controller = new MediaController(this);
        videoView.setMediaController(controller);

        queryAuthority();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_play:
                play();
                break;
        }
    }

    private void play(){
        if (videoView!=null&&videoView.isPlaying()){
            bt_play.setImageResource(android.R.drawable.ic_media_play);
            videoView.stopPlayback();
        }
        videoView.setVideoPath(et_path.getText().toString().trim());
        videoView.start();
        bt_play.setImageResource(android.R.drawable.ic_media_pause);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                bt_play.setImageResource(android.R.drawable.ic_media_play);
            }
        });
    }

    private void queryAuthority() {
        int hasPermission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
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