package com.example.cryptonomist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class newsAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<News> data;
    private LayoutInflater inflater;

    public newsAdapter(Activity activity, ArrayList<News> data) {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        News temp = data.get(i);

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_news,null);
        }

        //Declaration

        TextView newsTitle = (TextView) convertView.findViewById(R.id.NewsTitle);
        TextView newsBody = (TextView) convertView.findViewById(R.id.NewsBody);
        TextView newsSource = (TextView) convertView.findViewById(R.id.NewsSource);
        TextView newsCoin = (TextView) convertView.findViewById(R.id.NewsCoin);
        TextView newsTime = (TextView) convertView.findViewById(R.id.NewsTime);

        // Process

        newsTitle.setText(temp.getTitle());
        newsBody.setText(temp.getBody());
        newsSource.setText(temp.getDomain());
        newsCoin.setText(temp.getCoin());
        newsTime.setText(temp.getTime());

        return convertView;
    }
}
