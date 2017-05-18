package com.example.administrator.magemata.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.publishes.JoinGoodActivity;
import com.example.administrator.magemata.activity.publishes.LostActivity;
import com.example.administrator.magemata.activity.publishes.PrintActivity;
import com.example.administrator.magemata.activity.publishes.UsedActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class PublishFragment extends Fragment {
    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_publish, container, false);
        activity=getActivity();
        ButterKnife.bind(this,mview);
        setListener(mview);
        return mview;
    }

    private  void setListener(View mview){
        mview.findViewById(R.id.publish_iv_lost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LostActivity.actionStart(activity);
            }
        });
        mview.findViewById(R.id.publish_iv_used).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsedActivity.actionStart(activity);
            }
        });
        mview.findViewById(R.id.publish_iv_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinGoodActivity.actionStart(activity);
            }
        });
        mview.findViewById(R.id.publish_iv_print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrintActivity.actionStart(activity);
            }
        });
    }

}
