package com.example.administrator.magemata.activity.publishes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.publishes.base.AddItemBase;
import com.example.administrator.magemata.activity.publishes.base.PublishBase;
import com.example.administrator.magemata.constant.Constant;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/14.
 */
public class PrintActivity extends PublishBase {
    private SimpleAdapter simplead;
    private ListView baselv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_base);
        baselv = (ListView) findViewById(R.id.base_lv);
        simplead = initAdapter_print(PrintActivity.this);
        baselv.setAdapter(simplead);
        setListener(PrintActivity.this, baselv);


    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PrintActivity.class);
        context.startActivity(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ImageMessage event) {
        String title = event.getTitle();
        String content = event.getContent();
        Bitmap logo = event.getBitmap();
        String time = Constant.TIME;
        Map<String, Object> listem = new HashMap<String, Object>();
        listem.put("logo", logo);
        listem.put("title", title);
        listem.put("content", content);
        listem.put("time",time);
        getListems().add(listem);
        simplead.notifyDataSetChanged();
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()){
            case 0:
                AddItemBase.actionStart(this,"print");
                break;
        }
        return true;
    }


}

