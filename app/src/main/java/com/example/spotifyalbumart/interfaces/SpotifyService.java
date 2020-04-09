package com.example.spotifyalbumart.interfaces;

import com.example.spotifyalbumart.models.ApiResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

public interface SpotifyService {

    @GET("search")
    Call<ApiResponse> getAlbum(@Query(value = "q", encoded = true) String q,
                               @Query("type") String type,
                               @HeaderMap Map<String,String> headers);

}
