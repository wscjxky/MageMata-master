package com.example.administrator.magemata.activity.more;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.magemata.R;
import com.example.administrator.magemata.activity.MychatActivity;
import com.example.administrator.magemata.adapter.FollowercyAdapter;
import com.example.administrator.magemata.model.Follower;

/**
 * Created by Administrator on 2017/5/26.
 */
public class UserPageFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Activity activity;
    private FollowercyAdapter mFollowerAdapter;
    private static boolean FOLLOERED=false;
    private Button addFollower;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_userinfo, container, false);
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity=getActivity();
        initRecyclerView();
        initAdapter();
        initListener();
    }

    private void initListener() {
        addFollower = (Button) activity.findViewById(R.id.userinfo_btn_follow);
        addFollower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!FOLLOERED) {
                    Toast.makeText(activity, "关注成功", Toast.LENGTH_LONG).show();
                    Follower follower = new Follower();
                    follower.setUserName("只是一只卷");
                    follower.setCreatedAt("05/20/");
                    follower.setUserGrade("大二");
                    follower.setUserIntroduce("死宅");
                    mFollowerAdapter.addData(follower);
                }
                else  {
                    Toast.makeText(activity, "取消关注", Toast.LENGTH_LONG).show();
                    Follower follower = new Follower();
                    follower.setUserName("只是一只卷");
                    follower.setCreatedAt("05/20/");
                    follower.setUserGrade("大二");
                    follower.setUserIntroduce("死宅");
                    mFollowerAdapter.remove(0);
                }
            }
        });
    }



    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }
    private void initAdapter() {
        mFollowerAdapter = new FollowercyAdapter();
        mFollowerAdapter.openLoadAnimation();
        mFollowerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MychatActivity.actionStart(activity);
            }
        });
        mRecyclerView.setAdapter(mFollowerAdapter);
    }



    public static Fragment newInstance() {
        return new UserPageFragment();
    }



}

