package com.example.dell.assessmentapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.assessmentapp.R;
import com.example.dell.assessmentapp.model.PictureModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.ViewHolder> {

    private List<PictureModel> mPictureModelList;
    private Context mContext;

    public PictureListAdapter(List<PictureModel> mPictureModelList, Context mContext) {
        this.mPictureModelList = mPictureModelList;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mPictureTitleText;
        private TextView mPictureDescriptionText;
        private ImageView mPictureImageView;
        private View mLayout;

        public ViewHolder(View view) {
            super(view);
            mLayout = view;
            mPictureDescriptionText = (TextView) view.findViewById(R.id.pictureDescriptionText);
            mPictureTitleText = (TextView) view.findViewById(R.id.pictureTitleText);
            mPictureImageView = (ImageView) view.findViewById(R.id.pictureImageView);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_detail_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mPictureDescriptionText.setText(mPictureModelList.get(position).getDescription());
        holder.mPictureTitleText.setText(mPictureModelList.get(position).getTitle());

        Picasso.with(mContext).load(mPictureModelList.get(position).getImageHref())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.mPictureImageView);
    }

    @Override
    public int getItemCount() {
        return mPictureModelList.size();
    }
}
