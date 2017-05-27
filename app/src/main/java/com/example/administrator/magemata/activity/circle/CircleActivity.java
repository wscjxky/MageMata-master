package com.example.administrator.magemata.activity.circle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.magemata.Events.CardMessage;
import com.example.administrator.magemata.Interface.NoScrollListView;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.BaseActivity;
import com.example.administrator.magemata.constant.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @ViewInject(R.id.circle_lv_content)
    private NoScrollListView cards_lv;
    @ViewInject(R.id.circle_topimg)
    private ImageView card_topimg;
    @ViewInject(R.id.circle_btn_addcircle)
    private ImageButton card_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_circle);
        x.view().inject(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setAdapter();
        setListener();
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CardMessage event) {
        Map<String, Object> listem = new HashMap<String, Object>();
        String type = event.getType();
        String content = event.getContent();
        if(event.hasBitmap()){
            Log.e("youtu ","tsdf");
            Bitmap bitmap =event.getBitmap();
            listem.put("bitmap", bitmap);
        }
        if(event.hasVideo()){
            Uri video=event.getVideo();
            listem.put("uri", video);
//            listem.put("bitmap", R.drawable.video);
        }
        if(event.getCoin()!=null){
            Log.e("金币",event.getCoin());
            listem.put("coin", "悬赏金币为:     "+event.getCoin());
        }
//        String time = Constant.TIME;
        listem.put("type", type);
        listem.put("content", content);
        listems.add(listem);
        simplead.notifyDataSetChanged();
    }


    private void setAdapter() {
        String content="我去问哪里出了山河谷哪里有卖，万能的东西，如果女票有点时候会想到苏打粉蔷薇";
        listems = new ArrayList<Map<String, Object>>();
        for(int i=0;i<5;i++) {
            listem = new HashMap<String, Object>();
            listem.put("type", "文字");
            listem.put("content", content);
            listems.add(listem);
        }
        simplead = new SimpleAdapter(CircleActivity.this, listems,
                R.layout.card_item, new String[]{"type", "content","bitmap","coin"},
                new int[]{R.id.card_tv_type, R.id.card_tv_content,R.id.card_item_image,R.id.card_tv_coin}
        );
        simplead.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                    if (view instanceof ImageView && data instanceof Bitmap) {
                            ImageView i = (ImageView) view;
                            i.setImageBitmap((Bitmap) data);
                            return true;
                }
                return false;
            }
        });

        cards_lv.setAdapter(simplead);

    }

    private void setListener(){
        cards_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CardActivity.actionStart(CircleActivity.this,listems.get(position).get("type").toString(),
                        listems.get(position).get("content").toString(),
                        (Bitmap)listems.get(position).get("bitmap"),(Uri)listems.get(position).get("uri"));

            }
        });
    }


    @Event(value = R.id.circle_btn_addcircle ,type=View.OnClickListener.class)
    private void showPopwindow(View mview) {
        // 利用laymoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.addcard_popwin, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.setFocusable(true);
        window.showAtLocation(card_add,
                Gravity.CENTER, 0, 220);
        //y轴以左下偏移
        // 这里检验popWindow里的button是否可以点击
        Button addReward=(Button)view.findViewById(R.id.addcard_reward);
        addReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(CircleActivity.this,AddCardActivity.class);
//                startActivityForResult(intent, CARD_REQ);
                AddCardActivity.actionStart(CircleActivity.this,"悬赏");
                window.dismiss();
            }
        });
        Button addImage=(Button)view.findViewById(R.id.addcard_addimage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCardActivity.actionStart(CircleActivity.this,"图片");
                window.dismiss();
            }
        });
        view.findViewById(R.id.addcard_addlink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCardActivity.actionStart(CircleActivity.this,"链接");
                window.dismiss();
            }
        });
        view.findViewById(R.id.addcard_addtext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCardActivity.actionStart(CircleActivity.this,"文字");
                window.dismiss();
            }
        });
        view.findViewById(R.id.addcard_addvideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCardActivity.actionStart(CircleActivity.this,"视频");
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
        super.onDestroy();
    }


}
