package com.example.dell.assessmentapp.ui.mvp.presenter;

import android.util.Log;

import com.example.dell.assessmentapp.ui.mvp.model.PictureDetailsResponse;
import com.example.dell.assessmentapp.ui.mvp.model.PictureModel;
import com.example.dell.assessmentapp.networkclient.ApiClient;
import com.example.dell.assessmentapp.networkclient.ApiInterface;
import com.example.dell.assessmentapp.ui.mvp.view.PicturesDetailView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PictureDetailPresenter {

    public final String TAG = this.getClass().getSimpleName();

    private ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    private PicturesDetailView mPicturesDetailView;

    public PictureDetailPresenter(PicturesDetailView picturesDetailView) {
        mPicturesDetailView = picturesDetailView;
    }

    public Completable callPicturesDetailApi() {
        return mApiInterface.getPictureDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<PictureDetailsResponse, List<PictureModel>>() {
                    @Override
                    public List<PictureModel> apply(PictureDetailsResponse pictureDetailsResponse) throws Exception {

                        List<PictureModel> pictureModelList = pictureDetailsResponse.getPictureModelList();
                        Log.e(TAG, pictureModelList.toString());
                        return pictureModelList;
                    }
                }).doOnSuccess(new Consumer<List<PictureModel>>() {
                    @Override
                    public void accept(List<PictureModel> pictureModelList) {
                        mPicturesDetailView.updatePictureDetailList(pictureModelList);
                        mPicturesDetailView.hideProgress();
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mPicturesDetailView.showRequestFailedErrorMessage();
                        mPicturesDetailView.hideProgress();
                    }
                }).toCompletable();
    }
}
