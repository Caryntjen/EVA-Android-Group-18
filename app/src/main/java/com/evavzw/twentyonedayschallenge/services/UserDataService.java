package com.evavzw.twentyonedayschallenge.services;

import com.evavzw.twentyonedayschallenge.models.LoginModel;
import com.evavzw.twentyonedayschallenge.models.LoginToken;
import com.evavzw.twentyonedayschallenge.models.Registration;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Nico on 23/11/2015.
 */
public interface UserDataService {
    @POST("/api/account/register")
    void register(@Body Registration registration, Callback<Response> cb);

    @POST("/token")
    LoginToken getToken(@Body LoginModel lm, Callback<LoginToken> cb);
}
