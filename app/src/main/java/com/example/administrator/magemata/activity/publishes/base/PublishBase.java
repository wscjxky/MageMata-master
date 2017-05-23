package com.example.administrator.magemata.activity.publishes.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.publishes.LostActivity;
import com.example.administrator.magemata.activity.publishes.UsedActivity;
import com.example.administrator.magemata.adapter.SkinSettingManager;
import com.example.administrator.magemata.constant.Constant;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/30.
 */

public class PublishBase extends AppCompatActivity{
    private List<Map<String, Object>> listems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public  SimpleAdapter initAdapter(Context context){
        Map<String, Object> listem;
        SimpleAdapter simplead;
        listems = new ArrayList<Map<String, Object>>();
        listem = new HashMap<String, Object>();
        listem.put("logo",Constant.LOGO);
        listem.put("title", Constant.TITLE);
        listem.put("content",Constant.CONTENT);
        listem.put("time",Constant.TIME);
        listems.add(listem);
        simplead = new SimpleAdapter(context, listems,
                R.layout.imgitem_base, new String[]{"logo","title", "content", "time"},
                new int[]{R.id.base_iv_logo, R.id.base_tv_title, R.id.base_tv_content,R.id.base_tv_time});
        simplead.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if(view instanceof ImageView && data instanceof Bitmap){
                    ImageView i = (ImageView)view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        return simplead;
    }
    public  SimpleAdapter initAdapter_print(Context context){
        Map<String, Object> listem;
        SimpleAdapter simplead;
        listems = new ArrayList<Map<String, Object>>();
        listem = new HashMap<String, Object>();
        listem.put("logo",Constant.LOGO);
        listem.put("title", "打印信息");
        listem.put("content","彩印，文件名");
        listem.put("time",Constant.TIME);
        listems.add(listem);
        simplead = new SimpleAdapter(context, listems,
                R.layout.imgitem_base, new String[]{"logo","title", "content", "time"},
                new int[]{R.id.base_iv_logo, R.id.base_tv_title, R.id.base_tv_content,R.id.base_tv_time});
        simplead.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if(view instanceof ImageView && data instanceof Bitmap){
                    ImageView i = (ImageView)view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        return simplead;
    }

    public void setListener(final Context context, ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoBase.actionStart(context,Constant.TITLE,Constant.CONTENT);
            }
        });
    }
    //Toast
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.circle_menu, menu);
        menu.add("发布新信息");
        menu.add("测试");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()){
            case 0:
                AddItemBase.actionStart(this,"lost");
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public  List<Map<String, Object>> getListems(){
        return listems;
    }
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        Log.e("stop,","asd");
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SkinSettingManager mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();
    }
}