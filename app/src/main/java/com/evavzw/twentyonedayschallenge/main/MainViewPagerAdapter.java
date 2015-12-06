package com.evavzw.twentyonedayschallenge.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.evavzw.twentyonedayschallenge.tabfragments.AccountFragment;
import com.evavzw.twentyonedayschallenge.tabfragments.ChallengesFragment;
import com.evavzw.twentyonedayschallenge.tabfragments.OverviewFragment;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    //Titles for the menu tabs
    private String tabTitles[];

    /*
        Constructor which adds and sets the titles for the menu tabs.
    */
    public MainViewPagerAdapter(String tabTitles[], FragmentManager fragmentManager) {
        super(fragmentManager);
        this.tabTitles = new String[tabTitles.length];
        java.lang.System.arraycopy(tabTitles, 0, this.tabTitles, 0, tabTitles.length);
    }

    /*
        Return the fragment which is swiped/moved to.
    */
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new ChallengesFragment();
            case 1:
                return new OverviewFragment();
            case 2:
                return new AccountFragment();
            default:
                return null;
        }
    }

    /*
        Gives the title of the tabs.
    */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    /*
        Returns the amount of tabs.
    */
    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
