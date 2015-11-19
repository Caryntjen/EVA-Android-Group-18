package com.evavzw.twentyonedayschallenge.firstrun;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.registration.RegistrationNonSwipeablePagerAdapter;

public class FirstRunActivity extends FragmentActivity implements FirstRunPartOne.Callback {

    private FirstRunNonSwipeablePagerAdapter paFirstRun;
    private ViewPager vpFirstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstrun);

        paFirstRun = new FirstRunNonSwipeablePagerAdapter(getSupportFragmentManager());
        vpFirstRun = (ViewPager) findViewById(R.id.vpFirstRun);
        vpFirstRun.setAdapter(paFirstRun);
    }

    @Override
    public void onButtonClick(View button) {
        vpFirstRun.setCurrentItem(1);
    }
}
