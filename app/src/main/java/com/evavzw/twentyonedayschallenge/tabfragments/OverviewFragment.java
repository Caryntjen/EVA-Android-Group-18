package com.evavzw.twentyonedayschallenge.tabfragments;

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
    The OverviewFragment class will display statistics, achievements and level.
    There's also the possibility to share this through facebook.
    Achievements are clickable to show the current progress.
    Level is also clickable to show the current level, which goes up every 75 point until the maximum of 7 level (0-6).
*/
public class OverviewFragment extends Fragment implements ITabFragment {

    private FragmentActivity activity;

    //UI References
    private static TextView tvPoints;
    private static TextView tvCompletedChallenges;
    private static TextView tvCurrentDay;
    private static Button btnShare;

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
    //Achievement Badges References
    private static ImageView ivBadgeGerm;
    private static final int MAX_BADGE_GERM = 1;
    private static ImageView ivBadgeFlower;
    private static final int MAX_BADGE_FLOWER = 5;
    private static ImageView ivBadgeTree;
    private static final int MAX_BADGE_TREE = 10;
    private static ImageView ivBadgeEarth;
    private static final int MAX_BADGE_EARTH = 20;
    private static ImageView ivBadgeHearth;
    private static final int MAX_BADGE_HEARTH = 7;
    private static ImageView ivBadgeNut;
    private static final int MAX_BADGE_NUT = 3;
    private static ImageView ivBadgeStar;
    private static final int MAX_BADGE_STAR = 10;
    private static ImageView ivBadgePrize;
    private static final int MAX_BADGE_PRIZE = 250;
    private static ImageView ivBadgeLittleCup;
    private static final int MAX_BADGE_LITTLECUP = 500;
    private static ImageView ivBadgeBigCup;
    private static final int MAX_BADGE_BIGCUP = 21;

    //Achievement Information References
    private static TextView tvAchievementTitle;
    private static TextView tvAchievementProgress;
    private static ProgressBar pbAchievements;

    //Badges
    private Badge germ, flower, tree, hearth, earth, bigcup, littlecup, prize, nut, star;

    //Level
    private int LEVEL_IDs[];
    private static ImageView ivLevel;
    private final int MAX_LEVEL = MAX_BADGE_HEARTH;
    private final int LEVELUP_POINTS = 75;
    int currentLevel;

    //Facebook References
    private OnPublishListener onPublishListener;
    private SimpleFacebook sfacebook;
    //EVA URL References
    private static final String EVA_URL = "http://www.evavzw.be";
    private static final String IMG_URL = "http://users.telenet.be/Caryntjen/fb_shareheart.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = super.getActivity();
        View view = (View) inflater.inflate(R.layout.fragment_overview, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Points Information
        tvPoints = (TextView) activity.findViewById(R.id.tvPoints);

        //Completed Challenges Information
        tvCompletedChallenges = (TextView) activity.findViewById(R.id.tvCompletedChallenges);

        //Current Day Information
        tvCurrentDay = (TextView) activity.findViewById(R.id.tvCurrentDay);
        tvCurrentDay.setText(User.DAY.toString());

        //Achievements Progressbar
        pbAchievements = (ProgressBar) activity.findViewById(R.id.pbAchievements);

        //Achievement Title Information
        tvAchievementTitle = (TextView) activity.findViewById(R.id.tvAchievementTitle);

        //Achievement Progress Information
        tvAchievementProgress = (TextView) activity.findViewById(R.id.tvAchievementProgress);

        //Creating badges
        ivBadgeGerm = (ImageView) activity.findViewById(R.id.ivBadgeGerm);
        germ = new Badge(R.drawable.badge_finished_germ, R.drawable.badge_germ, MAX_BADGE_GERM, getString(R.string.achievements_germ));
        ivBadgeFlower = (ImageView) activity.findViewById(R.id.ivBadgeFlower);
        flower = new Badge(R.drawable.badge_finished_flower, R.drawable.badge_flower, MAX_BADGE_FLOWER, getString(R.string.achievements_flower));
        ivBadgeTree = (ImageView) activity.findViewById(R.id.ivBadgeTree);
        tree = new Badge(R.drawable.badge_finished_tree, R.drawable.badge_tree, MAX_BADGE_TREE, getString(R.string.achievements_tree));
        ivBadgeEarth = (ImageView) activity.findViewById(R.id.ivBadgeEarth);
        earth = new Badge(R.drawable.badge_finished_earth, R.drawable.badge_earth, MAX_BADGE_EARTH, getString(R.string.achievements_earth));
        ivBadgeHearth = (ImageView) activity.findViewById(R.id.ivBadgeHearth);
        hearth = new Badge(R.drawable.badge_finished_hearth, R.drawable.badge_hearth, MAX_BADGE_HEARTH, getString(R.string.achievements_hearth));
        ivBadgeNut = (ImageView) activity.findViewById(R.id.ivBadgeNut);
        nut = new Badge(R.drawable.badge_finished_nut, R.drawable.badge_nut, MAX_BADGE_NUT, getString(R.string.achievements_nut));
        ivBadgeStar = (ImageView) activity.findViewById(R.id.ivBadgeStar);
        star = new Badge(R.drawable.badge_finished_star, R.drawable.badge_star, MAX_BADGE_STAR, getString(R.string.achievements_star));
        ivBadgePrize = (ImageView) activity.findViewById(R.id.ivBadgePrize);
        prize = new Badge(R.drawable.badge_finished_prize, R.drawable.badge_prize, MAX_BADGE_PRIZE, getString(R.string.achievements_prize));
        ivBadgeLittleCup = (ImageView) activity.findViewById(R.id.ivBadgeLittleCup);
        littlecup = new Badge(R.drawable.badge_finished_littlecup, R.drawable.badge_littlecup, MAX_BADGE_LITTLECUP, getString(R.string.achievements_littlecup));
        ivBadgeBigCup = (ImageView) activity.findViewById(R.id.ivBadgeBigCup);
        bigcup = new Badge(R.drawable.badge_finished_bigcup, R.drawable.badge_bigcup, MAX_BADGE_BIGCUP, getString(R.string.achievements_bigcup));

        //Level Information
        ivLevel = (ImageView) activity.findViewById(R.id.ivLevel);

        //Level image resources
        LEVEL_IDs = new int[7];
        LEVEL_IDs [0] = R.drawable.level_hearth0;
        LEVEL_IDs [1] = R.drawable.level_hearth1;
        LEVEL_IDs [2] = R.drawable.level_hearth2;
        LEVEL_IDs [3] = R.drawable.level_hearth3;
        LEVEL_IDs [4] = R.drawable.level_hearth4;
        LEVEL_IDs [5] = R.drawable.level_hearth5;
        LEVEL_IDs [6] = R.drawable.level_hearth6;

        //Simple Facebook information
        onPublishListener = new OnPublishListener() {
        };
        sfacebook = SimpleFacebook.getInstance(activity);

        //Button to share your progress and EVA website
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

    /*
        When the fragment is swiped or clicked to, the information needs to be updated, which happens here.
     */
    @Override
    public void updateFragment() {
        //TODO: load user information
        //Stats
        tvPoints.setText(User.POINTS.toString());
        tvCompletedChallenges.setText(User.COMPLETEDCHALLENGES.toString());

        //Update current level
        currentLevel = Integer.parseInt(User.POINTS.toString())/LEVELUP_POINTS;
        if(currentLevel >= MAX_LEVEL)
        {
            ivLevel.setImageResource(LEVEL_IDs[MAX_LEVEL-1]);
        }
        else{
            ivLevel.setImageResource(LEVEL_IDs[currentLevel]);
        }

        ivLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Load user information
                Context context = activity.getApplicationContext();
                CharSequence currentlevel = getString(R.string.level_current) + " " + currentLevel;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, currentlevel, duration);
                toast.show();
            }
        });

        //Achievements
        germ.setCurrentProgress(Integer.parseInt(User.COMPLETEDCHALLENGES.toString()));
        flower.setCurrentProgress(Integer.parseInt(User.COMPLETEDCHALLENGES.toString()));
        tree.setCurrentProgress(Integer.parseInt(User.COMPLETEDCHALLENGES.toString()));
        earth.setCurrentProgress(Integer.parseInt(User.COMPLETEDCHALLENGES.toString()));
        hearth.setCurrentProgress(currentLevel);
        int differentChallenges = 0;
        if(Integer.parseInt(User.COMPLETEDPRODUCTCHALLENGES.toString())>0){
            differentChallenges++;
        }
        if(Integer.parseInt(User.COMPLETEDRECIPECHALLENGES.toString())>0){
            differentChallenges++;
        }
        if(Integer.parseInt(User.COMPLETEDRECIPECHALLENGES.toString())>0){
            differentChallenges++;
        }
        nut.setCurrentProgress(differentChallenges);
        star.setCurrentProgress(Integer.parseInt(User.STARS.toString()));
        prize.setCurrentProgress(Integer.parseInt(User.POINTS.toString()));
        littlecup.setCurrentProgress(Integer.parseInt(User.POINTS.toString()));
        bigcup.setCurrentProgress(Integer.parseInt(User.COMPLETEDCHALLENGES.toString()));

        //Draw Badges
        germ.draw(ivBadgeGerm, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        flower.draw(ivBadgeFlower, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        tree.draw(ivBadgeTree, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        earth.draw(ivBadgeEarth, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        hearth.draw(ivBadgeHearth, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        nut.draw(ivBadgeNut, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        star.draw(ivBadgeStar, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        prize.draw(ivBadgePrize, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        littlecup.draw(ivBadgeLittleCup, tvAchievementTitle, pbAchievements, tvAchievementProgress);
        bigcup.draw(ivBadgeBigCup, tvAchievementTitle, pbAchievements, tvAchievementProgress);

        //Reset Achievement Information
        pbAchievements.setVisibility(View.VISIBLE);
        pbAchievements.setMax(0);
        tvAchievementTitle.setText(getString(R.string.achievements_defaulttext));


    }
}
