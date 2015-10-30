package com.evavzw.twentyonedayschallenge.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.challenges.BaseInflaterAdapter;
import com.evavzw.twentyonedayschallenge.challenges.ChallengeCard;
import com.evavzw.twentyonedayschallenge.challenges.ChallengeCardItem;

public class ChallengesActivity extends Fragment{

    private FragmentActivity activity;

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

        ListView list = (ListView)activity.findViewById(R.id.lvChallenges);

        list.addHeaderView(new View(activity));
        list.addFooterView(new View(activity));

        BaseInflaterAdapter<ChallengeCardItem> adapter = new BaseInflaterAdapter<ChallengeCardItem>(new ChallengeCard());
        for (int i = 0; i < 3; i++)
        {
            ChallengeCardItem data = new ChallengeCardItem("Product Challenge", "Use this product");
            adapter.addItem(data, false);
        }
        list.setAdapter(adapter);
    }
}
