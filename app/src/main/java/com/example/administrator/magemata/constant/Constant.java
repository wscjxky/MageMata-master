package com.example.administrator.magemata.constant;

import android.graphics.drawable.Drawable;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.model.User;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/14.
 */

public class Constant {
    static public String TITLE="商品名称";
    static public String CONTENT="在这里输入内容";
    static public String TIME="2017-04-30 12:00:23";
    static public int LOGO= R.drawable.photo;

    static public String LOST_TITLE="校园卡";
    static public String LOST_CONTENT="在某某丢失了校园卡很着急，请联系";


    public static final ArrayList<String> IMAGES = new ArrayList<String>() {
        {
            add("http://img.qq745.com/uploads/allimg/141231/1-1412310J545.jpg");
            add("http://img.qq745.com/uploads/allimg/141231/1-1412310J544-51.jpg");
            add("http://p1.qzone.la/Upload/20170428/20170428112456185577.jpg");
            add("http://i.imgur.com/hRShCT3.png");
            add("http://img06.tooopen.com/images/20170514/tooopen_sy_210126153448.jpg");

        }
    };
    public static final ArrayList<String> NAMES = new ArrayList<String>() {
        {
            add("小明");
            add("小李");
            add("老王");
        }
    };
    public static final User SENDER=new User("0",NAMES.get(0),IMAGES.get(0),true);
    public static final User RECEIVER =new User("1",NAMES.get(1),IMAGES.get(1),true);
    public static final User OTHER =new User("2",NAMES.get(2),IMAGES.get(2),false);
}
