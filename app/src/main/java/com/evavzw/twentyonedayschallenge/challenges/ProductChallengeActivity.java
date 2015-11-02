package com.evavzw.twentyonedayschallenge.challenges;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.main.MainViewPagerAdapter;
import com.evavzw.twentyonedayschallenge.main.SlidingTabLayout;

public class ProductChallengeActivity extends AppCompatActivity {
    // UI references.
    private ImageView ivProductImage;
    private ImageView ivStarOne;
    private ImageView ivStarTwo;
    private static Button btnVerify;
    private TextView tvPoints;
    private TextView tvProductChallengeExplanation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_product);

        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        ivStarOne = (ImageView) findViewById(R.id.ivStarOne);
        ivStarTwo = (ImageView) findViewById(R.id.ivStarTwo);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvProductChallengeExplanation = (TextView) findViewById(R.id.tvProductChallengeExplanation);

        btnVerify = (Button) findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}