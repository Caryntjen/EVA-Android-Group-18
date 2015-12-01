package com.evavzw.twentyonedayschallenge.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.evavzw.twentyonedayschallenge.R;

/**
 * A FragmentActivity to start the Registration process.
 */

public class RegisterActivity extends FragmentActivity implements RegistrationPartOne.Callback {

    private RegistrationNonSwipeablePagerAdapter paRegistration;
    private ViewPager vpRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        paRegistration = new RegistrationNonSwipeablePagerAdapter(getSupportFragmentManager());
        vpRegister = (ViewPager) findViewById(R.id.vpRegistration);

        vpRegister.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    //Hide keyboard on second registration part
                    final InputMethodManager imm = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(vpRegister.getWindowToken(), 0);
                }
            }

            @Override
            public void onPageScrolled(int position, float offset, int offsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        vpRegister.setAdapter(paRegistration);
    }

    @Override
    public void onButtonClick(View button) {
        vpRegister.setCurrentItem(1);
    }


}
