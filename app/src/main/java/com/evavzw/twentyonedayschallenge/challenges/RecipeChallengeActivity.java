package com.evavzw.twentyonedayschallenge.challenges;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.main.MainActivity;
import com.evavzw.twentyonedayschallenge.tabfragments.ChallengesFragment;

public class RecipeChallengeActivity extends AppCompatActivity implements View.OnClickListener {
    // UI references.
    private ImageView ivRecipeImage;
    private ImageView ivStarOne;
    private ImageView ivStarTwo;
    private static Button btnVerify;
    private TextView tvPoints;
    private TextView tvRecipeChallengeExplanation;
    private Intent exitIntent = null;
    private Intent putExtraIntent = null;

    private int state = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    private static final int CAMERAREQUEST = 1888;
    private Bitmap photo;

    //TODO; State needs to be reset after the 21 days.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_recipe);

        ivRecipeImage = (ImageView) findViewById(R.id.ivRecipeImage);
        ivStarOne = (ImageView) findViewById(R.id.ivStarOne);
        ivStarTwo = (ImageView) findViewById(R.id.ivStarTwo);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvRecipeChallengeExplanation = (TextView) findViewById(R.id.tvRecipeChallengeExplanation);

        exitIntent = new Intent(this, MainActivity.class);

        sharedPreferences = getApplicationContext().getSharedPreferences("statepreferences", Context.MODE_PRIVATE);
        state = sharedPreferences.getInt("state", state);

        btnVerify = (Button) findViewById(R.id.btnVerify);
        btnVerify.setOnClickListener(this);
        updateButton();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void createDialog() {


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        state++;
                        sharedPreferences = getApplicationContext().getSharedPreferences("statepreferences", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putInt("state", state);
                        editor.commit();

                        updateButton();

/*
                    String sToken = sharedPreferences.getString("token", "");
                    String sEmail = sharedPreferences.getString("email", "");

                    exitIntent.putExtra("accesToken", sToken);
                    exitIntent.putExtra("username", sEmail);
                    startActivity(exitIntent);
                    */
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to begin this challenge?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERAREQUEST && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            state++;
            editor = sharedPreferences.edit();
            editor.putInt("state", state);
            editor.commit();
            updateButton();
        }
    }

    private void updateButton() {
        switch (state) {
            case 0:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkGreen));
                btnVerify.setText("Begin Challenge");
                break;
            case 1:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaLightGreen));
                btnVerify.setText("Verify Challenge");
                break;
            case 2:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkOrange));
                btnVerify.setText("Challenge Completed");
                btnVerify.setEnabled(false);
                break;

            default:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkGreen));
                btnVerify.setText("Begin Challenge");
                break;

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVerify:
                switch (state) {
                    case 0:
                        btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkGreen));
                        btnVerify.setText("Begin Challenge");
                        createDialog();
                        break;
                    case 1:
                        btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaLightGreen));
                        btnVerify.setText("Verify Challenge");

                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERAREQUEST);
                        break;
                    case 2:
                        btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkOrange));
                        btnVerify.setText("Challenge Completed");
                        btnVerify.setEnabled(false);
                        break;

                    default:
                        btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkGreen));
                        btnVerify.setText("Begin Challenge");
                        createDialog();
                        break;

                }
                break;
        }
    }
}