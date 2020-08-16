package com.example.tween;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_alpha;
    private Button btn_rotate;
    private Button btn_scale;
    private Button btn_translate;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_alpha = findViewById(R.id.btn_alpha);
        btn_rotate = findViewById(R.id.btn_rotate);
        btn_scale = findViewById(R.id.btn_scale);
        btn_translate = findViewById(R.id.btn_translate);
        imageView = findViewById(R.id.imageView);

        btn_alpha.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_translate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alpha:
                Animation alpha = AnimationUtils.loadAnimation(this,R.anim.alpha_animation);
                imageView.startAnimation(alpha);
                break;
            case R.id.btn_rotate:
                Animation rotate = AnimationUtils.loadAnimation(this,R.anim.rotate_animation);
                imageView.startAnimation(rotate);
                break;
            case R.id.btn_scale:
                Animation scale = AnimationUtils.loadAnimation(this,R.anim.scale_animation);
                imageView.startAnimation(scale);
                break;
            case R.id.btn_translate:
                Animation translate = AnimationUtils.loadAnimation(this,R.anim.translate_animation);
                imageView.startAnimation(translate);
                break;
        }
    }
}