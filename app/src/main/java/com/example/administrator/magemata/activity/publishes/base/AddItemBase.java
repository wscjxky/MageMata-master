package com.example.administrator.magemata.activity.publishes.base;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.adapter.SkinSettingManager;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/4.
 */

public class AddItemBase extends AppCompatActivity {
    private ImageView addfound_iv_addimg;
    private EditText addfound_et_content;
    private EditText addfound_et_name;
    private Button addfound_but_submit;
    private TextView addprint_tv_filename;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (getIntent().getStringExtra("type")){
            case "lost":
                setContentView(R.layout.activity_addfound);
                break;
            case "usedgood":
                setContentView(R.layout.activity_addusedgood);
                break;
            case "print":
                setContentView(R.layout.activity_addprint);
                break;
            default:
                setContentView(R.layout.activity_addfound);
                break;
        }

        addfound_iv_addimg=(ImageView)findViewById(R.id.addfound_iv_addimg);
        addfound_et_content=(EditText)findViewById(R.id.addfound_et_content) ;
        addfound_et_name=(EditText)findViewById(R.id.addfound_et_name) ;
        addprint_tv_filename=(TextView)findViewById(R.id.addprint_tv_filename) ;
        addfound_but_submit=(Button)findViewById(R.id.addfound_but_submit);
        addfound_but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        if(Objects.equals(getIntent().getStringExtra("type"), "print"))
            addfound_iv_addimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addData("file");
                }
            });
        else
            addfound_iv_addimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addData("image");
                }
            });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addData(String type){
        if(Objects.equals(type, "image")){
            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 200);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent,500);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void submit(){
        String content = addfound_et_content.getText().toString();
        String username = addfound_et_name.getText().toString();
        addfound_iv_addimg.setDrawingCacheEnabled(true);
        Bitmap logo = Bitmap.createBitmap(addfound_iv_addimg.getDrawingCache());
        addfound_iv_addimg.setDrawingCacheEnabled(false);
        ImageMessage imageMessage=new ImageMessage();
        imageMessage.setTitle(username);imageMessage.setContent(content);imageMessage.setBitmap(logo);

        if(Objects.equals(getIntent().getStringExtra("type"), "found")){
            Intent intent=new Intent();
            intent.putExtra("title",username);
            intent.putExtra("content",content);
            intent.putExtra("logo",logo);
            setResult(101,intent);}
        else
            EventBus.getDefault().post(imageMessage);
        this.finish();
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if(data !=null){
                Uri photoUri = data.getData();
                //获取照片路径
                crop(photoUri);
            }
        } else if (requestCode == 300) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                addfound_iv_addimg.setImageBitmap(bitmap);
            }

        }
        else if(requestCode==500){
            if (data != null) {
                Uri uri = data.getData();
                addprint_tv_filename.setText(uri.getPath());
            }
        }
    }


    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, 300);
    }


    public static void actionStart(Context context,String type) {
        Intent intent = new Intent(context, AddItemBase.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Resume","pk");
        SkinSettingManager mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();
    }
}
