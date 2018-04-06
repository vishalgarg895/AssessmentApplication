package com.example.dell.assessmentapp.networkclient;

import com.example.dell.assessmentapp.model.PictureDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<PictureDetailsResponse> getPicturesDetailList();
}
