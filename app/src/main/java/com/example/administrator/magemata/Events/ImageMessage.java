package com.example.administrator.magemata.Events;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/5/14.
 */

public class ImageMessage {
    private String user;
    private String title;
    private String content;
    private String price;
    private String time;
    private Bitmap bitmap;
    public void setContent(String cont){
        content = cont;
    }
    public void setUser(String use){
        user = use;
    }
    public void setTitle(String tit){
        title = tit;
    }
    public void setBitmap( Bitmap bit){
        bitmap = bit;
    }
    public String getContent(){
        return content;
    }
    public String getTitle(){
        return title;
    }
    public Bitmap getBitmp(){
        return bitmap;
    }
}
