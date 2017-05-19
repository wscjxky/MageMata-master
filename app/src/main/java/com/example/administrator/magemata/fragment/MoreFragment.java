package com.example.administrator.magemata.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.more.ChangeSkinActivity;
import com.example.administrator.magemata.activity.more.MoreDetailActivity;
import com.example.administrator.magemata.activity.more.OtherActivity;
import com.example.administrator.magemata.activity.more.WalletActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/25.
 */

public class MoreFragment extends Fragment {
    private Activity activity;
    private CircleImageView imageView;
    private LinearLayout linearLayout;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_more, container, false);
        this.activity = getActivity();
        setListener(mview);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
            Log.e("注册成功","abc");
        }
        return mview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("ASd", "setUserVisibleHint " + isVisibleToUser);

    }

    private  void setListener(View mview){
        imageView = (CircleImageView) mview.findViewById(R.id.more_userview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreDetailActivity.actionStart(activity);
            }
        });

       linearLayout=(LinearLayout)mview.findViewById(R.id.more_linel_changeskin);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeSkinActivity.actionStart(activity);
            }
        });
        mview.findViewById(R.id.more_linel_otherinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherActivity.actionStart(activity);
            }
        });
        mview.findViewById(R.id.more_linel_mywallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WalletActivity.actionStart(activity);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
            EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ImageMessage event) {
        Bitmap logo = event.getBitmp();
        imageView.setImageBitmap(logo);
    };


}
