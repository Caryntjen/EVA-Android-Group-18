package com.evavzw.twentyonedayschallenge.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.evavzw.twentyonedayschallenge.R;
import com.evavzw.twentyonedayschallenge.dummy.User;
import com.facebook.share.model.ShareLinkContent;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Feed;
import com.sromku.simple.fb.listeners.OnPublishListener;

public class OverviewActivity extends Fragment {

    private FragmentActivity activity;

    //UI References
    private static TextView tvPoints;
    private static TextView tvCompletedChallenges;
    private static TextView tvCurrentDay;
    private static ImageView ivBadge0;
    private static ImageView ivBadge1;
    private static ImageView ivBadge2;
    private static ImageView ivBadge3;
    private static ImageView ivBadge4;
    private static ImageView ivBadge5;
    private static ImageView ivBadge6;
    private static ImageView ivBadge7;
    private static ImageView ivBadge8;
    private static ImageView ivBadge9;
    private static TextView tvAchievementTitle;
    private static TextView tvAchievementProgress;
    private static ProgressBar pbAchievements;
    private static ImageView ivLevel;
    private static Button btnShare;

    //Facebook
    private OnPublishListener onPublishListener;
    private SimpleFacebook sfacebook;

    private static final String EVA_URL="http://www.evavzw.be";
    private static final String IMG_URL="http://users.telenet.be/Caryntjen/fb_shareheart.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = super.getActivity();
        View view = (View) inflater.inflate(R.layout.activity_overview, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sfacebook = SimpleFacebook.getInstance(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //TODO: Load information into TextViews

        //Points Information
        tvPoints = (TextView) activity.findViewById(R.id.tvPoints);
        tvPoints.setText(User.POINTS.toString());

        //Completed Challenges Information
        tvCompletedChallenges = (TextView) activity.findViewById(R.id.tvCompletedChallenges);
        tvCompletedChallenges.setText(User.COMPLETEDCHALLENGES.toString());

        //Current Day Information
        tvCurrentDay = (TextView) activity.findViewById(R.id.tvCurrentDay);
        tvCurrentDay.setText(User.DAY.toString());

        //Achievements Progressbar
        pbAchievements = (ProgressBar) activity.findViewById(R.id.pbAchievements);
        pbAchievements.setVisibility(View.INVISIBLE);

        //Achievement Title Information
        tvAchievementTitle = (TextView) activity.findViewById(R.id.tvAchievementTitle);

        //Achievement Progress Information
        tvAchievementProgress = (TextView) activity.findViewById(R.id.tvAchievementProgress);

        //Badge 0 Information
        ivBadge0 = (ImageView) activity.findViewById(R.id.ivBadge0);
        ivBadge0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Import click event
                pbAchievements.setVisibility(View.VISIBLE);
            }
        });

        //Badge 1 Information
        ivBadge1 = (ImageView) activity.findViewById(R.id.ivBadge1);

        //Badge 2 Information
        ivBadge2 = (ImageView) activity.findViewById(R.id.ivBadge2);

        //Badge 3 Information
        ivBadge3 = (ImageView) activity.findViewById(R.id.ivBadge3);

        //Badge 4 Information
        ivBadge4 = (ImageView) activity.findViewById(R.id.ivBadge4);

        //Badge 5 Information
        ivBadge5 = (ImageView) activity.findViewById(R.id.ivBadge5);

        //Badge 6 Information
        ivBadge6 = (ImageView) activity.findViewById(R.id.ivBadge6);

        //Badge 7 Information
        ivBadge7 = (ImageView) activity.findViewById(R.id.ivBadge7);

        //Badge 8 Information
        ivBadge8 = (ImageView) activity.findViewById(R.id.ivBadge8);

        //Badge 9 Information
        ivBadge9 = (ImageView) activity.findViewById(R.id.ivBadge9);



        //Level Information
        ivLevel = (ImageView) activity.findViewById(R.id.ivLevel);

        //Simple Facebook information
        onPublishListener = new OnPublishListener() {};
        sfacebook = SimpleFacebook.getInstance(activity);

        //Button to share your progress and evawewbsite
        btnShare = (Button) activity.findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Feed feed = new Feed.Builder()
                        .setMessage(getString(R.string.facebook_message))
                        .setName(getString(R.string.facebook_name))
                        .setCaption(getString(R.string.facebook_caption))
                        .setDescription(getString(R.string.facebook_description))
                        .setPicture(IMG_URL)
                        .setLink(EVA_URL)
                        .build();

                sfacebook.publish(feed, true, onPublishListener);
            }
        });

    }
}
