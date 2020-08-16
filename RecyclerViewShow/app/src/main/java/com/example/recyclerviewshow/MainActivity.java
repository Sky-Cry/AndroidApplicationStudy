package com.example.recyclerviewshow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView id_recyclerView;
    private List<Integer> datas;
    private int[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        id_recyclerView = findViewById(R.id.id_recyclerview);
        id_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        id_recyclerView.setAdapter(new HomeAdapter());
    }

    private void initData(){
        datas = new ArrayList<>();
        for (int i = 1;i < 11;i++){
            datas.add(i);
        }
        img = new int[]{
                R.drawable.iv1,R.drawable.iv2,R.drawable.iv3,R.drawable.iv4,
                R.drawable.iv5,R.drawable.iv6,R.drawable.iv7,R.drawable.iv8,
                R.drawable.iv9,R.drawable.iv10,
        };
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_home,parent,false));

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.tv_num.setText("这是第"+datas.get(position).toString()+"个精灵");
            holder.iv_num.setImageResource(img[position]);
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv_num;
            ImageView iv_num;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_num = itemView.findViewById(R.id.tv_num);
                iv_num = itemView.findViewById(R.id.iv_num);
            }
        }
    }


}