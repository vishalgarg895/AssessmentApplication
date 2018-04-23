package com.example.dell.assessmentapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.assessmentapp.R;
import com.example.dell.assessmentapp.adapter.PictureListAdapter;
import com.example.dell.assessmentapp.model.PictureModel;
import com.example.dell.assessmentapp.networkclient.NetworkConnection;
import com.example.dell.assessmentapp.presenter.PictureDetailPresenter;
import com.example.dell.assessmentapp.view.PicturesDetailView;

import java.util.List;

import static android.view.View.*;

public class PictureDetailFragment extends Fragment implements OnClickListener, PicturesDetailView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mPictureDetailRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Button mRetryButton;

    private Context mContext;
    private PictureDetailPresenter mPresenter;
    private PictureListAdapter mPictureListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picture_detail_fragment, container, false);
        mContext = getActivity();
        mPresenter = new PictureDetailPresenter(this);

        initializeViewAndListener(view);
        initializeRecyclerView();
        return view;
    }

    private void initializeRecyclerView() {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mPictureDetailRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPictureListAdapter = new PictureListAdapter(mContext);
        mPictureDetailRecyclerView.setAdapter(mPictureListAdapter);
        mPictureDetailRecyclerView.setHasFixedSize(true);
    }

    private void initializeViewAndListener(View view) {
        mPictureDetailRecyclerView = view.findViewById(R.id.pictureRecyclerView);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mRetryButton = view.findViewById(R.id.retryBtn);

        mRetryButton.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestPicturesDetailList();
    }

    private void requestPicturesDetailList() {
        if (NetworkConnection.isNetworkConnected(mContext)) {
            showProgress();
            mPresenter.callPicturesDetailApi().subscribe();
        } else {
            showNetworkErrorMessage();
        }
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void updatePictureDetailList(List<PictureModel> pictureDetailList) {
        mPictureListAdapter.updatePictureList(pictureDetailList);
        mRetryButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNetworkErrorMessage() {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(mContext, getString(R.string.internet_not_available),
                Toast.LENGTH_SHORT).show();
        if (mPictureListAdapter.getItemCount() == 0 && !mRetryButton.isEnabled()) {
            enableRetryOption();
        }
    }

    private void enableRetryOption() {
        mRetryButton.setEnabled(true);
        mRetryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRequestFailedErrorMessage() {
        Toast.makeText(mContext, getString(R.string.request_failure_message),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        requestPicturesDetailList();
    }

    @Override
    public void onClick(View v) {
        mRetryButton.setEnabled(false);
        requestPicturesDetailList();
    }
}
