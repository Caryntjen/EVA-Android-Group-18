package com.evavzw.twentyonedayschallenge.tabfragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;
import com.evavzw.twentyonedayschallenge.main.MainActivity;
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

/*
    The AccountFragment class will display the information of the account of the user.
    There's also the possibility to invite a friend through e-mail.
*/
public class AccountFragment extends Fragment implements ITabFragment {

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
    private MainActivity _mainActivity;

    private static final String EMAILTYPE = "message/rfc822";

    //Rest adapter
    private RestAdapter retrofit;
    private UserDataService service;
    //genymotion virtual devices
    //private String url = "http://10.0.3.2:54967";
    //androidstudio emulators
    private String url = "http://10.0.2.2:54967";

    private String _accesToken;
    private String _username;

    private AccountModel _am;

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
        View view = (View) inflater.inflate(R.layout.fragment_activity_account, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            _accesToken = ((MainActivity) context).accesToken;
            _username= ((MainActivity) context).username;
            _mainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Email Information
        tvEmail = (TextView) activity.findViewById(R.id.tvEmail);

        //Birthday Information
        tvBirthday = (TextView) activity.findViewById(R.id.tvBirthday);

        //Gender Information
        tvSex = (TextView) activity.findViewById(R.id.tvSex);

        //Language Information
        tvLanguage = (TextView) activity.findViewById(R.id.tvLanguage);

        //Student Information
        tvStudent = (TextView) activity.findViewById(R.id.tvStudent);

        //Number of Children Information
        tvChildren = (TextView) activity.findViewById(R.id.tvChildren);

        //Subscribed to newsletter Information
        tvNewsletter = (TextView) activity.findViewById(R.id.tvNewsletter);

        //Kind of Diet Information
        tvDiet = (TextView) activity.findViewById(R.id.tvDiet);

        //Button to invite others through email if the intent can not be started a short Toast is made that no email client is found.
        btnInvite = (Button) activity.findViewById(R.id.btnInvite);
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType(EMAILTYPE);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{tvEmail.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.overview_invite_email_title));
                emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.overview_invite_email_body));
                try {
                    startActivity(Intent.createChooser(emailIntent, getString(R.string.overview_invite_email_chooser)));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(activity, getString(R.string.overview_invite_email_notfound), Toast.LENGTH_SHORT).show();
                }
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

       /* GetAccountDetailsTask task = new GetAccountDetailsTask(service, accesToken, username);
        am = task.fetchAccountDetails();
        updateFragment();*/

        service.getAccountDetails(_accesToken, _username, new Callback<AccountModel>() {
            @Override
            public void success(AccountModel accountModel, Response response) {
                Log.d("SUCCES", "Succesfully fetched accountdata");
                _am = accountModel;
                _mainActivity.am = accountModel;
                updateFragment();
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
            //English = getString(R.string.registration_english)
            case "en": return "english";
            case "fr": return "français";
            case "nl": return "nederlands";
            default: return "english";
        }
    }

    public String parseDifficulty(int difficulty){
        switch(difficulty){
            //Example: Omnivorme getString(R.string.registration_omnivorism)
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

    @Override
    public void onResume() {
        super.onResume();
        updateFragment();
    }

    /*
        When the fragment is swiped or clicked to, the information needs to be updated, which happens here.
     */
    @Override
    public void updateFragment() {
        if(_am != null) {
            tvEmail.setText(_am.email);
            tvBirthday.setText(_am.birthDate.toString());
            tvSex.setText(_am.gender);
            tvLanguage.setText(parseLanguage(_am.language));
            tvStudent.setText(parseBooleanToText(_am.isStudent));
            tvChildren.setText("" + _am.nmbrOfChildren);
            tvNewsletter.setText(parseBooleanToText(_am.sendNewsLetter));
            tvDiet.setText(parseDifficulty(_am.difficulty));
        }
    }

    /**
     * Represents an asynchronous task to fetch the accountdetails of
     * the user.
     */
  /*  public class GetAccountDetailsTask extends AsyncTask<Void, Void, Boolean> {

        private UserDataService _service;
        private String _username;
        private String _accesToken;
        private Intent _i;
        private AccountModel _am;

        public AccountModel fetchAccountDetails(){
            doInBackground();
            return _am;
        }

        GetAccountDetailsTask(UserDataService service, String accesToken, String username) {
            _service = service;
            _accesToken = accesToken;
            _username = username;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            _service.getAccountDetails(_accesToken, _username, new Callback<AccountModel>() {
                @Override
                public void success(AccountModel accountModel, Response response) {
                    Log.d("SUCCES", "Succesfully fetched accountdata");
                    _am = accountModel;
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
            return true;
        }
    }*/
}
