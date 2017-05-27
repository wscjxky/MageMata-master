package com.example.administrator.magemata.activity.more;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.R2;
import com.example.administrator.magemata.activity.BaseActivity;
import com.example.administrator.magemata.activity.MainActivity;
import com.example.administrator.magemata.activity.MychatActivity;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/5/26.
 */
public class UserInfoActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;
    private boolean followed=false;

    @ViewInject(R.id.userinfo_tl_tabs)
    TabLayout tabLayout;
    @ViewInject(R.id.userinfo_vp_viewpager)
    ViewPager viewPager;
    @ViewInject(R.id.userinfo_al_appbar)
    AppBarLayout appbarLayout;
    @ViewInject(R.id.user_cv_userimage)
    CircleImageView mProfileImage;
    @ViewInject(R.id.userinfo_profile_backdrop)
    ImageView backimage;
    public String[] Title={"关注","粉丝"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        x.view().inject(this);
        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(),Title));
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfileImage.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

    private static class TabsAdapter extends FragmentPagerAdapter {
        private  static final int TAB_COUNT = 2;
        private String[] mTitle;
        TabsAdapter(FragmentManager fm ,String[] mTitle) {
            super(fm);
            this.mTitle=mTitle;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Fragment getItem(int i) {
            return UserPageFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }

    @Event(value = R.id.user_cv_userimage,type = View.OnClickListener.class)
    private void changeUser(View view){
        ChangInfoActivity.actionStart(UserInfoActivity.this);
    }

    @Event(value = R.id.userinfo_btn_chat,type = View.OnClickListener.class)
    private void startChat(View view){
        MychatActivity.actionStart(UserInfoActivity.this);
    }
    @Event(value = R.id.userinfo_profile_backdrop,type = View.OnClickListener.class)
    private void changeBackground(View view){

    }


    public static void actionStart(Context context ){
        Intent intent=new Intent(context,UserInfoActivity.class);
        context.startActivity(intent);
    }
}
