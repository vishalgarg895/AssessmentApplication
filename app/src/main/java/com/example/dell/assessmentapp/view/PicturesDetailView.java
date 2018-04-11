package com.example.dell.assessmentapp.view;

import com.example.dell.assessmentapp.model.PictureModel;

import java.util.List;

public interface PicturesDetailView {

    void showProgress();

    void hideProgress();

    void updatePictureDetailList(List<PictureModel> pictureDetailList);

    void showNetworkErrorMessage();

    void showRequestFailedErrorMessage();
}
