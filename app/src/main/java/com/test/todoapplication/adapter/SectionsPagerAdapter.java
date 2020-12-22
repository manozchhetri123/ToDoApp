package com.test.todoapplication.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.test.todoapplication.R;
import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private ArrayList<Fragment> mFragments;

    public SectionsPagerAdapter(Context context,
                                FragmentManager fm,
                                ArrayList<Fragment> fragments) {
        super(fm);
        mContext = context;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
       return mFragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}