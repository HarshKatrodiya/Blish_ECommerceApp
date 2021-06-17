package com.example.h.blish_onlienflowerandcake;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewpageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listfragment = new ArrayList<>();
    private final List<String> listtitle = new ArrayList<>();


    public ViewpageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listtitle.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return listfragment.get(i);
    }

    @Override
    public int getCount() {
        return listtitle.size();
    }

    public void Addfragment (Fragment fragment, String title){
        listfragment.add(fragment);
        listtitle.add(title);
    }

}

