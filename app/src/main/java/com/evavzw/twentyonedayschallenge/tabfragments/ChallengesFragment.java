package com.evavzw.twentyonedayschallenge.tabfragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.challenges.BaseInflaterAdapter;
import com.evavzw.twentyonedayschallenge.challenges.ChallengeCard;
import com.evavzw.twentyonedayschallenge.challenges.ChallengeCardItem;
import com.evavzw.twentyonedayschallenge.challenges.ChallengeType;
import com.evavzw.twentyonedayschallenge.challenges.ProductChallengeActivity;
import com.evavzw.twentyonedayschallenge.challenges.RecipeChallengeActivity;
import com.evavzw.twentyonedayschallenge.challenges.SocialMediaChallengeActivity;
import com.evavzw.twentyonedayschallenge.dummy.User;
import com.evavzw.twentyonedayschallenge.main.MainActivity;
import com.evavzw.twentyonedayschallenge.models.ChallengeModel;
import com.evavzw.twentyonedayschallenge.models.LoginToken;
import com.evavzw.twentyonedayschallenge.models.Registration;
import com.evavzw.twentyonedayschallenge.services.ChallengeDataService;
import com.evavzw.twentyonedayschallenge.services.UserDataService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class ChallengesFragment extends Fragment implements ITabFragment {

    private FragmentActivity activity;
    private BaseInflaterAdapter<ChallengeCardItem> adapter;

    private String currentday = User.DAY.toString();
    private final int MAXDAYS = 21;
    private String _accesToken;
    private String _username;
    private String _url = "http://10.0.2.2:54967";
    private RestAdapter _retrofit;
    private ChallengeDataService _service;
    private List<ChallengeModel> _challengeModels = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = super.getActivity();
        View view = (View) inflater.inflate(R.layout.fragment_challenges, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProgressBar pbLoading = (ProgressBar) activity.findViewById(R.id.pbLoading);

        ProgressBar pbDays = (ProgressBar) activity.findViewById(R.id.pbDays);
        pbDays.setMax(MAXDAYS);
        pbDays.setProgress(Integer.parseInt(currentday));

        TextView tvDays = (TextView) activity.findViewById(R.id.tvDays);
        tvDays.setText(currentday + "/21 days");

        _retrofit = new RestAdapter.Builder().setEndpoint(_url).build();
        _service = _retrofit.create(ChallengeDataService.class);
        _service.getChallenges(_accesToken, 4, new Callback<List<ChallengeModel>>() {

            @Override
            public void success(List<ChallengeModel> challengeModels, Response response) {
                for (Iterator<ChallengeModel> i= challengeModels.iterator(); i.hasNext();){
                    ChallengeModel item = i.next();
                    _challengeModels.add(item);
                }
                updateInterface();
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
        final ListView challengeItems = (ListView) activity.findViewById(R.id.lvChallenges);

        challengeItems.addHeaderView(new View(activity));
        challengeItems.addFooterView(new View(activity));

        adapter = new BaseInflaterAdapter<ChallengeCardItem>(new ChallengeCard());

        /*if(_challengeModels != null) {
            for (Iterator<ChallengeModel> i = _challengeModels.iterator(); i.hasNext(); ) {
                ChallengeModel item = i.next();
                ChallengeType type = ChallengeType.PRODUCT;
                ;
                switch (item.variant.toLowerCase()) {
                    case "recipe":
                        type = ChallengeType.RECIPE;
                    case "product":
                        type = ChallengeType.PRODUCT;
                    case "social media":
                        type = ChallengeType.SOCIALMEDIA;
                    case "restaurants":
                        type = ChallengeType.RESTAURANTS;
                    case "learning":
                        type = ChallengeType.LEARNING;
                    case "friend":
                        type = ChallengeType.FRIEND;
                    case "get involved":
                        type = ChallengeType.GETINVOLVED;
                    default:
                        type = ChallengeType.PRODUCT;
                }
                ChallengeCardItem card = new ChallengeCardItem(type, item.difficulty);
                adapter.addItem(card, false);
            }
         }*/







       /* ChallengeCardItem product = new ChallengeCardItem(ChallengeType.PRODUCT, 2);
        adapter.addItem(product, false);
        ChallengeCardItem recipe = new ChallengeCardItem(ChallengeType.RECIPE, 0);
        adapter.addItem(recipe, false);
        ChallengeCardItem socialmedia = new ChallengeCardItem(ChallengeType.SOCIALMEDIA, 1);
        adapter.addItem(socialmedia, false);*/
        challengeItems.setAdapter(adapter);


        challengeItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                ChallengeCardItem ccItem = new ChallengeCardItem(adapter.getTItem(position - 1));
                Intent typeChallenge = null;
                switch (ccItem.getType()) {
                    case PRODUCT:
                        typeChallenge = new Intent(activity, ProductChallengeActivity.class);
                        break;
                    case SOCIALMEDIA:
                        typeChallenge = new Intent(activity, SocialMediaChallengeActivity.class);
                        break;
                    case RECIPE:
                        typeChallenge = new Intent(activity, RecipeChallengeActivity.class);
                        break;
                }
                if (!typeChallenge.equals(null)) {
                    startActivity(typeChallenge);
                }

            }
        });


        pbLoading.setVisibility(View.GONE);
    }

    public void updateInterface(){
        if(_challengeModels != null) {
            for (Iterator<ChallengeModel> i = _challengeModels.iterator(); i.hasNext(); ) {
                ChallengeModel item = i.next();
                ChallengeType type = ChallengeType.PRODUCT;
                ;
                switch (item.variant.toLowerCase()) {
                    case "recipe":
                        type = ChallengeType.RECIPE;
                        break;
                    case "product":
                        type = ChallengeType.PRODUCT;
                        break;
                    case "social media":
                        type = ChallengeType.SOCIALMEDIA;
                        break;
                    case "restaurants":
                        type = ChallengeType.RESTAURANTS;
                        break;
                    case "learning":
                        type = ChallengeType.LEARNING;
                        break;
                    case "friend":
                        type = ChallengeType.FRIEND;
                        break;
                    case "get involved":
                        type = ChallengeType.GETINVOLVED;
                        break;
                    default:
                        type = ChallengeType.PRODUCT;
                        break;
                }
                ChallengeCardItem card = new ChallengeCardItem(type, item.difficulty);
                adapter.addItem(card, false);
            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            _accesToken = ((MainActivity) context).accesToken;
            _username= ((MainActivity) context).username;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFragment();
    }

    @Override
    public void updateFragment() {

    }
}
