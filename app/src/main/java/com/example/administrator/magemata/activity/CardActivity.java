package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.publishes.base.AddItemBase;
import com.example.administrator.magemata.fragment.MychatFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/30.
 */

public class CardActivity  extends AppCompatActivity {
    private static  int COMCOUNT = 0;
    @BindView(R.id.card_et_comment)
    EditText comment;
    @BindView(R.id.card_lv_comment)
    ListView commentlv;
    @BindView(R.id.card_tv_count)
    TextView commentcount;
    private SimpleAdapter simplead;
    private List<Map<String, Object>> listems=new ArrayList<Map<String, Object>>();;
    private Map<String, Object> listem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        setAdapter();
    }
    @OnClick(R.id.card_bt_comment)
    public void addComment(){
        String ct=comment.getText().toString();
        listem = new HashMap<String, Object>();
        listem.put("user","第二个用户");
        listem.put("comment", ct);
        listem.put("time", "2017-04-30 12:00:23");
        listems.add(listem);
        Log.e("","sadf");
        simplead.notifyDataSetChanged();
        comment.setText("");
        commentcount.setText("评论("+(COMCOUNT+=1)+")");
    }

    private void setAdapter(){
        simplead = new SimpleAdapter(CardActivity.this, listems,
                R.layout.card_item, new String[]{"user", "comment", "time"},
                new int[]{R.id.card_user, R.id.card_content, R.id.card_time});
        commentlv.setAdapter(simplead);
    }

    static public void actionStart(Context context){
        Intent intent=new Intent(context,CardActivity.class);
        context.startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.circle_menu, menu);
        menu.add("联系发布者");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()){
            case 0:
                MychatFragment.ADDUSER=true;
                MychatActivity.actionStart(CardActivity.this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
