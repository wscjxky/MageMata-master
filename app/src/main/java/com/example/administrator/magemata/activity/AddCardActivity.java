package com.example.administrator.magemata.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.magemata.Events.ImageMessage;
import com.example.administrator.magemata.R;

import com.example.administrator.magemata.activity.more.WalletActivity;
import com.example.administrator.magemata.fragment.CircleFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/30.
 */

public class AddCardActivity extends AppCompatActivity {
    private  int PHOTO_REQUEST_GALLERY = 100 ;
    private  int PHOTO_REQUEST_CUT = 200 ;
    EditText addcard_content;
    ImageView addimg;
    EditText addcard_coin;
    Button submit;
    static public void actionStart(Context context){
        Intent intent=new Intent(context,AddCircleActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        setListener();
    }

    private void setListener() {
        addcard_content=(EditText)findViewById(R.id.addcard_content);
        addimg=(ImageView)findViewById(R.id.addcard_addimg);
        addcard_coin=(EditText)findViewById(R.id.addcard_et_coin);
        submit=(Button)findViewById(R.id.addcard_submit);
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImg();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    public void addImg(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }
    public void submit(){
        Log.e("sdf","Sdf");
        String user="第一个用户";
        String content=addcard_content.getText().toString();
        String time="2017-04-30 12:00:23";
//        Intent intent = new Intent();
//        intent.putExtra("user", user);
//        intent.putExtra("content", content);
//        intent.putExtra("time", time);
        addimg.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(addimg.getDrawingCache());
//        intent.putExtra("bitmap", bitmap);
        addimg.setDrawingCacheEnabled(false);
//        setResult(CircleFragment.CIRCLE_RESULT, intent);
        ImageMessage imageMessage=new ImageMessage();
        imageMessage.setTitle(user);imageMessage.setContent(content);imageMessage.setBitmap(bitmap);
        EventBus.getDefault().post(imageMessage);
        showmDialog();
    }

    private void showmDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(AddCardActivity.this);
        dialog.setTitle("确认发布吗");
        final String coin=addcard_coin.getText().toString();
        dialog.setMessage("你将会失去"+coin+"枚金币");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WalletActivity.COIN-=Integer.parseInt(coin);
                dialog.dismiss();
                AddCardActivity.this.finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
