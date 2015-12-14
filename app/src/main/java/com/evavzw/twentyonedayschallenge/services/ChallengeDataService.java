package com.evavzw.twentyonedayschallenge.services;

import com.evavzw.twentyonedayschallenge.models.AccountModel;
import com.evavzw.twentyonedayschallenge.models.ChallengeModel;
import com.evavzw.twentyonedayschallenge.models.ChooseChallengeModel;
import com.evavzw.twentyonedayschallenge.models.ChosenChallengeModel;
import com.evavzw.twentyonedayschallenge.models.CompleteChallengeModel;
import com.evavzw.twentyonedayschallenge.models.Registration;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Nico on 7/12/2015.
 */
public interface ChallengeDataService {
    @GET("/api/challenge/getrandomchallenges/{number}")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    void getChallenges(@Header("Authorization") String token, @Path("number")int number, Callback<List<ChallengeModel>> cb);

    @GET("/api/challenge/getchosenchallenge")
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    void checkForChosenChallenge(@Header("Authorization") String token, @Query("email")String username, Callback<ChosenChallengeModel> cb);

    @POST("/api/challenge/chooseChallenge")
    void chooseChallenge(@Body ChooseChallengeModel chooseChallengeModel, Callback<Response> cb);

    @POST("/api/challenge/completeChallenge")
    void completeChallenge(@Body CompleteChallengeModel completeChallengeModel, Callback<Response> cb);
}
