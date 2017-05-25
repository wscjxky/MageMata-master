package com.example.administrator.magemata.activity.circle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.administrator.magemata.Events.CardMessage;
import com.example.administrator.magemata.R;

import com.example.administrator.magemata.activity.more.WalletActivity;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Objects;

/**
 * Created by Administrator on 2017/4/30.
 */

public class AddCardActivity extends AppCompatActivity {
    private int VIDEO_REQUEST_GET = 300 ;
    private  int PHOTO_REQUEST_GALLERY = 100 ;
    private  int PHOTO_REQUEST_CUT = 200 ;
    @ViewInject(R.id.addcard_content)
    EditText addcard_content;
    @ViewInject(R.id.addcard_addimg)
    ImageView addimg;
    @ViewInject(R.id.addcard_et_coin)
    EditText addcard_coin;
    @ViewInject(R.id.addcard_submit)
    Button submit;
    @ViewInject(R.id.addcard_vv_videoView)
    private VideoView videoView;
    @ViewInject(R.id.addcard_ll_inputcoin)
    LinearLayout inputcoin;
    @ViewInject(R.id.addcard_ll_addlayout)
    LinearLayout addlayout;


    private String type;
    private String coin;
    private Uri video;
    static public void actionStart(Context context , String type){
        Intent intent=new Intent(context,AddCardActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        x.view().inject(this);
        type=getIntent().getStringExtra("type");
        initContent();
    }

    private void initContent() {
        switch (type) {
            case "图片":
                addimg.setVisibility(View.VISIBLE);
                break;
            case "文字":
                addlayout.setVisibility(View.GONE);
                break;
            case "链接":
                addlayout.setVisibility(View.GONE);
                break;
            case "视频":
                videoView.setVisibility(View.VISIBLE);
                videoView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        addVideo();
                        return false;
                    }
                });
                break;
            case "悬赏":
                addlayout.setVisibility(View.GONE);
                inputcoin.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
    @Event(value = R.id.addcard_addimg ,type=View.OnClickListener.class)
    private void addImg(View view) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }


    private void addVideo() {
        Log.e("cickl","Sdf");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("video/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, VIDEO_REQUEST_GET);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Event(value = R.id.addcard_submit ,type=View.OnClickListener.class)
    private void submit(View view){
        if(Objects.equals(addcard_content.getText().toString(), "")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(AddCardActivity.this);
            dialog.setTitle("注意");
            dialog.setMessage("内容不能为空哦");
            dialog.setCancelable(false);
            dialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        else {
            String content = addcard_content.getText().toString();
            CardMessage CardMessage = new CardMessage();
            CardMessage.setType(type);
            CardMessage.setTitle(type);
            CardMessage.setContent(content);
            switch (type) {
                case "图片":
                    addimg.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(addimg.getDrawingCache());
                    addimg.setDrawingCacheEnabled(false);
                    CardMessage.setBitmap(bitmap);
                    break;
                case "视频":
                    CardMessage.setVideo(video);
                    break;
                case "悬赏":
                    coin=addcard_coin.getText().toString();
                    CardMessage.setCoin(coin);
                    break;
                case "链接":
                    break;
                default:
                    break;
            }
            showConfirmDialog(CardMessage);
        }




//        intent.putExtra("bitmap", bitmap);

//        setResult(CircleFragment.CIRCLE_RESULT, intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showConfirmDialog(final CardMessage CardMessage) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(AddCardActivity.this);
        dialog.setTitle("确认发布吗");
        if(Objects.equals(type, "悬赏"))
            dialog.setMessage("你将会失去"+coin+"枚金币");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(Objects.equals(type, "悬赏"))
                    WalletActivity.COIN-=Integer.parseInt(coin);
                EventBus.getDefault().post(CardMessage);
                dialog.dismiss();
                AddCardActivity.this.finish();
//                submit();
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
        else if (requestCode == VIDEO_REQUEST_GET) {
            if (data != null) {
                video = data.getData();
                Log.e("sdf",video.toString());

                videoView.setMediaController(new MediaController(this));
                videoView.setVideoURI(video);
                videoView.start();
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
