package com.evavzw.twentyonedayschallenge.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

public class ChallengesActivity extends Fragment {

    private FragmentActivity activity;
    private BaseInflaterAdapter<ChallengeCardItem> adapter;

    private String currentday = User.DAY.toString();
    private final int MAXDAYS = 21;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = super.getActivity();
        View view = (View) inflater.inflate(R.layout.activity_challenges, container, false);

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


        final ListView challengeItems = (ListView) activity.findViewById(R.id.lvChallenges);

        challengeItems.addHeaderView(new View(activity));
        challengeItems.addFooterView(new View(activity));

        adapter = new BaseInflaterAdapter<ChallengeCardItem>(new ChallengeCard());

        ChallengeCardItem product = new ChallengeCardItem(ChallengeType.PRODUCT, 2);
        adapter.addItem(product, false);
        ChallengeCardItem recipe = new ChallengeCardItem(ChallengeType.RECIPE, 0);
        adapter.addItem(recipe, false);
        ChallengeCardItem socialmedia = new ChallengeCardItem(ChallengeType.SOCIALMEDIA, 1);
        adapter.addItem(socialmedia, false);
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
}
