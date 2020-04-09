package com.example.spotifyalbumart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifyalbumart.R;
import com.example.spotifyalbumart.models.Artist;
import com.example.spotifyalbumart.models.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResultsRecyclerAdapter extends RecyclerView.Adapter<ResultsRecyclerAdapter.ViewHolder> {

    private ArrayList<Image> mImageList;
    private ArrayList<Artist> mArtistList;
    private ArrayList<String> mAlbumList;
    private OnCardListener mOnCardListener;

    public ResultsRecyclerAdapter(ArrayList<Image> mImageList, ArrayList<Artist> mArtistList,
                                  ArrayList<String> mAlbumList, OnCardListener mOnCardListener) {

        this.mImageList = mImageList;
        this.mArtistList = mArtistList;
        this.mAlbumList = mAlbumList;
        this.mOnCardListener = mOnCardListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_album_item, parent, false);
        return new ViewHolder(view, mOnCardListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(mImageList.get(position).getUrl()).into(holder.imageView);

        String text = mAlbumList.get(position).toUpperCase() + "\n" + "by" + "\n" + mArtistList.get(position).getName();
        holder.textView.setText(text);

    }


    @Override
    public int getItemCount() {

        return mImageList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView textView;
        private OnCardListener onCardListener;

        public ViewHolder(@NonNull View itemView, OnCardListener onCardListener) {

            super(itemView);
            imageView = itemView.findViewById(R.id.card_imageview);
            textView = itemView.findViewById(R.id.card_textview);
            this.onCardListener = onCardListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            mOnCardListener.onCardClick(getAdapterPosition());

        }
    }


    public interface OnCardListener {

        void onCardClick(int position);

    }

}
