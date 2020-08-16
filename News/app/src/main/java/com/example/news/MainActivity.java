package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout loading;
    private ListView lv_news;
    private List<NewsInfo> newsInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fillData();
    }

    private void initView(){
        loading = findViewById(R.id.loading);
        lv_news = findViewById(R.id.lv_news);
    }

    private void fillData(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getString(R.string.serverurl), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes,"utf-8");
                    newsInfos = JsonParse.getNewsInfo(json);
                    if (newsInfos==null){
                        Toast.makeText(MainActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                    } else {
                        loading.setVisibility(View.INVISIBLE);
                        lv_news.setAdapter(new NewsAdapter(newsInfos,MainActivity.this));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}