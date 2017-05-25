package com.example.administrator.magemata.activity.publishes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.publishes.pubfragment.LostAdapter;
import com.example.administrator.magemata.adapter.SkinSettingManager;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

/**
 * Created by Administrator on 2017/4/30.
 */

public class LostActivity extends AppCompatActivity {
    AdvancedPagerSlidingTabStrip losttabs;
    ViewPager lostpaper;
    private PagerAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostmain);
        losttabs =(AdvancedPagerSlidingTabStrip)findViewById(R.id.lost_tabs);
        lostpaper=(ViewPager)findViewById(R.id.lost_viewpager);
        lostpaper.setOffscreenPageLimit(1);
        adapter = new LostAdapter(getSupportFragmentManager());
        lostpaper.setAdapter(adapter);
        losttabs.setViewPager(lostpaper);
    }

//    @BindView(R.id.base_lv)
//    public ListView baselv;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lostmain);
//        ButterKnife.bind(this);
//        super.initAdapter(LostActivity.this);
//        baselv.setAdapter(simplead);
//        baselv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CardActivity.actionStart(CircleActivity.this);
//            }
//        });
//    }
//
//
//
    public static void actionStart(Context context){
        Intent intent=new Intent(context,LostActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Resume","pk");
        SkinSettingManager mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();
    }
}
