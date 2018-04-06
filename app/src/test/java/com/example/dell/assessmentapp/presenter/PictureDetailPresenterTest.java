package com.example.dell.assessmentapp.presenter;

import com.example.dell.assessmentapp.model.PictureDetailsResponse;
import com.example.dell.assessmentapp.networkclient.ApiClient;
import com.example.dell.assessmentapp.networkclient.ApiInterface;
import com.example.dell.assessmentapp.view.PicturesDetailView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import retrofit2.Call;
import retrofit2.Response;

@RunWith(MockitoJUnitRunner.class)
public class PictureDetailPresenterTest {
    @Mock
    private ApiClient mApiClient;
    @Mock
    private ApiInterface mApiInterface;
    @Mock
    private PicturesDetailView mPicturesDetailView;


    @Test
    public void retrievePictureDetails_getPictureDetails() throws Exception {
        Call<PictureDetailsResponse> call = Mockito.verify(mApiInterface).getPicturesDetailList();

        Response<PictureDetailsResponse> respons = call.execute();

        Assert.assertTrue(respons.isSuccessful());
    }
}
