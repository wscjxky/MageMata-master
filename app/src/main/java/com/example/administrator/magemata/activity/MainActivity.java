package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import com.example.administrator.magemata.Events.FlatMessage;
import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.R2;
import com.example.administrator.magemata.adapter.MainAdapter;
import com.example.administrator.magemata.constant.Constant;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.geminiwen.skinsprite.app.SkinnableActivity;

/**
 * Created by Administrator on 2017/4/24.
 */

public class MainActivity extends SkinnableActivity {
    @BindView(R2.id.tabs)
    AdvancedPagerSlidingTabStrip tabs;
    @BindView(R2.id.viewpager)
    ViewPager pager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        Permission.requestPermission(MainActivity.this);
        pager.setOffscreenPageLimit(3);
        adapter = new MainAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        tabs.showDot(2, "99+");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
            Log.e("注册成功","abc");
        }

    }
    public static void actionStart(Context context ){
        Intent intent=new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void changeskin(FlatMessage event){
        Log.e("接受","s");
        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO: {
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            }
            case Configuration.UI_MODE_NIGHT_YES:{
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            }
        }
    }
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        Log.e("stop,","asd");
        super.onDestroy();
    }
}