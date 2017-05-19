package com.example.administrator.magemata.activity.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.BaseActivity;
import com.example.administrator.magemata.activity.MychatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/5/19.
 */

public class WalletActivity extends BaseActivity {
    static public int COIN=10000;
    private TextView remaincoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        setListener();

    }

    private void setListener() {
        remaincoin=(TextView)findViewById(R.id.wallet_tv_remaincoin) ;
        remaincoin.setText(COIN+" ");
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, WalletActivity.class);
        context.startActivity(intent);
    }
}
