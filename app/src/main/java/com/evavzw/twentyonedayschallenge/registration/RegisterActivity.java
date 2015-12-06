package com.evavzw.twentyonedayschallenge.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.models.Registration;
import com.evavzw.twentyonedayschallenge.services.UserDataService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * A FragmentActivity to start the Registration process.
 */

public class RegisterActivity extends FragmentActivity implements RegistrationPartOne.Callback {

    private RegistrationNonSwipeablePagerAdapter paRegistration;
    private ViewPager vpRegister;
    private String url = "http://10.0.2.2:54967";
    public Registration registration = new Registration();
    UserDataService service;


    public void onConnected(View view, Bundle savedInstanceState)
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint(url).build();

        service = retrofit.create(UserDataService.class);


        registration.Email = getIntent().getExtras().getString("email");
        registration.UserName = registration.Email;
        registration.password = getIntent().getExtras().getString("password");
        registration.confirmPassword = getIntent().getExtras().getString("password");

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

    public void Register() {
        service.register(registration, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Log.d("SUCCES", response.getBody().toString());
            }

            @Override
            public void failure(RetrofitError error) {

                if (error.getResponse() != null) {
                    String errorString =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
                    //Error handling here
                    Log.e("FAILURE", errorString.toString());
                }
            }
        });
    }
}
