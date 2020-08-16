package com.example.demo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private  void init(){
        progressBar1 = findViewById(R.id.progressBar2);
        progressBar2 = findViewById(R.id.progressBar3);
        progressBar3 = findViewById(R.id.progressBar4);

        textView1 = findViewById(R.id.textView7);
        textView2 = findViewById(R.id.textView9);
        textView3 = findViewById(R.id.textView11);

        progressBar1.setMax(1000);
        progressBar2.setMax(1000);
        progressBar3.setMax(1000);
    }

    public void click(View v) {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivityForResult(intent,1);
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode == 1){
                if (resultCode == 1){
                    ItemInfo itemInfo = (ItemInfo) data.getSerializableExtra("equipment");
                    updateProgress(itemInfo);
                }
            }
        }
    }

    private void updateProgress(ItemInfo info){
        int current_life = progressBar1.getProgress();
        int current_attack = progressBar2.getProgress();
        int current_speed = progressBar3.getProgress();

        progressBar1.setProgress(current_life + info.getLife());
        progressBar2.setProgress(current_attack + info.getAttack());
        progressBar3.setProgress(current_speed + info.getSpeed());

        textView1.setText(progressBar1.getProgress()+"");
        textView2.setText(progressBar2.getProgress()+"");
        textView3.setText(progressBar3.getProgress()+"");

    }
}