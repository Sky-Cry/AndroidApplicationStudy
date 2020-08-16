package com.example.contentobserverdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ContentResolver resolver;
    private Uri uri;
    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        createDB();
    }

    private void initView(){
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        resolver = getContentResolver();
        uri = Uri.parse("content://com.example.contentobserverdb/info");
        values = new ContentValues();
        switch (view.getId()){
            case R.id.button:
                Random random = new Random();
                values.put("name","add_itcast"+random.nextInt(10));
                Uri newuri = resolver.insert(uri,values);
                Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                Log.i("数据库应用","添加"+newuri);
                break;
            case R.id.button3:
                int deleteCount = resolver.delete(uri,"name=?",new String[]{"itcast0"});
                Toast.makeText(this,"删除成功，删除了"+deleteCount+"条数据。",Toast.LENGTH_SHORT).show();
                Log.i("数据库应用","删除");
                break;
            case R.id.button4:
                List<Map<String,String>> data = new ArrayList<>();
                Cursor cursor = resolver.query(uri,new String[]{"_id","name"},null,null,null);
                while (cursor.moveToNext()){
                    Map<String,String> map = new HashMap<>();
                    map.put("_id",cursor.getString(0));
                    map.put("name",cursor.getString(1));
                    data.add(map);
                }
                cursor.close();
                Toast.makeText(this,"查询成功",Toast.LENGTH_SHORT).show();
                Log.i("数据库应用","查询结果："+data.toString());
                break;
            case R.id.button2:
                values.put("name","update_itcast");
                int updateCount = resolver.update(uri,values,"name=?",new String[]{"itcast1"});
                Toast.makeText(this,"更新成功，更新了"+updateCount+"条数据。",Toast.LENGTH_SHORT).show();
                Log.i("数据库应用","更新");
                break;
        }
    }

    private void createDB(){
        PersonDBOpenHelper helper = new PersonDBOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        for (int i=0;i<3;i++){
            ContentValues values = new ContentValues();
            values.put("name","itcast"+i);
            db.insert("info",null,values);
        }
        db.close();
    }
}