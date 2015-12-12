package com.evavzw.twentyonedayschallenge.challenges;

import com.evavzw.twentyonedayschallenge.R;

public enum ChallengeType {
    SOCIALMEDIA(R.drawable.socialmedia, "Social Media", "Share the love!"),
    RECIPE(R.drawable.recipe, "Recipe", "Cook your own bio meal."),
    PRODUCT(R.drawable.product,"Product", "Use a bio product."),
    RESTAURANTS(R.drawable.product,"Restaurant", "Eat bio in style!"),
    LEARNING(R.drawable.product,"Learning", "How much do you know about bio?"),
    FRIEND(R.drawable.product,"Friend", "Be bio with friends!"),
    GETINVOLVED(R.drawable.product, "Get Involved", "Be bio with EVA!");

    private int resourceId;
    private String title;
    private String tagline;

    private boolean challengeAccepted;

    private ChallengeType(int resourceId, String title, String tagline) {
        this.resourceId = resourceId;
        this.title = title;
        this.tagline = tagline;
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

}
