package com.example.dell.assessmentapp.presenter;

import com.example.dell.assessmentapp.ui.mvp.model.PictureDetailsResponse;
import com.example.dell.assessmentapp.ui.mvp.model.PictureModel;
import com.example.dell.assessmentapp.networkclient.ApiClient;
import com.example.dell.assessmentapp.networkclient.ApiInterface;
import com.example.dell.assessmentapp.ui.mvp.presenter.PictureDetailPresenter;
import com.example.dell.assessmentapp.ui.mvp.view.PicturesDetailView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PictureDetailPresenterTest {
    @Mock
    private ApiClient mApiClient;
    @Mock
    private ApiInterface mApiInterface;
    @Mock
    private PicturesDetailView mPicturesDetailView;
    @Mock
    private PictureDetailsResponse mPictureDetailsResponse;
    @Mock
    List<PictureModel> modelList;
    @Mock
    Disposable mDisposable;

    io.reactivex.Observable<Response<PictureDetailsResponse>> observable = io.reactivex.Observable.just(Response.success(mPictureDetailsResponse));

    private PictureDetailPresenter mPictureDetailPresenter;

    @Before
    public void setup() {
        mPictureDetailPresenter = new PictureDetailPresenter(mPicturesDetailView);
    }

    @Test
    public void test1()
    {
        when(mApiInterface.getPictureDetails()).thenReturn(Single.just(mPictureDetailsResponse));
        when(mPictureDetailsResponse.getPictureModelList()).thenReturn(modelList);

        mPictureDetailPresenter.callPicturesDetailApi().test().assertComplete().assertNoErrors();

        verify(mPicturesDetailView).updatePictureDetailList(modelList);
        verify(mPicturesDetailView).hideProgress();
    }
}
