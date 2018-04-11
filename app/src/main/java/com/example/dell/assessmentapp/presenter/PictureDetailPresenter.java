package com.example.dell.assessmentapp.presenter;

import com.example.dell.assessmentapp.model.PictureDetailsResponse;
import com.example.dell.assessmentapp.model.PictureModel;
import com.example.dell.assessmentapp.networkclient.ApiClient;
import com.example.dell.assessmentapp.networkclient.ApiInterface;
import com.example.dell.assessmentapp.view.PicturesDetailView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureDetailPresenter {

    private ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    private PicturesDetailView mPicturesDetailView;

    public PictureDetailPresenter(PicturesDetailView picturesDetailView) {
        mPicturesDetailView = picturesDetailView;
    }

    public void retrievePictureDetails() {
        mPicturesDetailView.showProgress();
        callPictureDetailApi();
    }

    private void callPictureDetailApi() {
        Call<PictureDetailsResponse> call = mApiInterface.getPicturesDetailList();
        call.enqueue(new Callback<PictureDetailsResponse>() {
            @Override
            public void onResponse(Call<PictureDetailsResponse> call, Response<PictureDetailsResponse> response) {
                List<PictureModel> pictureDetailList = response.body().getPictureModelList();
                mPicturesDetailView.updatePictureDetailList(pictureDetailList);
                mPicturesDetailView.hideProgress();
            }

            @Override
            public void onFailure(Call<PictureDetailsResponse> call, Throwable t) {
                mPicturesDetailView.hideProgress();
                mPicturesDetailView.showRequestFailedErrorMessage();
            }
        });
    }
}
