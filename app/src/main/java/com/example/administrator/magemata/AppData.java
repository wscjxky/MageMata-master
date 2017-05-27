package com.example.administrator.magemata;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Toast;

import com.example.administrator.magemata.model.User;
import com.example.administrator.magemata.util.Permission;
import com.squareup.leakcanary.LeakCanary;

import org.xutils.x;

/**
 * Created by Administrator on 2017/4/25.
 */

public class AppData extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        x.Ext.init(this);
        // Normal app init code...
    }
}
