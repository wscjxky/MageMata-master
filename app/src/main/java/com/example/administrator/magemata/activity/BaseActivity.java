package com.example.administrator.magemata.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.adapter.SkinSettingManager;

import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/19.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        SkinSettingManager mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();
    }


}
