package com.example.vhlee.mygallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private ArrayList<Photo> mListPhotos;
    private Context mContext;
    private LayoutInflater mInflater;

    public PhotoAdapter(ArrayList<Photo> mListPhotos) {
        this.mListPhotos = mListPhotos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        mContext = parent.getContext();
        mInflater = LayoutInflater.from(mContext);
        View view = mInflater.inflate(R.layout.item_recycler, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Photo photo = mListPhotos.get(position);
        File file = new File(photo.getmUrl());
        Picasso.with(mContext).load(file)
                .into(viewHolder.mImage);
        viewHolder.mTitle.setText(photo.getmTitle());
    }

    @Override
    public int getItemCount() {
        return mListPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ImageView mImage;
        private TextView mTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image_view);
            mTitle = itemView.findViewById(R.id.text_title_photo);
        }
    }
}
