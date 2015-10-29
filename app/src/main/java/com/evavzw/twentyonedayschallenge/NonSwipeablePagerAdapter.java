package com.evavzw.twentyonedayschallenge;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Matthias on 29/10/2015.
 */
public class NonSwipeablePagerAdapter extends FragmentPagerAdapter {

    public NonSwipeablePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new RegistrationPartOne();
            case 1:
                return new RegistrationPartTwo();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
