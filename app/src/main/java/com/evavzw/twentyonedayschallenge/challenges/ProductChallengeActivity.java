package com.evavzw.twentyonedayschallenge.challenges;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.main.MainActivity;
import com.evavzw.twentyonedayschallenge.models.ChallengeModel;
import com.evavzw.twentyonedayschallenge.models.ChooseChallengeModel;
import com.evavzw.twentyonedayschallenge.models.CompleteChallengeModel;
import com.evavzw.twentyonedayschallenge.services.ChallengeDataService;
import com.evavzw.twentyonedayschallenge.services.UserDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

public class ProductChallengeActivity extends AppCompatActivity implements View.OnClickListener {
    // UI references.
    private ImageView ivProductImage;
    private ImageView ivStarOne;
    private ImageView ivStarTwo;
    private static Button btnVerify;
    private TextView tvPoints;
    private TextView tvProductChallengeExplanation;
    private TextView tvProductTitle;
    private Intent exitIntent = null;
    private Intent entryIntent = null;

    private int state = 0;
    private ChallengeModel _challengeModel;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String stateprefsTitle;

    private String _username;
    private String _accesToken;

    private static final int CAMERAREQUEST = 1888;
    private Bitmap photo;

    //Rest adapter
    private RestAdapter retrofit;
    private ChallengeDataService _chalService;

    //genymotion virtual devices
    private String url = "http://10.0.3.2:54967";
    //androidstudio emulators
    //private String url = "http://10.0.2.2:54967";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_product);

        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);
        ivStarOne = (ImageView) findViewById(R.id.ivStarOne);
        ivStarOne.setVisibility(View.VISIBLE);
        ivStarOne.setImageResource(getIntent().getIntExtra("starImage",0));
        ivStarTwo = (ImageView) findViewById(R.id.ivStarTwo);
        //tvPoints = (TextView) findViewById(R.id.tvPoints);

        entryIntent = getIntent();

        tvProductChallengeExplanation = (TextView) findViewById(R.id.tvProductChallengeExplanation);
        _challengeModel = (ChallengeModel) entryIntent.getExtras().get("challengeModel");
        tvProductChallengeExplanation.setText(_challengeModel.description);
        tvProductTitle = (TextView) findViewById(R.id.tvProductTitle);
        tvProductTitle.setText(_challengeModel.title);


        // show The Image
        new DownloadImageTask((ImageView) findViewById(R.id.ivProductImage))
                .execute(_challengeModel.image);

        exitIntent = new Intent(this, MainActivity.class);

        _accesToken = getIntent().getExtras().getString("accesToken");
        _username = getIntent().getExtras().getString("username");

        //Rest adapter
        Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        RestAdapter retrofit = new RestAdapter.Builder().setConverter(new GsonConverter(gSon)).setEndpoint(url).build();
        _chalService = retrofit.create(ChallengeDataService.class);

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

                        ChooseChallengeModel chooseChallengeModel = new ChooseChallengeModel();
                        chooseChallengeModel.challengeId = _challengeModel.id;
                        chooseChallengeModel.email = _username;

                        _chalService.chooseChallenge(chooseChallengeModel, new Callback<Response>() {
                            @Override
                            public void success(Response response, Response response2) {
                                state++;
                                sharedPreferences = getApplicationContext().getSharedPreferences("ChallengePreferences2", Context.MODE_PRIVATE);
                                editor = sharedPreferences.edit();
                                editor.putInt(stateprefsTitle, state);
                                editor.commit();

                                updateButton();
                                sharedPreferences = getApplicationContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                handleRetrofitError(error);
                            }
                        });


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

            CompleteChallengeModel completeChallengeModel = new CompleteChallengeModel();
            completeChallengeModel.challengeId = _challengeModel.id;
            completeChallengeModel.email = _username;
            completeChallengeModel.passed = true;
            _chalService.completeChallenge(completeChallengeModel, new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    state++;
                    sharedPreferences = getApplicationContext().getSharedPreferences("ChallengePreferences2", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putInt(stateprefsTitle, state);
                    editor.commit();
                    updateButton();
                }

                @Override
                public void failure(RetrofitError error) {
                    handleRetrofitError(error);
                }
            });

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
    public void onClick(View v){
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
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    public void handleRetrofitError(RetrofitError error){
        if (error.getResponse() != null) {
            String errorString = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
            //Error handling here
            Log.e("FAILURE", errorString.toString());
        }
    }
}