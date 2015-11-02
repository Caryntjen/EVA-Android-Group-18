package com.evavzw.twentyonedayschallenge.challenges;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;

public class RecipeChallengeActivity extends AppCompatActivity {
    // UI references.
    private ImageView ivRecipeImage;
    private ImageView ivStarOne;
    private ImageView ivStarTwo;
    private static Button btnVerify;
    private TextView tvPoints;
    private TextView tvRecipeChallengeExplanation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_recipe);

        ivRecipeImage = (ImageView) findViewById(R.id.ivRecipeImage);
        ivStarOne = (ImageView) findViewById(R.id.ivStarOne);
        ivStarTwo = (ImageView) findViewById(R.id.ivStarTwo);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvRecipeChallengeExplanation = (TextView) findViewById(R.id.tvRecipeChallengeExplanation);

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