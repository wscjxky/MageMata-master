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
import android.view.MenuItem;
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


    }
    public static void actionStart(Context context ){
        Intent intent=new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent intent1=new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT,"你好 分享成功 www.magemata.com\"");
                intent1.setType("text/plain");
                startActivity(Intent.createChooser(intent1,"share"));
        }
        return false;
    }
}