package com.example.administrator.magemata.activity.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.BaseActivity;

import org.xutils.x;

/**
 * Created by Administrator on 2017/5/26.
 */

public class ChangInfoActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeuserinfo);
        x.view().inject(this);
    }


    public static void actionStart(Context context ){
        Intent intent=new Intent(context,ChangInfoActivity.class);
        context.startActivity(intent);
    }
}
