package com.evavzw.twentyonedayschallenge.overview;

import android.content.Context;
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
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Feed;
import com.sromku.simple.fb.listeners.OnPublishListener;

/*
    The OverviewActivity class will display statistics, achievements and level.
    There's also the possibility to share this through facebook.
    Achievements are clickable to show the current progress.
    Level is also clickable to show the current level.
*/
public class OverviewActivity extends Fragment {

    private FragmentActivity activity;

    //UI References
    private static TextView tvPoints;
    private static TextView tvCompletedChallenges;
    private static TextView tvCurrentDay;
    private static ImageView ivLevel;
    private static Button btnShare;

    //Achievements References
    /*
    First Line
        Badge 0 = GERM: Completed your first challenge.
        Badge 1 = FLOWER: Completed 5 challenges.
        Badge 2 = TREE: Completed your 10 challenges.
        Badge 3 = EARTH: Your ecologic footprint has been lowered by completing 20 challenges.
        Badge 4 = HEARTH: Filled your hearth

    Second Line
        Badge 5 = NUT: Completed 3 different challenges.
        Badge 6 = STAR: Collected 10 starred challenges.
        Badge 7 = PRIZE: You've collected 250 points.
        Badge 8 = LITTLECUP: You've collected 500 points.
        Badge 9 = BIGCUP: You've completed the 21 day challenge.
     */
    private static ImageView ivBadgeGerm;
    private String sBadgeGerm;
    private static final int MAX_BADGE_GERM = 1;
    private static ImageView ivBadgeFlower;
    private String sBadgeFlower;
    private static final int MAX_BADGE_FLOWER = 5;
    private static ImageView ivBadgeTree;
    private String sBadgeTree;
    private static final int MAX_BADGE_TREE = 10;
    private static ImageView ivBadgeEarth;
    private String sBadgeEarth;
    private static final int MAX_BADGE_EARTH = 20;
    private static ImageView ivBadgeHearth;
    private String sBadgeHearth;
    private static final int MAX_BADGE_HEARTH = 7;
    private static ImageView ivBadgeNut;
    private String sBadgeNut;
    private static final int MAX_BADGE_NUT = 3;
    private static ImageView ivBadgeStar;
    private String sBadgeStar;
    private static final int MAX_BADGE_STAR = 10;
    private static ImageView ivBadgePrize;
    private String sBadgePrize;
    private static final int MAX_BADGE_PRIZE = 250;
    private static ImageView ivBadgeLittleCup;
    private String sBadgeLittleCup;
    private static final int MAX_BADGE_LITTLECUP = 500;
    private static ImageView ivBadgeBigCup;
    private String sBadgeBigCup;
    private static final int MAX_BADGE_BIGCUP = 21;
    private static TextView tvAchievementTitle;
    private static TextView tvAchievementProgress;
    private static ProgressBar pbAchievements;

    //Facebook References
    private OnPublishListener onPublishListener;
    private SimpleFacebook sfacebook;

    private static final String EVA_URL = "http://www.evavzw.be";
    private static final String IMG_URL = "http://users.telenet.be/Caryntjen/fb_shareheart.png";

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
        tvAchievementTitle.setText(getText(R.string.achievements_defaulttext));

        //Achievement Progress Information
        tvAchievementProgress = (TextView) activity.findViewById(R.id.tvAchievementProgress);

        //TODO: Change pictures of Achievement when not completed to plack & white.
        //Germ Badge Information
        sBadgeGerm = getString(R.string.achievements_germ);
        ivBadgeGerm = (ImageView) activity.findViewById(R.id.ivBadgeGerm);
        ivBadgeGerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeGerm);
                int max = MAX_BADGE_GERM;
                int current = Integer.parseInt(User.BADGE_GERM.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });
        //Flower Badge Information
        sBadgeFlower = getString(R.string.achievements_flower);
        ivBadgeFlower = (ImageView) activity.findViewById(R.id.ivBadgeFlower);
        ivBadgeFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeFlower);
                int max = MAX_BADGE_FLOWER;
                int current = Integer.parseInt(User.BADGE_FLOWER.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //Tree Badge Information
        sBadgeTree = getString(R.string.achievements_tree);
        ivBadgeTree = (ImageView) activity.findViewById(R.id.ivBadgeTree);
        ivBadgeTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeTree);
                int max = MAX_BADGE_TREE;
                int current = Integer.parseInt(User.BADGE_TREE.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //Earth Badge Information
        sBadgeEarth = getString(R.string.achievements_earth);
        ivBadgeEarth = (ImageView) activity.findViewById(R.id.ivBadgeEarth);
        ivBadgeEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeEarth);
                int max = MAX_BADGE_EARTH;
                int current = Integer.parseInt(User.BADGE_EARTH.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //Hearth Badge Information
        sBadgeHearth = getString(R.string.achievements_hearth);
        ivBadgeHearth = (ImageView) activity.findViewById(R.id.ivBadgeHearth);
        ivBadgeHearth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeHearth);
                int max = MAX_BADGE_HEARTH;
                int current = Integer.parseInt(User.BADGE_HEARTH.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //Nut Badge5 Information
        sBadgeNut = getString(R.string.achievements_nut);
        ivBadgeNut = (ImageView) activity.findViewById(R.id.ivBadgeNut);
        ivBadgeNut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeNut);
                int max = MAX_BADGE_NUT;
                int current = Integer.parseInt(User.BADGE_NUT.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //Star Badge Information
        sBadgeStar = getString(R.string.achievements_star);
        ivBadgeStar = (ImageView) activity.findViewById(R.id.ivBadgeStar);
        ivBadgeStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeStar);
                int max = MAX_BADGE_STAR;
                int current = Integer.parseInt(User.BADGE_STAR.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //Prize Badge Information
        sBadgePrize = getString(R.string.achievements_prize);
        ivBadgePrize = (ImageView) activity.findViewById(R.id.ivBadgePrize);
        ivBadgePrize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgePrize);
                int max = MAX_BADGE_PRIZE;
                int current = Integer.parseInt(User.BADGE_PRIZE.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //LittleCup Badge Information
        sBadgeLittleCup = getString(R.string.achievements_littlecup);
        ivBadgeLittleCup = (ImageView) activity.findViewById(R.id.ivBadgeLittleCup);
        ivBadgeLittleCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeLittleCup);
                int max = MAX_BADGE_LITTLECUP;
                int current = Integer.parseInt(User.BADGE_LITTLECUP.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //BigCup Badge Information
        sBadgeBigCup = getString(R.string.achievements_bigcup);
        ivBadgeBigCup = (ImageView) activity.findViewById(R.id.ivBadgeBigCup);
        ivBadgeBigCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                pbAchievements.setVisibility(View.VISIBLE);
                tvAchievementTitle.setText(sBadgeBigCup);
                int max = MAX_BADGE_BIGCUP;
                int current = Integer.parseInt(User.BADGE_BIGCUP.toString());
                pbAchievements.setMax(max);
                pbAchievements.setProgress(current);
                tvAchievementProgress.setText(current + "/" + max);
            }
        });

        //Level Information
        //TODO: Change pictures for each level
        ivLevel = (ImageView) activity.findViewById(R.id.ivLevel);
        ivLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                Context context = activity.getApplicationContext();
                CharSequence currentlevel = getString(R.string.level_current) + " " + User.LEVEL;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, currentlevel, duration);
                toast.show();
            }
        });

        //Simple Facebook information
        onPublishListener = new OnPublishListener() {
        };
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
