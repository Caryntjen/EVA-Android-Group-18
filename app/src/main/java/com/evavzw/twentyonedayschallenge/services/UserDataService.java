package com.evavzw.twentyonedayschallenge.services;

import com.evavzw.twentyonedayschallenge.models.LoginModel;
import com.evavzw.twentyonedayschallenge.models.LoginToken;
import com.evavzw.twentyonedayschallenge.models.Registration;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Nico on 23/11/2015.
 */
public interface UserDataService {
    @POST("/api/account/register")
    void register(@Body Registration registration, Callback<Response> cb);

    @POST("/token")
    @FormUrlEncoded
    void getToken(@Field("grant_type") String grant, @Field("username")String userName, @Field("password") String password, Callback<LoginToken> cb);
}
