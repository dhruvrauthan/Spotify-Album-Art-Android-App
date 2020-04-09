package com.example.spotifyalbumart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotifyalbumart.models.ApiResponse;
import com.example.spotifyalbumart.R;
import com.example.spotifyalbumart.interfaces.SpotifyService;
import com.example.spotifyalbumart.models.Album;
import com.example.spotifyalbumart.models.Item;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.spotifyalbumart.activities.LoginActivity.TOKEN;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static List<Item> ITEMLIST;

    //UI
    private EditText mSearchEditText;
    private Button mSearchButton;
    private TextView mTestTextView;

    //Variables
    private SpotifyService mSpotifyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchEditText = findViewById(R.id.search_edittext);
        mSearchButton = findViewById(R.id.search_button);
        mTestTextView = findViewById(R.id.test_textview);

        retrofitOperations();

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSearchEditText.getText() != null) {
                    searchForArt(mSearchEditText.getText().toString());
                }
            }
        });

    }

    private void searchForArt(String text) {

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("Authorization", "Bearer " + TOKEN);

        Call<ApiResponse> call = mSpotifyService.getAlbum(text, "album", parameters);
        Log.d(TAG, "searchForArt: started call");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {

                if (!response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: not successful");
                    return;

                }

                Log.d(TAG, "onResponse: successful");
                Log.d(TAG, "onResponse: " + response);
                mTestTextView.setText(null);
                ApiResponse apiResponse = response.body();
                Album album = apiResponse.getAlbums();

                ITEMLIST = album.getItemList();

                Log.d(TAG, "onResponse: started new activity");
                startActivity(new Intent(MainActivity.this, ResultsActivity.class));

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: call failure " + t.getMessage());

            }
        });

    }

    private void retrofitOperations() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spotify.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mSpotifyService = retrofit.create(SpotifyService.class);

    }
}
