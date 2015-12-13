package com.evavzw.twentyonedayschallenge.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.models.AccountModel;
import com.evavzw.twentyonedayschallenge.models.ChallengeModel;
import com.evavzw.twentyonedayschallenge.tabfragments.ITabFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpMain;
    private MainViewPagerAdapter mvAdapter;
    private SlidingTabLayout slidingTabs;

    private final int TABS = 3;
    private String tabTitles[];

    public String accesToken;
    public String username;

    public AccountModel am;
    public List<ChallengeModel> challenges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        //setSupportProgressBarIndeterminateVisibility(true);

        //Filling up tabtiles
        tabTitles = new String[TABS];
        tabTitles[0] = getString(R.string.title_activity_challenges);
        tabTitles[1] = getString(R.string.title_activity_overview);
        tabTitles[2] = getString(R.string.title_activity_account);

        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.evalogo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
            accesToken = getIntent().getExtras().getString("accesToken");
            username = getIntent().getExtras().getString("username");
        mvAdapter = new MainViewPagerAdapter(tabTitles, getSupportFragmentManager());

        vpMain = (ViewPager) findViewById(R.id.vpMain);
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float v, final int i2) {
            }

            @Override
            public void onPageSelected(final int position) {
                //setSupportProgressBarIndeterminateVisibility(true);
                ITabFragment fragment = (ITabFragment) mvAdapter.instantiateItem(vpMain, position);
                if (fragment != null) {
                    fragment.updateFragment();
                }
                //setSupportProgressBarIndeterminateVisibility(false);
            }

            @Override
            public void onPageScrollStateChanged(final int position) {
            }
        });
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
        //setSupportProgressBarIndeterminateVisibility(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
