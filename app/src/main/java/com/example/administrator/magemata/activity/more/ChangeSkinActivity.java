package com.example.administrator.magemata.activity.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.adapter.SkinSettingManager;
import com.example.administrator.magemata.constant.Define;

/**
 * Created by Administrator on 2017/5/18.
 */
public class ChangeSkinActivity extends AppCompatActivity implements View.OnClickListener{

    private SkinSettingManager mSettingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeskin);
        mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();

        findViewById(R.id.imageView1).setOnClickListener(this);
        findViewById(R.id.imageView2).setOnClickListener(this);
        findViewById(R.id.imageView3).setOnClickListener(this);
        findViewById(R.id.imageView4).setOnClickListener(this);
        findViewById(R.id.imageView5).setOnClickListener(this);
        findViewById(R.id.imageView6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView1:
                mSettingManager.toggleSkins(0);
//                title.setBackgroundResource(R.drawable.color7);
                Define.setValue(ChangeSkinActivity.this, R.style.Theme_color7, R.drawable.color7);
                break;
            case R.id.imageView2:
                mSettingManager.toggleSkins(1);
                Define.setValue(ChangeSkinActivity.this, R.style.Theme_color2, R.drawable.color2);
                break;
            case R.id.imageView3:
                mSettingManager.toggleSkins(2);
                Define.setValue(ChangeSkinActivity.this, R.style.Theme_color6, R.drawable.color6);
                break;
            case R.id.imageView4:
                mSettingManager.toggleSkins(3);
                Define.setValue(ChangeSkinActivity.this, R.style.Theme_color3, R.drawable.color3);
                break;
            case R.id.imageView5:
                mSettingManager.toggleSkins(4);
                Define.setValue(ChangeSkinActivity.this, R.style.Theme_color5, R.drawable.color5);
                break;
            case R.id.imageView6:
                mSettingManager.toggleSkins(5);
                Define.setValue(ChangeSkinActivity.this, R.style.Theme_color1, R.drawable.color1);
                break;
            default:
                break;
        }
    }
    static public void actionStart(Context context){
        Intent intent=new Intent(context,ChangeSkinActivity.class);
        context.startActivity(intent);
    }

}
