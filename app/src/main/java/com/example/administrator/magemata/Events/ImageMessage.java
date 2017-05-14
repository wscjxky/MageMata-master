package com.example.administrator.magemata.Events;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/5/14.
 */

public class ImageMessage {
    private String mMsg;
    private Bitmap mBitmap;
    public ImageMessage(String msg,Bitmap mbitmap) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
        mBitmap = mbitmap;
    }
    public String getMsg(){
        return mMsg;
    }
    public Bitmap getBitmp(){
        return mBitmap;
    }
}
