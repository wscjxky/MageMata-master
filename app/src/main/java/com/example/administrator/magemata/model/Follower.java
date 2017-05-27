package com.example.administrator.magemata.model;

import android.graphics.Bitmap;

import com.example.administrator.magemata.R;

/**
 * Created by Administrator on 2017/5/27.
 */
public class Follower {
    private String introduction;
    private String userName;
    private String UserGrade;
    private String createdAt;
    private String  UserIntroduce;
    private int userPortrait;
    public void setUserGrade(String UserGrade) {
        this.UserGrade=UserGrade    ;
    }

    public String getUserGrade( ) {
        return UserGrade;
    }

    public String getUserIntroduce( ) {
        return UserIntroduce;
    }

    public void setUserIntroduce(String UserIntroduce ) {
        this.UserIntroduce= UserIntroduce;
    }
    public void setUserPortrait(int userPortrait) {
        this.userPortrait=userPortrait    ;
    }

    public int getUserPortrait( ) {
        return R.drawable.leak_canary_icon;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
