package com.example.dell.assessmentapp.networkclient;

import com.example.dell.assessmentapp.ui.mvp.model.PictureDetailsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    Single<PictureDetailsResponse> getPictureDetails();
}
