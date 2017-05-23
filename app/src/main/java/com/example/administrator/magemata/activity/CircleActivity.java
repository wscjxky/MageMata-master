package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.adapter.SkinSettingManager;
import com.example.administrator.magemata.constant.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/29.
 */

public class CircleActivity extends BaseActivity {

    private List<Map<String, Object>> listems;
    private SimpleAdapter simplead;
    private Map<String, Object> listem;

    @BindView(R.id.cards_lv)
    public ListView cards_lv;
    @BindView(R.id.card_img)
    public ImageView card_img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setAdapter();
        setListener();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ImageMessage event) {
        String user = event.getTitle();
        String content = event.getContent();
//        Bitmap logo = event.getBitmp();
        String time = Constant.TIME;
        Map<String, Object> listem = new HashMap<String, Object>();
        listem.put("user", user);
        listem.put("content", content);
        listem.put("time", "2017-04-30 12:00:23");

        listems.add(listem);
        simplead.notifyDataSetChanged();
    }


    private void setAdapter() {
        String user="第一个用户";
        String content="我去问哪里出了山河谷哪里有卖，万能的东西，如果女票有点时候会想到苏打粉蔷薇";
        String time="2017-04-30 12:00:23";
        listems = new ArrayList<Map<String, Object>>();
        listem = new HashMap<String, Object>();
        listem.put("user", user);
        listem.put("content", content);
        listem.put("time", time);
        listems.add(listem);

        simplead = new SimpleAdapter(CircleActivity.this, listems,
                R.layout.card_item, new String[]{"user", "content", "time"},
                new int[]{R.id.card_user, R.id.card_content, R.id.card_time});
        cards_lv.setAdapter(simplead);

    }
    private void setListener() {
        cards_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CardActivity.actionStart(CircleActivity.this);
            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == CARD_REQ && resultCode==CARD_RESULT)
//        {
//            String user = data.getStringExtra("user");
//            String content = data.getStringExtra("content");
//            String time = data.getStringExtra("time");
//            Map<String, Object> listem = new HashMap<String, Object>();
//            listem.put("user", user);
//            listem.put("content", content);
//            listem.put("time", "2017-04-30 12:00:23");
//            listems.add(listem);
//            simplead.notifyDataSetChanged();
//        }
//    }

    @OnClick(R.id.card_add)
    public void showPop(){
        showPopwindow();
    }

    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.addcard_popwin, null);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.setFocusable(true);
        window.showAtLocation(card_img,
                Gravity.BOTTOM, 0, 0);
        // 这里检验popWindow里的button是否可以点击
        Button addReward=(Button)view.findViewById(R.id.addcard_reward);
        addReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(CircleActivity.this,AddCardActivity.class);
//                startActivityForResult(intent, CARD_REQ);
                AddCardActivity.actionStart(CircleActivity.this);
                    Log.e("sdf","Asdf");
                window.dismiss();
            }
        });
    }

    static public void actionStart(Context context){
            Intent intent=new Intent(context,CircleActivity.class);
            context.startActivity(intent);
        }
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        Log.e("stop,","asd");
        super.onDestroy();
    }
}
