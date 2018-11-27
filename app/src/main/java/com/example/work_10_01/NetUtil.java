package com.example.work_10_01;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
    private static NetUtil instance;
    private NetUtil(){

    }

    public static NetUtil getInstance(){
        if (instance==null){
            instance=new NetUtil();
        }
        return instance;
    }

    //1.根据网址得到数据
    public String getNet(String path){
        String getstring="";
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(1000);
            urlConnection.setRequestMethod("GET");
            int responseCode = urlConnection.getResponseCode();
            if (responseCode==200){
                InputStream inputStream = urlConnection.getInputStream();
                //转换成字符
                 getstring = getstring(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getstring;
    }

    //2.转换成字符
    public String getstring(InputStream stream) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        StringBuilder builder = new StringBuilder();
        for (String i =reader.readLine(); i !=null ; i=reader.readLine()) {
                builder.append(i);
        }
        return builder.toString();
    }

    //3.json解析
    public <T> T getGson(String path,Class clazz){
        T o=null;
        String net = getNet(path);
        Gson gson = new Gson();
         o = (T) gson.fromJson(net, clazz);
        return o;
    }

    //4.定义接口
    public interface CallBack<T>{
        void onseccse(T t);
    }

    //5.AsynTach运行
    @SuppressLint("StaticFieldLeak")
    public void getAsyn(final String path, final Class clazz, final CallBack callBack){
        new AsyncTask <String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                return getGson(path,clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
               callBack.onseccse(o);
            }
        }.execute(path);
    }

}
