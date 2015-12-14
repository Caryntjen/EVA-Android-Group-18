package com.evavzw.twentyonedayschallenge.challenges;

public class ChallengeCardItem {
    private Challenge challenge;
    private int stars;

    public ChallengeCardItem(Challenge challenge, int stars) {
        this.challenge = challenge;
        this.stars = stars;
    }

    public ChallengeCardItem(ChallengeCardItem ccItem) {
        this(ccItem.getChallenge(), ccItem.getStars());
    }

    public int getImage() {
        return challenge.getResourceId();
    }

    public String getTitle() {
        return challenge.getTitle();
    }

    public String getTagline() {
        return challenge.getTagline();
    }


    public int getStars() {
        return stars;
    }
    public Challenge getChallenge() {
        return challenge;
    }
}
