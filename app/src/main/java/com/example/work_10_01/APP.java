package com.example.work_10_01;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration=null;
        configuration=new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(10)
                .diskCacheSize(50*1024*1024)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
