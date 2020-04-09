package com.example.spotifyalbumart.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.spotifyalbumart.R;
import com.example.spotifyalbumart.models.Image;
import com.squareup.picasso.Picasso;


public class DownloadActivity extends AppCompatActivity {

    private static final String TAG = "DownloadActivity";

    //UI
    private ImageView mImageView;
    private Button mDownloadButton;
    private ProgressDialog progressDialog;

    //Variables
    private Image mImage;
    public static final int progressType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mImageView = findViewById(R.id.download_imageview);
        mDownloadButton = findViewById(R.id.download_button);

        mImage = getIntent().getParcelableExtra("image");

        Picasso.get()
                .load(mImage.getUrl())
                .fit()
                .into(mImageView);

        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mImage.getUrl()));
                startActivity(browserIntent);

            }
        });

    }


}

