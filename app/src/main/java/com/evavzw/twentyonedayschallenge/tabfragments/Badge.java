package com.evavzw.twentyonedayschallenge.tabfragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
    The Badge class will keep the information of the badge such as: resourceIDs, maximum, progress and description.
    There's also the possibility to draw the Badge and gives a colour resourceID when it's completed.
*/
public class Badge {

    private int resourceId, resourceIdFinished, max, currentProgress = 0;
    private String description;

    public Badge(int resourceIdFinished, int resourceId, int max, String description) {
        this.resourceId = resourceId;
        this.resourceIdFinished = resourceIdFinished;
        this.max = max;
        this.description = description;
    }

    private int getResourceId() {
        return this.resourceId;
    }

    private int getResourceIdFinished() {
        return this.resourceIdFinished;
    }

    private int getMax() {
        return this.max;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentProgress() {
        return this.currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    private boolean isTheAchievementComplete() {
        return currentProgress >= max;
    }

    /*
        Draws the Badge and gives a colour resourceID when it's completed. Updates the progress and text and adds click functionality to the ImageView.
    */
    public void draw(ImageView imBadge, final TextView tvDescription, final ProgressBar progressBar, final TextView tvProgressBar) {

        if (isTheAchievementComplete()) {
            imBadge.setImageResource(resourceIdFinished);
        }

        imBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDescription.setText(getDescription());
                progressBar.setMax(getMax());
                progressBar.setProgress(getCurrentProgress());
                tvProgressBar.setText(getCurrentProgress() + "/" + getMax());
                tvProgressBar.setVisibility(View.VISIBLE);

                if (isTheAchievementComplete()) {
                    tvProgressBar.setText("");
                    tvProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

}
