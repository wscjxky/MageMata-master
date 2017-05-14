package com.example.administrator.magemata.activity.publishes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.publishes.base.InfoBase;
import com.example.administrator.magemata.activity.publishes.base.PublishBase;
import com.example.administrator.magemata.constant.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okio.Buffer;

/**
 * Created by Administrator on 2017/5/14.
 */

public class  UsedActivity extends PublishBase {
    private SimpleAdapter simplead;
    private ListView baselv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_base);
        baselv = (ListView) findViewById(R.id.base_lv);
        simplead = initAdapter(UsedActivity.this);
        baselv.setAdapter(simplead);
        setListener(UsedActivity.this, baselv);


    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UsedActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            Bitmap logo = data.getParcelableExtra("logo");
            String time = data.getStringExtra("time");
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("logo", logo);
            listem.put("title", title);
            listem.put("content", content);
            listem.put("time",time);
            getListems().add(listem);
            simplead.notifyDataSetChanged();
        }
    }
}

