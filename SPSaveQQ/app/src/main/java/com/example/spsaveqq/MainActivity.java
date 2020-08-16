package com.example.spsaveqq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText1;
    private EditText editText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Map<String,String> userInfo = SPSaveQQ.getUserInfo(this);
        if (userInfo!=null){
            editText1.setText(userInfo.get("number"));
            editText2.setText(userInfo.get("password"));
        }
    }

    private void init(){
        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        button = findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String number = editText1.getText().toString().trim();
        String password = editText2.getText().toString().trim();

        if (TextUtils.isEmpty(number)){
            Toast.makeText(this,"请输入QQ号码",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入QQ密码",Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isSaveSuccess = SPSaveQQ.saveUserInfo(this,number,password);
        if (isSaveSuccess){
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
        }
    }
}