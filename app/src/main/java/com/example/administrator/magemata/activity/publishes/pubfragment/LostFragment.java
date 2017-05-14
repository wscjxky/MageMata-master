package com.example.administrator.magemata.activity.publishes.pubfragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.publishes.base.AddItemBase;
import com.example.administrator.magemata.activity.publishes.base.InfoBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/4.
 */

public class LostFragment extends Fragment {
    private ListView baselv;
    private List<Map<String, Object>> listems;
    private SimpleAdapter simplead;
    private Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.listview_base, container, false);
        this.activity=getActivity();
        baselv=(ListView)mview.findViewById(R.id.base_lv) ;
        ButterKnife.bind(this,mview);
        return mview;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setAdapter();


        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add("发布新信息");
        menu.add("测试");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()){
            case 0:
                Intent intent = new Intent(activity,AddItemBase.class);
                startActivityForResult(intent, 100);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode==101)
        {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            Bitmap logo = data.getParcelableExtra("logo");
            String time = data.getStringExtra("time");
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("logo", logo);
            listem.put("title", title);
            listem.put("content", content);
            listem.put("time", "2017-04-30 12:00:23");
            listems.add(listem);
            simplead.notifyDataSetChanged();
        }
    }
    private void setAdapter()
    {
        Map<String, Object> listem;
        listems = new ArrayList<Map<String, Object>>();
        listem = new HashMap<String, Object>();
        listem.put("logo", R.drawable.__leak_canary_icon);
        listem.put("title", "校园卡");
        listem.put("content","很急");
        listem.put("time", "2017-04-30 12:00:23");
        listems.add(listem);
        simplead = new SimpleAdapter(activity, listems,
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
        baselv.setAdapter(simplead);
        baselv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoBase.actionStart(activity);
            }
        });

    }
}
