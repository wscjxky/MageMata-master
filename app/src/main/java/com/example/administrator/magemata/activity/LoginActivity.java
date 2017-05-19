package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.magemata.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/24.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_tv_info)
    EditText editText;
    @BindView(R.id.login_but_submit)
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }



    @OnClick(R.id.login_but_submit)
    public void submit(){
        String value= editText.getText().toString();
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
        MainActivity.actionStart(LoginActivity.this);
    }
    public static void actionStart(Context context ){
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }
}
