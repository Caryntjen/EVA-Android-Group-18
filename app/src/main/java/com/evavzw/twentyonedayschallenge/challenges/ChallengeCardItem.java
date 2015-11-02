package com.evavzw.twentyonedayschallenge.challenges;

public class ChallengeCardItem {
    private ChallengeType type;
    private int stars;

    public ChallengeCardItem(ChallengeType type, int stars) {
        this.stars = stars;
        this.type = type;
    }

    public ChallengeCardItem(ChallengeCardItem ccItem) {
        this(ccItem.getType(), ccItem.getStars());
    }

    public int getImage() {
        return type.getResourceId();
    }

    public String getTitle() {
        return type.getTitle();
    }

    public String getTagline() {
        return type.getTagline();
    }

    public int getStars() {
        return stars;
    }
    public ChallengeType getType() {
        return type;
    }

}
