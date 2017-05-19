package com.example.administrator.magemata.activity.publishes.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.MychatActivity;
import com.example.administrator.magemata.adapter.SkinSettingManager;
import com.example.administrator.magemata.fragment.MychatFragment;

/**
 * Created by Administrator on 2017/5/4.
 */

public class InfoBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infobase);


        TextView title=(TextView)findViewById(R.id.base_tv_title);
        TextView content=(TextView)findViewById(R.id.base_tv_content);
        title.setText(getIntent().getStringExtra("title"));
        content.setText( getIntent().getStringExtra("content"));


        Button contactbtu=(Button)findViewById(R.id.infobase_btu_contactuser);
        contactbtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MychatFragment.ADDUSER=true;
                MychatActivity.actionStart(InfoBase.this);
            }
        });
    }

    public static void actionStart(Context context,String title,String content) {
        Intent intent=new Intent(context,InfoBase.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SkinSettingManager mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();
    }
}