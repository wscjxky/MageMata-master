package com.example.administrator.magemata.adapter;

import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.magemata.R;
import com.example.administrator.magemata.fragment.CircleFragment;
import com.example.administrator.magemata.fragment.MoreFragment;
import com.example.administrator.magemata.fragment.MychatFragment;
import com.example.administrator.magemata.fragment.PublishFragment;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

public class MainAdapter extends FragmentStatePagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider {

    private final String[] TITLES = {"圈子","发布","私信","更多"};
    private static final Integer[] ICONS = new Integer[] {
            R.drawable.tab_course,
            R.drawable.tab_ipgw,
            R.drawable.tab_mypku,
            R.drawable.tab_settings,
    };
    private static final Integer[] SELECTEDICONS = new Integer[] {
            R.drawable.tab_course_selected,
            R.drawable.tab_ipgw_selected,
            R.drawable.tab_mypku_selected,
            R.drawable.tab_settings_selected,
    };
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:
                return new CircleFragment();
            case  2:
                return new MychatFragment();
            case  1:
                return new PublishFragment();
            case  3:
                return new MoreFragment();
            default:
                return new PublishFragment();
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

    @Override
    public Integer getPageIcon(int index) {
        return ICONS[index];
    }

    @Override
    public Integer getPageSelectIcon(int index) {
        return SELECTEDICONS[index];
    }

    @Override
    public Rect getPageIconBounds(int position) {
        return null;
    }
}

