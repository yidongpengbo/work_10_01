package com.example.work_10_01.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.work_10_01.Bean;

import java.util.List;

public class ViewAdapter extends PagerAdapter {
    private Context con;
    private List<Bean.DataBean> mjihe;

    public ViewAdapter(Context con) {
        this.con = con;
    }

    public void setMjihe(List <Bean.DataBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mjihe.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(con);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
