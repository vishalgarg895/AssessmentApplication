package com.example.dell.assessmentapp.ui.mvp.model;

import com.google.gson.annotations.SerializedName;

public class PictureModel {
    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imageHref")
    private String imageHref;

    public PictureModel(String title,String description,String imageHref)
    {
        this.title=title;
        this.description=description;
        this.imageHref=imageHref;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }
}
