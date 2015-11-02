package com.evavzw.twentyonedayschallenge.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.evavzw.twentyonedayschallenge.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpMain;
    private MainViewPagerAdapter mvAdapter;
    private SlidingTabLayout slidingTabs;
    private String currentday =" 5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.evalogo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getSupportActionBar().getTitle() +currentday);

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
        return super.onOptionsItemSelected(item);
    }

}
