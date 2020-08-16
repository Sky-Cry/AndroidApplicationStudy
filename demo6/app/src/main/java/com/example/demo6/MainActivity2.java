package com.example.demo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private ItemInfo itemInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

        itemInfo = new ItemInfo("金色圣剑",100,20,20);

        textView1.setText(itemInfo.getName());
        textView2.setText("生命值"+itemInfo.getLife());
        textView3.setText("攻击力"+itemInfo.getAttack());
        textView4.setText("敏捷度"+itemInfo.getSpeed());

        findViewById(R.id.linearLayout).setOnClickListener(this);
    }

    private void init(){
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView12);
        textView4 = findViewById(R.id.textView13);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linearLayout:
                Intent intent = new Intent();
                intent.putExtra("equipment",itemInfo);
                setResult(1,intent);
                finish();
                break;
        }
    }
}