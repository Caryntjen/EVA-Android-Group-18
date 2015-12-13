package com.evavzw.twentyonedayschallenge.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.models.AccountModel;
import com.evavzw.twentyonedayschallenge.models.ChallengeModel;
import com.evavzw.twentyonedayschallenge.models.ChosenChallengeModel;
import com.evavzw.twentyonedayschallenge.models.ScoreModel;
import com.evavzw.twentyonedayschallenge.services.ChallengeDataService;
import com.evavzw.twentyonedayschallenge.services.UserDataService;
import com.evavzw.twentyonedayschallenge.tabfragments.ITabFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

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
    public ScoreModel sm;
    public ChosenChallengeModel chosenChallenge;

    //Rest adapter
    private RestAdapter retrofit;
    private UserDataService _userService;
    private ChallengeDataService _chalService;

    //genymotion virtual devices
    private String url = "http://10.0.3.2:54967";
    //androidstudio emulators
    //private String url = "http://10.0.2.2:54967";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        //setSupportProgressBarIndeterminateVisibility(true);

        accesToken = getIntent().getExtras().getString("accesToken");
        username = getIntent().getExtras().getString("username");

        //Rest adapter
        Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        RestAdapter retrofit = new RestAdapter.Builder().setConverter(new GsonConverter(gSon)).setEndpoint(url).build();
        _userService = retrofit.create(UserDataService.class);
        _chalService = retrofit.create(ChallengeDataService.class);

        //get data

        _userService.getAccountAccomplishments(accesToken,username,new Callback<ScoreModel>(){
                    @Override
                    public void success(ScoreModel scoreModel, Response response) {
                        if(scoreModel != null) {
                            sm = scoreModel;
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        handleRetrofitError(error);
                    }
                }
        );

        //get chosen challenges
        _chalService.checkForChosenChallenge(accesToken, username, new Callback<ChosenChallengeModel>() {
                    @Override
                    public void success(ChosenChallengeModel chosenChallengeModel, Response response) {
                        if (chosenChallengeModel != null) {
                            chosenChallenge = chosenChallengeModel;

                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        handleRetrofitError(error);
                    }
                }
        );

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
    }

    public void handleRetrofitError(RetrofitError error){
        if (error.getResponse() != null) {
            String errorString = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
            //Error handling here
            Log.e("FAILURE", errorString.toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
