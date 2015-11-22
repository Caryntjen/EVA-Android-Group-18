package com.evavzw.twentyonedayschallenge.registration;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.models.Registration;

public class RegisterActivity extends FragmentActivity implements RegistrationPartOne.Callback {

    private RegistrationNonSwipeablePagerAdapter paRegistration;
    private ViewPager vpRegister;
    public Registration registration = new Registration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        paRegistration = new RegistrationNonSwipeablePagerAdapter(getSupportFragmentManager());
        vpRegister = (ViewPager) findViewById(R.id.vpRegistration);
        vpRegister.setAdapter(paRegistration);
    }

    @Override
    public void onButtonClick(View button) {
        vpRegister.setCurrentItem(1);
    }
}
