package com.example.administrator.magemata.activity.circle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.administrator.magemata.Events.CardMessage;
import com.example.administrator.magemata.Events.CircleMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.BaseActivity;
import com.example.administrator.magemata.activity.MychatActivity;
import com.example.administrator.magemata.adapter.SkinSettingManager;
import com.example.administrator.magemata.fragment.MychatFragment;
import com.example.administrator.magemata.model.Message;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
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

public class CardActivity  extends BaseActivity {
    private static  int COMCOUNT = 0;
    @ViewInject(R.id.card_et_comment)
    EditText comment;
    @ViewInject(R.id.card_lv_comment)
    ListView commentlv;
    @ViewInject(R.id.card_tv_count)
    TextView commentcount;
    @ViewInject(R.id.card_item_image)
    ImageView imageView;
    @ViewInject(R.id.card_vv_video)
    VideoView videoView;
    @ViewInject(R.id.card_tv_content)
    TextView tv_content;
    @ViewInject(R.id.card_tv_type)
    TextView tv_type;


    private SimpleAdapter simplead;
    private List<Map<String, Object>> listems=new ArrayList<Map<String, Object>>();;
    private Map<String, Object> listem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        x.view().inject(this);
        initCardContent();
        setAdapter();
    }
    private void initCardContent(){
        String type = getIntent().getStringExtra("type");
        if(type!=null) {
            String content = getIntent().getStringExtra("content");
            Uri uri=getIntent().getParcelableExtra("uri");
            Bitmap bitmap = getIntent().getParcelableExtra("bitmap");
            tv_content.setText(content);
            tv_type.setText(type);

            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            if(uri!=null) {
                videoView.setVisibility(View.VISIBLE);
                Log.e("sdf",uri.toString());
                videoView.setMediaController(new MediaController(this));
                videoView.setVideoURI(uri);
                videoView.start();
            }
        }
    }


    @Event(value = R.id.card_bt_comment ,type=View.OnClickListener.class)
    private void addComment(View view){
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
                new int[]{R.id.card_user, R.id.card_tv_content, R.id.card_time});
        commentlv.setAdapter(simplead);
    }

    static public void actionStart(Context context , @Nullable String type,@Nullable String content,@Nullable Bitmap bitmap,@Nullable Uri uri){
        Intent intent=new Intent(context,CardActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("bitmap",bitmap);
        intent.putExtra("uri",uri);
        intent.putExtra("content",content);
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
