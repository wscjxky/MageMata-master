package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.fragment.CircleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/30.
 */

public class AddCardActivity extends AppCompatActivity {
    private static final int PHOTO_REQUEST_GALLERY = 100 ;
    private static final int PHOTO_REQUEST_CUT = 200 ;
    @BindView(R.id.addcard_content)
    EditText addcard_content;
    @BindView(R.id.addcard_coin)
    EditText addcard_coin;
    @BindView(R.id.addcard_addimg)
    ImageView addimg;
    static public void actionStart(Context context){
        Intent intent=new Intent(context,CircleActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.addcard_addimg)
    public void addImg(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    @OnClick(R.id.addcard_submit)
    public void submit(){
        String user="第一个用户";
        String content=addcard_content.getText().toString();
        String time="2017-04-30 12:00:23";
        Intent intent = new Intent();
        intent.putExtra("user", user);
        intent.putExtra("content", content);
        intent.putExtra("time", time);
        addimg.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(addimg.getDrawingCache());
        intent.putExtra("bitmap", bitmap);
        addimg.setDrawingCacheEnabled(false);
        setResult(CircleFragment.CIRCLE_RESULT, intent);
        this.finish();
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if(data !=null){
                Uri photoUri = data.getData();
                //获取照片路径
                crop(photoUri);
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
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
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
