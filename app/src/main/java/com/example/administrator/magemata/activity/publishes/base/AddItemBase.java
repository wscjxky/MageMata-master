package com.example.administrator.magemata.activity.publishes.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.magemata.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfound);

        Log.e("ok","sdf");
        addfound_iv_addimg=(ImageView)findViewById(R.id.addfound_iv_addimg) ;
        addfound_et_content=(EditText)findViewById(R.id.addfound_et_content) ;
        addfound_et_name=(EditText)findViewById(R.id.addfound_et_name) ;
        addfound_but_submit=(Button)findViewById(R.id.addfound_but_submit);
        addfound_but_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        addfound_iv_addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImg();
            }
        });
    }

    public void addImg(){
        Log.e("ok","sdf");
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 200);
    }
    public void submit(){
        String content = addfound_et_content.getText().toString();
        String username = addfound_et_name.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("content", content);
        intent.putExtra("title", username);
        addfound_iv_addimg.setDrawingCacheEnabled(true);
        Bitmap logo = Bitmap.createBitmap(addfound_iv_addimg.getDrawingCache());
        intent.putExtra("logo", logo);
        addfound_iv_addimg.setDrawingCacheEnabled(false);
        setResult(101, intent);
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
}
