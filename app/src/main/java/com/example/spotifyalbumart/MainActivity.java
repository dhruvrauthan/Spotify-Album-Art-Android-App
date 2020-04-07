package com.example.spotifyalbumart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotifyalbumart.models.Album;
import com.example.spotifyalbumart.models.Image;
import com.example.spotifyalbumart.models.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.spotifyalbumart.LoginActivity.TOKEN;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //UI
    private EditText mSearchEditText;
    private Button mSearchButton;
    private TextView mTestTextView;

    //Variables
    private SpotifyService mSpotifyService;
    private List<Album> mAlbumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchEditText = findViewById(R.id.search_edittext);
        mSearchButton = findViewById(R.id.search_button);
        mTestTextView = findViewById(R.id.test_textview);
        mAlbumList = new ArrayList<>();

        retrofitOperations();

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSearchEditText != null) {
                    searchForArt(mSearchEditText.getText().toString());
                }
            }
        });

    }

    private void searchForArt(String text) {

        text = text.replace(" ", "&20");
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("Authorization", "Bearer " + TOKEN);

        Call<List<Album>> call = mSpotifyService.getAlbum(text, "album", parameters);
        Log.d(TAG, "searchForArt: started call");
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                if (!response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: not successful");
                    return;

                }

                mTestTextView.setText(null);
                mAlbumList = response.body();
                for (Album album : mAlbumList) {

                    List<Item> itemList = album.getItemList();

                    for (Item item : itemList) {

                        List<Image> imageList = item.getImageList();

                        for (Image image : imageList) {

                            mTestTextView.append(image.getUrl() + "\n");

                        }


                    }
                }

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: call failure");

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
