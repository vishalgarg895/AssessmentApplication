package com.example.dell.assessmentapp.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.assessmentapp.R;
import com.example.dell.assessmentapp.adapter.PictureListAdapter;
import com.example.dell.assessmentapp.model.PictureModel;
import com.example.dell.assessmentapp.networkclient.NetworkConnection;
import com.example.dell.assessmentapp.presenter.PictureDetailPresenter;
import com.example.dell.assessmentapp.view.PicturesDetailView;

import java.util.List;

public class PictureDetailFragment extends BaseFragment implements PicturesDetailView {

    private RecyclerView mPictureDetailRecyclerView;

    private PictureDetailPresenter mPresenter;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picture_detail_fragment, container, false);
        mContext = getActivity();
        mPictureDetailRecyclerView = (RecyclerView) view.findViewById(R.id.pictureRecyclerView);
        mPictureDetailRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mPictureDetailRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPresenter = new PictureDetailPresenter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkConnection.isNetworkConnected(mContext)) {
            initializeProgressBar();
            mPresenter.retrievePictureDetails();
        } else {
            showNetworkErrorToastMessage();
        }
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void updatePictureDetailList(List<PictureModel> pictureDetailList) {
        PictureListAdapter mPictureListAdapter = new PictureListAdapter(pictureDetailList, mContext);
        mPictureDetailRecyclerView.setAdapter(mPictureListAdapter);
    }

    @Override
    public void showNetworkErrorToastMessage() {
        Toast.makeText(mContext, getString(R.string.request_failure_message),
                Toast.LENGTH_SHORT).show();
    }

    private void initializeProgressBar() {
        mProgressDialog = ProgressDialog.show(getActivity(),
                getString(R.string.progress_dialog_title),
                getString(R.string.show_progress_message));
    }
}
