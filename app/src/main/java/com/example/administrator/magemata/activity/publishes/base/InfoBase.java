package com.example.administrator.magemata.activity.publishes.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.MychatActivity;
import com.example.administrator.magemata.fragment.MychatFragment;

/**
 * Created by Administrator on 2017/5/4.
 */

public class InfoBase extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infobase);
        Button contactbtu=(Button)findViewById(R.id.infobase_btu_contactuser);
        contactbtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MychatFragment.ADDUSER=true;
                MychatActivity.actionStart(InfoBase.this);
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, InfoBase.class);
        context.startActivity(intent);
    }
}