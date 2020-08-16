package com.example.frame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_flower;
    private Button button;

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_flower = findViewById(R.id.iv_flower);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        animationDrawable = (AnimationDrawable) iv_flower.getBackground();
    }

    @Override
    public void onClick(View view) {
        if (!animationDrawable.isRunning()){
            animationDrawable.start();
            button.setText("暂停");
        } else {
            animationDrawable.stop();
            button.setText("播放");
        }
    }
}