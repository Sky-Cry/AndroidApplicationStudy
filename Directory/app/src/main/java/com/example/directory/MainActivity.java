package com.example.directory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_phone;
    private TextView tv_show;

    MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        myHelper = new MyHelper(this);
    }

    private void init(){
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        tv_show = findViewById(R.id.tv_show);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name;
        String phone;
        SQLiteDatabase db;
        ContentValues values;
        switch (view.getId()){
            case R.id.btn_add:
                name = et_name.getText().toString().trim();
                phone = et_phone.getText().toString().trim();
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("name",name);
                values.put("phone",phone);
                db.insert("information",null,values);
                Toast.makeText(this,"添加信息成功",Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_query:
                db = myHelper.getReadableDatabase();
                Cursor cursor = db.query("information",null,null,null,null,null,null);
                if (cursor.getCount() == 0){
                    tv_show.setText("");
                    Toast.makeText(this,"没有数据",Toast.LENGTH_SHORT).show();
                } else {
                    cursor.moveToFirst();
                    tv_show.setText("Name:"+cursor.getString(1)+" Tel:"+cursor.getString(cursor.getColumnIndex("phone"))+"\n");
                }
                while (cursor.moveToNext()){
                    tv_show.append("Name:"+cursor.getString(1)+" Tel:"+cursor.getString(cursor.getColumnIndex("phone"))+"\n");
                }
                cursor.close();
                db.close();
                break;
            case R.id.btn_update:
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("phone",et_phone.getText().toString().trim());
                db.update("information",values,"name=?",new String[]{et_name.getText().toString().trim()});
                Toast.makeText(this,"数据更新成功",Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_delete:
                db = myHelper.getWritableDatabase();
                db.delete("information","name=?",new String[]{et_name.getText().toString().trim()});
                Toast.makeText(this,"数据删除成功",Toast.LENGTH_SHORT).show();
                db.close();
                break;
        }
    }
}