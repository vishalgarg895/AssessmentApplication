package com.example.dell.assessmentapp.ui.mvp.view;

import com.example.dell.assessmentapp.ui.mvp.model.PictureModel;

import java.util.List;

public interface PicturesDetailView {

    void showProgress();

    void hideProgress();

    void updatePictureDetailList(List<PictureModel> pictureDetailList);

    void showNetworkErrorMessage();

    void showRequestFailedErrorMessage();
}
