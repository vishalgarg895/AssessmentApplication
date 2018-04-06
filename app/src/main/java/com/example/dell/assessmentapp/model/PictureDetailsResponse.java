package com.example.dell.assessmentapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PictureDetailsResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("rows")
    private List<PictureModel> pictureModelList;

    public String getStatus() {
        return status;
    }

    public List<PictureModel> getPictureModelList() {
        return pictureModelList;
    }
}
