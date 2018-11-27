package com.example.work_10_01;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.work_10_01.Adapter.ViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类
 */
public class MainActivity extends AppCompatActivity {
                //定义变量
            private ViewPager viewPager;
            private ViewAdapter adapter;
            private RadioGroup radioGroup;
            private TextView name_text,yuan_text,you_text;
            private String path="https://www.zhaoapi.cn/product/getProductDetail?pid=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源ID
        viewPager=findViewById(R.id.viewpager);
        radioGroup=findViewById(R.id.group);
        name_text=findViewById(R.id.name_textview);
        yuan_text=findViewById(R.id.yuan_textview);
        you_text=findViewById(R.id.you_textview);
        //适配器
        adapter=new ViewAdapter(this);
        viewPager.setAdapter(adapter);

        //数据
        initData();




    }

    private void initData() {
        NetUtil.getInstance().getAsyn(path, Bean.class, new NetUtil.CallBack<Bean>() {
            @Override
            public void onseccse(Bean o) {
                adapter.setMjihe((List <Bean.DataBean>) o.getData());
            }
        });



    }
//    //定义集合
//    List strList=new ArrayList();
//    private void sub(String path){
//        //第一步：找到关键字“|”的角标
//        int index = path.indexOf("|");
//        Log.i("TAG",index+"");
//        //如果角标>=0，说明字符串中存在关键字
//        if (index>=0){
//            //第二步：将字符串拆成左右两部分
//            String substring = path.substring(0, index);
//            strList.add(substring);
//            //重新开始sub方法，(递归)
//            //此时路径为
//            sub(path.substring(index+1,path.length()));
//        }else {
//            //找不到角标，保存剩余文字
//            strList.add(path);
//        }
//
//    }
}
