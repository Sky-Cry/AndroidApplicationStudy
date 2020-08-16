package com.example.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        textView = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        button.setOnClickListener(this);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("匿名内部类处理事件。");
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        textView.setText("接口方式处理事件。");
    }

//    public void click(View view){
//        textView.setText("onClick方式处理事件。");
//    }
}