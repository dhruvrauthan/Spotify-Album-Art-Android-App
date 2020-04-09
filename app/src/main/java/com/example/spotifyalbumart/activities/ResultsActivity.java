package com.example.spotifyalbumart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.spotifyalbumart.R;
import com.example.spotifyalbumart.adapters.ResultsRecyclerAdapter;
import com.example.spotifyalbumart.models.Artist;
import com.example.spotifyalbumart.models.Image;
import com.example.spotifyalbumart.models.Item;

import java.util.ArrayList;
import java.util.List;

import static com.example.spotifyalbumart.activities.MainActivity.ITEMLIST;

public class ResultsActivity extends AppCompatActivity implements ResultsRecyclerAdapter.OnCardListener{

    private static final String TAG = "ResultsActivity";

    //UI
    private RecyclerView mRecyclerView;
    private ResultsRecyclerAdapter mRecyclerAdapter;

    //Variables
    private ArrayList<Image> mImageList;
    private ArrayList<String> mAlbumList;
    private ArrayList<Artist> mArtistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mRecyclerView = findViewById(R.id.results_recyclerview);

        mImageList = new ArrayList<>();
        mAlbumList = new ArrayList<>();
        mArtistList = new ArrayList<>();

        for (Item item : ITEMLIST) {

            List<Image> imageList = item.getImageList();
            Image image = imageList.get(0);
            mImageList.add(image);

            List<Artist> artistList = item.getArtistList();
            Artist artist = artistList.get(0);
            mArtistList.add(artist);

            String albumName = item.getName();
            mAlbumList.add(albumName);

        }

        Log.d(TAG, "onCreate: filled items list");

        initRecyclerView();

    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerAdapter = new ResultsRecyclerAdapter(mImageList, mArtistList, mAlbumList, this);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        Log.d(TAG, "initRecyclerView: filled recyclerview");

    }

    @Override
    public void onCardClick(int position) {

        Intent intent= new Intent(ResultsActivity.this, DownloadActivity.class);
        intent.putExtra("image", mImageList.get(position));
        startActivity(intent);
        Log.d(TAG, "onCardClick: started DownloadActivity");

    }


}
