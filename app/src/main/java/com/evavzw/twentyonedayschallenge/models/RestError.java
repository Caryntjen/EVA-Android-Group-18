package com.evavzw.twentyonedayschallenge.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nico on 1/12/2015.
 */
public class RestError {
    @SerializedName("error")
    public String error;
    @SerializedName("error_description")
    public String errorDetail;
}
