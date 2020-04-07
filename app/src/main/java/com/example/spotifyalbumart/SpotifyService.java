package com.example.spotifyalbumart;

import com.example.spotifyalbumart.models.Album;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

public interface SpotifyService {

    @GET("search")
    Call<List<Album>> getAlbum(@Query("q") String q,
                               @Query("type") String type,
                               @HeaderMap Map<String,String> headers);

    /*@GET("search?q=arrival&type=album")
    Call<Album> getAlbum(@HeaderMap Map<String,String> headers);*/

}
