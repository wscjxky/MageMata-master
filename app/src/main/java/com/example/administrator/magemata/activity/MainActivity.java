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
import com.example.administrator.magemata.adapter.SkinSettingManager;
import com.example.administrator.magemata.constant.Constant;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R2.id.tabs)
    AdvancedPagerSlidingTabStrip tabs;
    @BindView(R2.id.viewpager)
    ViewPager pager;
    private PagerAdapter adapter;
    private SkinSettingManager mSettingManager;
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Resume","pk");
        mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();
    }
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

    }
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        Log.e("stop,","asd");
        super.onDestroy();
    }
}