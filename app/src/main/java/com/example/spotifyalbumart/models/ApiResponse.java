package com.example.spotifyalbumart.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("albums")
    @Expose
    private Album album;

    public Album getAlbums() {
        return album;
    }

    public void setAlbums(Album album) {
        this.album = album;
    }

}
