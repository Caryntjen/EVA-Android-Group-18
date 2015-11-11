package com.evavzw.twentyonedayschallenge.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private static CharSequence TABS[] = {"Challenges", "Overview", "Account"};

    public MainViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new ChallengesActivity();
            case 1:
                return new OverviewActivity();
            case 2:
                return new AccountActivity();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}
