package com.evavzw.twentyonedayschallenge.firstrun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.login.LoginActivity;

public class FirstRunActivity extends FragmentActivity implements FirstRunPartOne.Callback {

    private FirstRunNonSwipeablePagerAdapter paFirstRun;
    private ViewPager vpFirstRun;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getApplicationContext().getSharedPreferences("FirstRunPreferences", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("firtrun", false))
        {
            Intent loginActivity = new Intent(this, LoginActivity.class);
            startActivity(loginActivity);

        }
        else {
            setContentView(R.layout.activity_firstrun);

            paFirstRun = new FirstRunNonSwipeablePagerAdapter(getSupportFragmentManager());
            vpFirstRun = (ViewPager) findViewById(R.id.vpFirstRun);
            vpFirstRun.setAdapter(paFirstRun);
        }
    }

    @Override
    public void onButtonClick(View button) {
        vpFirstRun.setCurrentItem(1);
    }
}
