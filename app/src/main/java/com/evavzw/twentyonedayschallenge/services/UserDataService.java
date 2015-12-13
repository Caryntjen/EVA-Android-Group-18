package com.evavzw.twentyonedayschallenge.services;

import com.evavzw.twentyonedayschallenge.models.AccountModel;
import com.evavzw.twentyonedayschallenge.models.ChosenChallengeModel;
import com.evavzw.twentyonedayschallenge.models.LoginModel;
import com.evavzw.twentyonedayschallenge.models.LoginToken;
import com.evavzw.twentyonedayschallenge.models.Registration;
import com.evavzw.twentyonedayschallenge.models.ScoreModel;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
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

    @GET("/api/account/accountdetails")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    void getAccountDetails(@Header("Authorization") String token, @Query("email")String username, Callback<AccountModel> cb);

    @GET("/api/account/accountaccomplishments")
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    void getAccountAccomplishments(@Header("Authorization") String token, @Query("email")String username, Callback<ScoreModel> cb);

    }
