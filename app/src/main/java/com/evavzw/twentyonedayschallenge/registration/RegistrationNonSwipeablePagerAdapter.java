package com.evavzw.twentyonedayschallenge.registration;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A FragmentPagerAdapter to determine which page needs to be viewed.
 */
public class RegistrationNonSwipeablePagerAdapter extends FragmentPagerAdapter {

    public RegistrationNonSwipeablePagerAdapter(FragmentManager fragmentManager) {
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
