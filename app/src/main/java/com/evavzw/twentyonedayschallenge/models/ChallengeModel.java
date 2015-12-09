package com.evavzw.twentyonedayschallenge.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nico on 7/12/2015.
 */
public class ChallengeModel implements Parcelable {
    public String title;
    public String description;
    public String image;
    public String variant;
    public int difficulty;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(variant);
        dest.writeInt(difficulty);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<ChallengeModel> CREATOR = new Parcelable.Creator<ChallengeModel>() {
        public ChallengeModel createFromParcel(Parcel in) {
            return new ChallengeModel(in);
        }

        public ChallengeModel[] newArray(int size) {
            return new ChallengeModel[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private ChallengeModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readString();
        variant = in.readString();
        difficulty = in.readInt();
    }
}
