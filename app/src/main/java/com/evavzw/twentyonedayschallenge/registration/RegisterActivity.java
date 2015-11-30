package com.evavzw.twentyonedayschallenge.registration;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.models.Registration;
import com.evavzw.twentyonedayschallenge.services.UserDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.Call;

public class RegisterActivity extends FragmentActivity implements RegistrationPartOne.Callback {

    private RegistrationNonSwipeablePagerAdapter paRegistration;
    private ViewPager vpRegister;
    public Registration registration = new Registration();
    UserDataService service;


    public void onConnected(View view, Bundle savedInstanceState)
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://laptop-nico:54967/").addConverterFactory(GsonConverterFactory.create(gson)).build();
            service = retrofit.create(UserDataService.class);
        }

        registration.Email = getIntent().getExtras().getString("email");
        registration.UserName = registration.Email;
        registration.password = getIntent().getExtras().getString("password");

        paRegistration = new RegistrationNonSwipeablePagerAdapter(getSupportFragmentManager());
        vpRegister = (ViewPager) findViewById(R.id.vpRegistration);
        vpRegister.setAdapter(paRegistration);

        /*Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.3.2:54967/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        service = retrofit.create(UserDataService.class);*/
    }

    @Override
    public void onButtonClick(View button) {
        vpRegister.setCurrentItem(1);
    }

    public void Register(){
        Call<Registration> call = service.register(registration);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Response response, Retrofit retrofit) {
                    Log.d("Retrofit", "succeeded for android studio");
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.d("Retrofit", t.getMessage());
                }
            });
    }
}