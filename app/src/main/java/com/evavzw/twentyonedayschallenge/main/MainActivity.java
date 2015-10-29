package com.evavzw.twentyonedayschallenge.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.evavzw.twentyonedayschallenge.R;

public class MainActivity extends AppCompatActivity {

    ViewPager vpMain;
    MainViewPagerAdapter mvAdapter;
    SlidingTabLayout slidingTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvAdapter = new MainViewPagerAdapter(getSupportFragmentManager());

        vpMain = (ViewPager) findViewById(R.id.vpMain);
        vpMain.setAdapter(mvAdapter);

        slidingTabs = (SlidingTabLayout) findViewById(R.id.slidingTabs);
        slidingTabs.setDistributeEvenly(true);

        slidingTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return ContextCompat.getColor(getBaseContext(), R.color.tabsScrollColor);
            }
        });

        slidingTabs.setViewPager(vpMain);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
