package com.example.dell.assessmentapp.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class PictureDetailFragment extends Fragment implements PicturesDetailView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mPictureDetailRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressDialog mProgressDialog;

    private PictureDetailPresenter mPresenter;
    private Context mContext;
    PictureListAdapter mPictureListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picture_detail_fragment, container, false);
        mContext = getActivity();
        mPictureDetailRecyclerView = (RecyclerView) view.findViewById(R.id.pictureRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mPictureDetailRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mPictureDetailRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPictureListAdapter = new PictureListAdapter(mContext);
        mPictureDetailRecyclerView.setAdapter(mPictureListAdapter);
        mPresenter = new PictureDetailPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestPicturesDetailList();
    }

    private void requestPicturesDetailList() {
        if (NetworkConnection.isNetworkConnected(mContext)) {
            initializeProgressBar();
            mPresenter.retrievePictureDetails();
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            showNetworkErrorMessage();
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
       // PictureListAdapter mPictureListAdapter = new PictureListAdapter(mContext);
      //  mPictureDetailRecyclerView.setAdapter(mPictureListAdapter);
        mPictureListAdapter.updatePictureList(pictureDetailList);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNetworkErrorMessage() {
        Toast.makeText(mContext, getString(R.string.internet_not_available),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRequestFailedErrorMessage() {
        Toast.makeText(mContext, getString(R.string.request_failure_message),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        requestPicturesDetailList();
    }

    private void initializeProgressBar() {
        mProgressDialog = ProgressDialog.show(getActivity(),
                getString(R.string.progress_dialog_title),
                getString(R.string.show_progress_message));
    }
}
