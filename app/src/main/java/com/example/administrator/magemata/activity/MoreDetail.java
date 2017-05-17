package com.example.administrator.magemata.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.magemata.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/16.
 */
public class MoreDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moredetail);
        ButterKnife.bind(this);

    }
}
