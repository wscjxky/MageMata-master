package com.example.administrator.magemata.adapter;

import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.model.Follower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */

public class FollowercyAdapter extends BaseQuickAdapter<Follower, BaseViewHolder> {

    public FollowercyAdapter() {
        super(R.layout.recyclelist_cv_follw, getSampleData(14));
    }

    @Override
    protected void convert(BaseViewHolder helper, Follower item) {
        helper.addOnClickListener(R.id.follow_item_moreimage);
        helper.setBackgroundRes(R.id.follow_item_portrait,item.getUserPortrait());
        helper.setText(R.id.follow_item_name, item.getUserName());
        helper.setText(R.id.follow_item_grade, item.getUserGrade());
        helper.setText(R.id.follow_item_introduce, item.getUserIntroduce());
    }

    private  static List<Follower> getSampleData(int lenth) {
        List<Follower> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Follower status = new Follower();
            status.setUserName("蓝影态" + i);
            status.setCreatedAt("04/05/" + i);
            status.setUserGrade("大一新生");
            status.setUserIntroduce("一个软萌妹子");
            list.add(status);
        }
        return list;
    }
}