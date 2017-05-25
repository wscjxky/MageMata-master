package com.example.administrator.magemata.activity.circle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.administrator.magemata.Events.CircleMessage;
import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.BaseActivity;
import com.example.administrator.magemata.fragment.CircleFragment;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/27.
 */

public class AddCircleActivity extends BaseActivity {
    private   int PHOTO_REQUEST_GALLERY = 100 ;
    private   int PHOTO_REQUEST_CUT = 200 ;
    private Activity activity;
    @ViewInject(R.id.addcircle_ciclename)
     EditText et_ciclename;
    @ViewInject(R.id.addcircle_username)
      EditText et_username;
    @ViewInject(R.id.addcircle_addimg)
      ImageView addimg;
    @ViewInject(R.id.addcircle_phone)
       EditText et_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcircle);
        x.view().inject(this);
    }


    @Event(value = R.id.addcircle_addimg ,type=View.OnClickListener.class)
    private void addImg(View view){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    @Event(value = R.id.addcircle_submit ,type=View.OnClickListener.class)
    private void submit(View view){
        String ciclename = et_ciclename.getText().toString();
        String phone = et_phone.getText().toString();
        String username = et_username.getText().toString();
//        Intent intent = new Intent();
//        intent.putExtra("ciclename", ciclename);
//        intent.putExtra("phone", phone);
//        intent.putExtra("username", username);
        addimg.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(addimg.getDrawingCache());
//        intent.putExtra("bitmap", bitmap);
        addimg.setDrawingCacheEnabled(false);
        CircleMessage imageMessage=new CircleMessage();
        imageMessage.setTitle(ciclename);imageMessage.setContent(username);imageMessage.setBitmap(bitmap);
        EventBus.getDefault().post(imageMessage);
//        setResult(CircleFragment.CIRCLE_RESULT, intent);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
               this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
