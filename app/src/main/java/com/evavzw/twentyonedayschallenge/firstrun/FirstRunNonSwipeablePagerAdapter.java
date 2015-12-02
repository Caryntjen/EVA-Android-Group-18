package com.evavzw.twentyonedayschallenge.firstrun;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FirstRunNonSwipeablePagerAdapter extends FragmentPagerAdapter {

    public FirstRunNonSwipeablePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new FirstRunPartOne();
            case 1:
                return new FirstRunPartTwo();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
