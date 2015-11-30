package com.evavzw.twentyonedayschallenge.services;

import com.evavzw.twentyonedayschallenge.models.Registration;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Nico on 23/11/2015.
 */
public interface UserDataService {
    @POST("api/account/register")
    void register(@Body Registration registration, Callback cb);
}
