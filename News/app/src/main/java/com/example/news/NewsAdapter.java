package com.example.news;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<NewsInfo> newsInfos;
    private Context context;

    public NewsAdapter(List<NewsInfo> newsInfos, Context context) {
        this.newsInfos = newsInfos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return newsInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.news_item,null);

        SmartImageView siv_pic = v.findViewById(R.id.siv_icon);
        TextView tv_title = v.findViewById(R.id.tv_title);
        TextView tv_description = v.findViewById(R.id.tv_description);
        TextView tv_type = v.findViewById(R.id.tv_type);

        NewsInfo newsInfo = newsInfos.get(i);

        siv_pic.setImageUrl(newsInfo.getIcon(),R.drawable.ic_launcher_background,R.drawable.ic_launcher_background);
        tv_title.setText(newsInfo.getTitle());
        tv_description.setText(newsInfo.getContent());

        int type = newsInfo.getType();
        switch (type){
            case 1:
                tv_type.setText("评论："+newsInfo.getComment());
                break;
            case 2:
                tv_type.setTextColor(Color.RED);
                tv_type.setText("专题");
                break;
            case 3:
                tv_type.setTextColor(Color.BLUE);
                tv_type.setText("LIVE");
                break;
        }
        return v;
    }
}
