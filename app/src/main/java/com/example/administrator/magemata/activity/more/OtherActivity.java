package com.example.administrator.magemata.activity.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.BaseActivity;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by Administrator on 2017/5/19.
 */

public class OtherActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
//        PhotoView subway =(PhotoView)findViewById(R.id.otheracti_pv_subway);
//        subway.setImageResource(R.drawable.subway);
        findViewById(R.id.other_linel_submway).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLinelay();
            }
        });
    }
    private void clickLinelay(){
        PhotoView imgView = new PhotoView(this);
        //用于居中显示，定义view的显示
//        imgView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        imgView.setImageResource(R.drawable.subway);

        //用于全屏黑色显示
        final AlertDialog dialog = new AlertDialog.Builder(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen).create();
        //单击推出
        imgView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                    dialog.dismiss();
            }
        });
        dialog.setView(imgView);

        dialog.show();

    }
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OtherActivity.class);
        context.startActivity(intent);
    }


}
