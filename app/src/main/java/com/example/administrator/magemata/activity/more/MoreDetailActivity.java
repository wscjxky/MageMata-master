package com.example.administrator.magemata.activity.more;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/5/16.
 */
public class MoreDetailActivity extends BaseActivity {
    private ImageView addimg;
    private Button changeskin;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moredetail);
        setListener();
    }

    private void setListener() {
        addimg=(ImageView)findViewById(R.id.moredetail_cv_addimg);
        changeskin=(Button)findViewById(R.id.moredetail_but_skin);
        submit=(Button)findViewById(R.id.moredetail_but_submit);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePortrait();
            }
        });
        changeskin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSkin();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    public void changePortrait(){
//        new Permission(activity);
        Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
        startActivityForResult(intent, 200);
    }
    public void changeSkin(){
        ChangeSkinActivity.actionStart(this);
    }
    public void submit(){
        addimg.setDrawingCacheEnabled(true);
        Bitmap logo = Bitmap.createBitmap(addimg.getDrawingCache());
        addimg.setDrawingCacheEnabled(false);
        ImageMessage imageMessage=new ImageMessage();
        imageMessage.setBitmap(logo);
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
                addimg.setImageBitmap(bitmap);
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

    static public void actionStart(Context context){
        Intent intent=new Intent(context,MoreDetailActivity.class);
        context.startActivity(intent);
    }
}
