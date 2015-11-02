package com.evavzw.twentyonedayschallenge.challenges;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;

public class SocialMediaChallengeActivity extends AppCompatActivity {
    // UI references.
    private ImageView ivSocialMediaImage;
    private ImageView ivStarOne;
    private ImageView ivStarTwo;
    private static Button btnVerify;
    private TextView tvPoints;
    private TextView tvSocialMediaChallengeExplanation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_socialmedia);

        ivSocialMediaImage = (ImageView) findViewById(R.id.ivSocialMediaImage);
        ivStarOne = (ImageView) findViewById(R.id.ivStarOne);
        ivStarTwo = (ImageView) findViewById(R.id.ivStarTwo);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvSocialMediaChallengeExplanation = (TextView) findViewById(R.id.tvSocialMediaChallengeExplanation);

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