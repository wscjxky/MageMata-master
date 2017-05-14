package com.example.administrator.magemata.activity.publishes.pubfragment;

import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.fragment.CircleFragment;
import com.example.administrator.magemata.fragment.MoreFragment;
import com.example.administrator.magemata.fragment.MychatFragment;
import com.example.administrator.magemata.fragment.PublishFragment;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

/**
 * Created by Administrator on 2017/5/4.
 */

public class LostAdapter extends FragmentStatePagerAdapter {

    private final String[] TITLES = {"失物","招领"};
    public LostAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:
                return new LostFragment();
            case  1:
                return new FoundFragment();
            default:
                return new LostFragment();
        }
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

}
