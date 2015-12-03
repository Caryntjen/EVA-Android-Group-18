package com.evavzw.twentyonedayschallenge.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import retrofit.RestAdapter;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Rest adapter
        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint(url).build();

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
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public String parseLanguage(String shortLanguage){

        switch(shortLanguage.toLowerCase()){
            case "en": return "english";

            case "fr": return "français";

            case "nl": return "nederlands";

            default: return "english";

        }
    }

    public String parseDifficulty(int Difficulty){
        String result = ""+Difficulty;
        return result;
    }

    public void UpdateInterface(AccountModel am){
        tvEmail = (TextView) activity.findViewById(R.id.tvEmail);
        tvEmail.setText(am.Email);

        tvBirthday = (TextView) activity.findViewById(R.id.tvBirthday);
        tvBirthday.setText(am.BirthDate.toString());

        tvSex = (TextView) activity.findViewById(R.id.tvSex);
        tvSex.setText(am.Gender);

        tvLanguage = (TextView) activity.findViewById(R.id.tvLanguage);
        tvLanguage.setText(parseLanguage(am.Language));

        tvStudent = (TextView) activity.findViewById(R.id.tvStudent);
        tvStudent.setText(am.IsStudent.toString());

        tvChildren = (TextView) activity.findViewById(R.id.tvChildren);
        tvChildren.setText(am.NmbrOfChildren);

        tvNewsletter = (TextView) activity.findViewById(R.id.tvNewsletter);
        tvNewsletter.setText(am.sendNewsLetter.toString());

        tvDiet = (TextView) activity.findViewById(R.id.tvDiet);
        tvDiet.setText(parseDifficulty(am.Difficulty));

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
    }
}
