package com.zlayar.zlayar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IrfanRZ on 6/2/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmenListTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmenListTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmenListTitle.get(position);
    }
    public void AddFragment(Fragment fragment, String Title){
        fragmentList.add(fragment);
        fragmenListTitle.add(Title);
    }
}
