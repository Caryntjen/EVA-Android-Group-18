package com.evavzw.twentyonedayschallenge.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;
import com.evavzw.twentyonedayschallenge.models.AccountModel;
import com.evavzw.twentyonedayschallenge.registration.RegisterActivity;
import com.evavzw.twentyonedayschallenge.services.UserDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;

import static android.content.Intent.getIntent;

public class AccountAcitivy extends Fragment {

    private FragmentActivity activity;

    private static TextView tvEmail;
    private static TextView tvStudent;
    private static TextView tvSex;
    private static TextView tvBirthday;
    private static TextView tvDiet;
    private static TextView tvChildren;
    private static TextView tvLanguage;
    private static TextView tvNewsletter;
    private static Button btnInvite;

    //Rest adapter
    private RestAdapter retrofit;
    private UserDataService service;
    private String url = "http://10.0.2.2:54967";

    private String accesToken;
    private String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Rest adapter
        Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        RestAdapter retrofit = new RestAdapter.Builder().setConverter(new GsonConverter(gSon)).setEndpoint(url).build();
        service = retrofit.create(UserDataService.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = super.getActivity();
        View view = (View) inflater.inflate(R.layout.activity_account, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            accesToken = ((MainActivity) context).accesToken;
            username= ((MainActivity) context).username;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvEmail = (TextView) activity.findViewById(R.id.tvEmail);
        tvBirthday = (TextView) activity.findViewById(R.id.tvBirthday);
        tvSex = (TextView) activity.findViewById(R.id.tvSex);
        tvLanguage = (TextView) activity.findViewById(R.id.tvLanguage);
        tvStudent = (TextView) activity.findViewById(R.id.tvStudent);
        tvChildren = (TextView) activity.findViewById(R.id.tvChildren);
        tvNewsletter = (TextView) activity.findViewById(R.id.tvNewsletter);
        tvDiet = (TextView) activity.findViewById(R.id.tvDiet);
        btnInvite = (Button) activity.findViewById(R.id.btnInvite);
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"hogent@evavzw.be"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Come join the challenge");
                intent.putExtra(Intent.EXTRA_TEXT, "Go check this new app out!");
                startActivity(Intent.createChooser(intent, ""));
    */
            }
        });

        service.getAccountDetails(accesToken, username, new Callback<AccountModel>() {
            @Override
            public void success(AccountModel accountModel, Response response) {
                Log.d("SUCCES", "Succesfully fetched accountdata");
                UpdateInterface(accountModel);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getResponse() != null) {
                    String errorString = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                    //Error handling here
                    Log.e("FAILURE", errorString.toString());
                }
            }
        });
    }

    public String parseLanguage(String shortLanguage){
        switch(shortLanguage.toLowerCase()){
            case "en": return "english";
            case "fr": return "français";
            case "nl": return "nederlands";
            default: return "english";
        }
    }

    public String parseDifficulty(int difficulty){
        switch(difficulty){
            case 1: return "Omnivorism";
            case 2: return "Pescetarianism";
            case 3: return "ParttimeVegetarianism";
            case 4: return "Vegetarianism";
            case 5: return "Veganism";
            default: return "" + difficulty;
        }
    }

    public String parseBooleanToText(boolean sendNewsLetter){
        return sendNewsLetter?"true":"false";
    }

    public void UpdateInterface(AccountModel am){
        tvEmail.setText(am.email);
        tvBirthday.setText(am.birthDate.toString());
        tvSex.setText(am.gender);
        tvLanguage.setText(parseLanguage(am.language));
        tvStudent.setText(parseBooleanToText(am.isStudent));
        tvChildren.setText("" + am.nmbrOfChildren);
        tvNewsletter.setText(parseBooleanToText(am.sendNewsLetter));
        tvDiet.setText(parseDifficulty(am.difficulty));
    }
}
