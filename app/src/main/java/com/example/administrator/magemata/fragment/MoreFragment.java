package com.example.administrator.magemata.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.util.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/4/25.
 */

public class MoreFragment extends Fragment {
    private Activity activity;
    @BindView(R.id.more_userview)
    CircleImageView imageView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_more, container, false);
        this.activity = getActivity();
        ButterKnife.bind(this,mview);
        return mview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @OnClick(R.id.more_userview)
    public void changePortrait(){
        new Permission(activity);
        Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
        startActivityForResult(intent, 1);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("ASd", "setUserVisibleHint " + isVisibleToUser);
        if (isVisibleToUser) {
        } else {
        }
    }
}
