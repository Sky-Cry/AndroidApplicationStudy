package com.example.appmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<App> list;
    private Context context;

    public MyAdapter(List<App> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        View v = View.inflate(context,R.layout.list_item,null);
//        TextView item_name = v.findViewById(R.id.item_name);
//        ImageView item_icon = v.findViewById(R.id.item_icon);
//        App app = list.get(i);
//        item_name.setText(app.getName());
//        item_icon.setBackgroundResource(app.getIcon());
//        return v;

        ViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
            holder = new ViewHolder();
            holder.item_name = view.findViewById(R.id.item_name);
            holder.item_icon = view.findViewById(R.id.item_icon);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        App app = list.get(i);
        holder.item_name.setText(app.getName());
        holder.item_icon.setBackgroundResource(app.getIcon());
        return view;
    }

    class ViewHolder{
        TextView item_name;
        ImageView item_icon;
    }
}
