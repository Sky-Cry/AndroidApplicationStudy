package com.example.demo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.i("activity_life","onCreate()");

//        button = findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
//                startActivity(intent);
//            }
//        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                startActivity(intent);
            }
        });



    }



//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i("activity_life","onStart()");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i("activity_life","onPause()");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i("activity_life","onResume()");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.i("activity_life","onStop()");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i("activity_life","onDestroy()");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.i("activity_life","onRestart()");
//    }
}