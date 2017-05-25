package com.example.administrator.magemata.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.magemata.Events.CircleMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.circle.AddCircleActivity;
import com.example.administrator.magemata.activity.circle.CircleActivity;
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
 * Created by Administrator on 2017/4/24.
 */

public class CircleFragment extends Fragment {
    public static final int CIRCLE_REQ = 100;
    public static final int CIRCLE_RESULT=101;
    private String[] title = { "作业帮", "cosplay",};

    private String[] content = { "作业帮是中小学生们都在用的拍照搜库阿萨德群无", "COSPLAY是英文Costum、角色艾斯德斯。" };

    private int[] image = { R.drawable.ic_launcher, R.drawable.ic_launcher};
    @BindView(R.id.fg_circle_lv)
    ListView listView;
    private Activity activity;

    private SimpleAdapter simplead;
    private  List<Map<String, Object>> listems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_circle, container, false);
        this.activity=getActivity();
        ButterKnife.bind(this,mview);
        return mview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setAdapter();
        setListView();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CircleMessage event) {
        String ciclename = event.getTitle();
        String username = event.getContent();
        Bitmap bitmap = event.getBitmp();
        String time = Constant.TIME;
        Map<String, Object> listem = new HashMap<String, Object>();
        listem.put("image", bitmap);
        listem.put("title", ciclename);
        listem.put("content", username);
        listems.add(listem);
        simplead.notifyDataSetChanged();
    }
    @OnClick(R.id.fg_circle_add)
    public void addCircle(){
        Intent intent = new Intent(activity,AddCircleActivity.class);
        startActivityForResult(intent, CIRCLE_REQ);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == CIRCLE_REQ && resultCode==CIRCLE_RESULT)
//        {
//            String ciclename = data.getStringExtra("ciclename");
//            String phone = data.getStringExtra("phone");
//            String username = data.getStringExtra("username");
//            Bitmap bitmap = data.getParcelableExtra("bitmap");
//            Map<String, Object> listem = new HashMap<String, Object>();
//            listem.put("image", bitmap);
//            listem.put("title", ciclename);
//            listem.put("content", username);
//            listems.add(listem);
//
//            simplead.notifyDataSetChanged();
//        }
//    }

    private void setAdapter(){
        listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 2; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("image", image[i]);
            listem.put("title", title[i]);
            listem.put("content", content[i]);
            listems.add(listem);
        }
        simplead = new SimpleAdapter(activity, listems,
                R.layout.circles_item, new String[]{"image", "title", "content"},
                new int[]{R.id.circles_iv, R.id.card_user, R.id.circles_content});
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

    }
    private void  setListView(){
        listView.setSelector(R.color.border_clo);
        listView.setAdapter(simplead);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CircleActivity.actionStart(activity);
            }
        });
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        Log.e("stop,","asd");
        super.onDestroy();
    }
}
