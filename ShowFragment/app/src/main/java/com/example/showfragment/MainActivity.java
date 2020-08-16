package com.example.showfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] images = {R.drawable.fragment1,R.drawable.fragment2,R.drawable.fragment3};
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_show).setOnClickListener(this);
    }

    public int[] getImages(){
        return images;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_one:
                Fragment1 fragment1 = new Fragment1();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fm,fragment1);
                transaction.commit();
                break;
            case R.id.btn_two:
                Fragment2 fragment2 = new Fragment2();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fm,fragment2);
                transaction.commit();
                break;
            case R.id.btn_three:
                Fragment3 fragment3 = new Fragment3();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fm,fragment3);
                transaction.commit();
                break;
            case R.id.btn_show:
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fm);
                if (fragment instanceof Fragment1){
                    Fragment1 f1 = (Fragment1) fragment;
                    String page = f1.getPage();
                    Toast.makeText(this,page,Toast.LENGTH_SHORT).show();
                }else if (fragment instanceof Fragment2){
                    Fragment2 f2 = (Fragment2) fragment;
                    String page = f2.getPage();
                    Toast.makeText(this,page,Toast.LENGTH_SHORT).show();
                }else if (fragment instanceof Fragment3){
                    Fragment3 f3 = (Fragment3) fragment;
                    String page = f3.getPage();
                    Toast.makeText(this,page,Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}