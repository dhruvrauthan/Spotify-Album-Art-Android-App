package com.example.spotifyalbumart.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item implements Parcelable {

    @SerializedName("images")
    private List<Image> imageList;

    @SerializedName("name")
    private String name;

    @SerializedName("artists")
    private List<Artist> artistList;

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public Item() {
    }

    protected Item(Parcel in) {
        imageList = in.createTypedArrayList(Image.CREATOR);
        name = in.readString();
        artistList = in.createTypedArrayList(Artist.CREATOR);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(imageList);
        dest.writeString(name);
        dest.writeTypedList(artistList);
    }
}
