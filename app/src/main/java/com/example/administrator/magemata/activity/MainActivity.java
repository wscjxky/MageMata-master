package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.R2;
import com.example.administrator.magemata.adapter.MainAdapter;

import com.example.administrator.magemata.util.Permission;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;


import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/24.
 */

public class MainActivity extends BaseActivity {
    @ViewInject(R2.id.tabs)
    private AdvancedPagerSlidingTabStrip tabs;
    @ViewInject(R2.id.viewpager)
    private ViewPager pager;
    private PagerAdapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);

        Permission.requestPermission(MainActivity.this);
//        useRxjava();
        adapter = new MainAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(3);
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


    private void  useRxjava() {
        Observable.create(new Observable.OnSubscribe<MainAdapter>() {
            @Override
            public void call(Subscriber<? super MainAdapter> subscriber) {
                MainAdapter adapter;
                adapter = new MainAdapter(getSupportFragmentManager());
                pager.setOffscreenPageLimit(3);
                pager.setAdapter(adapter);
                tabs.setViewPager(pager);
                tabs.showDot(2, "99+");
//                subscriber.onNext(adapter);
                if (Thread.currentThread() != Looper.getMainLooper().getThread())
                    Log.e("我是io线程", "sdf");
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe();
//                .subscribe(
//                        new Subscriber<MainAdapter>() {
//                    @Override
//                    public void onCompleted() {
//                        if (Thread.currentThread() == Looper.getMainLooper().getThread())
//                            Log.e("sub是主进程", "sdf");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MainAdapter adapter) {
//
//                    }
//
//                });
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