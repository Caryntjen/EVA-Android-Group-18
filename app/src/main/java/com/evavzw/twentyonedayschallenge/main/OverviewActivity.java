package com.evavzw.twentyonedayschallenge.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.evavzw.twentyonedayschallenge.R;

public class OverviewActivity extends Fragment {

    private FragmentActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = super.getActivity();
        View view = (View) inflater.inflate(R.layout.activity_overview, container, false);

        ProgressBar pbAchievements = (ProgressBar) view.findViewById(R.id.pbAchievements);
        pbAchievements.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
