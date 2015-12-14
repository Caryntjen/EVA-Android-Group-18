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

public class SocialMediaChallengeActivity extends AppCompatActivity implements View.OnClickListener {
    // UI references.
    private ImageView ivSocialMediaImage;
    private ImageView ivStarOne;
    private ImageView ivStarTwo;
    private static Button btnVerify;
    private TextView tvPoints;
    private TextView tvSocialMediaChallengeExplanation;
    private Intent exitIntent = null;

    private int state = 0;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String stateprefsTitle;


    private static final int CAMERAREQUEST = 1888;
    private Bitmap photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_socialmedia);
        ivSocialMediaImage = (ImageView) findViewById(R.id.ivSocialMediaImage);
        ivStarOne = (ImageView) findViewById(R.id.ivStarOne);
        ivStarOne.setVisibility(View.VISIBLE);
        ivStarOne.setImageResource(getIntent().getIntExtra("starImage",0));
        ivStarTwo = (ImageView) findViewById(R.id.ivStarTwo);
        //tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvSocialMediaChallengeExplanation = (TextView) findViewById(R.id.tvSocialMediaChallengeExplanation);

        exitIntent = new Intent(this, MainActivity.class);

        stateprefsTitle = getIntent().getStringExtra("stateprefs");


        sharedPreferences = getApplicationContext().getSharedPreferences("ChallengePreferences2", Context.MODE_PRIVATE);
        int stateprefs = sharedPreferences.getInt(stateprefsTitle, state);
        if(state<stateprefs){
            state = stateprefs;
        }

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
                        //TODO: Backend set challengeChosen to true
                        state++;
                        sharedPreferences = getApplicationContext().getSharedPreferences("ChallengePreferences2", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putInt(stateprefsTitle, state);
                        editor.commit();

                        updateButton();
                        sharedPreferences = getApplicationContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
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
        builder.setMessage(R.string.challenges_begin_dialog).setPositiveButton(R.string.text_yes, dialogClickListener)
                .setNegativeButton(R.string.text_no, dialogClickListener).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERAREQUEST && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            state++;
            sharedPreferences = getApplicationContext().getSharedPreferences("ChallengePreferences2", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putInt(stateprefsTitle, state);
            editor.commit();
            updateButton();
        }
    }

    private void updateButton() {
        switch (state) {
            case 0:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkGreen));
                btnVerify.setText(R.string.challenges_state_begin);
                break;
            case 1:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaLightGreen));
                btnVerify.setText(R.string.challenges_state_verify);
                break;
            case 2:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkOrange));
                btnVerify.setText(R.string.challenges_state_completed);
                btnVerify.setEnabled(false);
                break;
            case 3:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaGrey));
                btnVerify.setText(R.string.challenges_state_other);
                btnVerify.setEnabled(false);
                break;

            default:
                btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkGreen));
                btnVerify.setText(R.string.challenges_state_begin);
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
                        btnVerify.setText(R.string.challenges_state_begin);
                        createDialog();
                        break;
                    case 1:
                        btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaLightGreen));
                        btnVerify.setText(R.string.challenges_state_verify);

                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERAREQUEST);
                        break;
                    case 2:
                        btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkOrange));
                        btnVerify.setText(R.string.challenges_state_completed);
                        btnVerify.setEnabled(false);
                        break;
                    case 3:
                        btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaGrey));
                        btnVerify.setText(R.string.challenges_state_other);
                        btnVerify.setEnabled(false);
                        break;

                    default:
                        btnVerify.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.evaDarkGreen));
                        btnVerify.setText(R.string.challenges_state_begin);
                        createDialog();
                        break;

                }
                break;
        }
    }
}