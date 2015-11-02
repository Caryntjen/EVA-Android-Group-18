package com.evavzw.twentyonedayschallenge.challenges;

import com.evavzw.twentyonedayschallenge.R;

public enum ChallengeType {
    SOCIALMEDIA(R.drawable.socialmedia, "Social Media", "Share the love"),
    RECIPE(R.drawable.recipe, "Recipe", "Follow these easy steps"),
    PRODUCT(R.drawable.product,"Product","Use this in your day");

    private int resourceId;
    private String title;
    private String tagline;

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


}