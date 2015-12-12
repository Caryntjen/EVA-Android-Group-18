package com.evavzw.twentyonedayschallenge.challenges;

import com.evavzw.twentyonedayschallenge.R;

/**
 * Created by Matthias on 12/12/2015.
 */
public class Challenge {

    private int resourceId;
    private String title;
    private String tagline;

    private boolean challengeAccepted;

    private ChallengeType challengeType;

    public Challenge(int resourceId, String title, String tagline, ChallengeType challengeType) {
        this.resourceId = resourceId;
        this.title = title;
        this.tagline = tagline;
        this.challengeType = challengeType;
    }

    public int getResourceId()
    {
        return resourceId;
    }

    public String getTitle()
    {
        return title;
    }

    public String getTagline()
    {
        return tagline;
    }


    public boolean isChallengeAccepted() {
        return this.challengeAccepted;
    }

    public void setChallengeAccepted(boolean challengeAccepted) {
        this.challengeAccepted = challengeAccepted;
    }

    public ChallengeType getChallengeType() {
        return this.challengeType;
    }

    public void setChallengeType(ChallengeType challengeType) {
        this.challengeType = challengeType;
    }
}
