package com.example.administrator.magemata.Events;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/25.
 */

public class CardMessage   {
    private String user;
    private String title;
    private String content;
    private String coin;
    private String time;

    private Bitmap bitmap=null;
    private String type;
    private Uri video=null;
    public void setVideo(Uri video){
        this.video = video;
    }
    public Uri getVideo(){
        return video;
    }
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
    public Bitmap getBitmap(){
        return bitmap;
    }
    public boolean hasBitmap(){
        return bitmap != null;
    }
    public boolean hasVideo(){
        return video != null;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type=type;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
    public String getCoin(){
        return this.coin;
    }

}
