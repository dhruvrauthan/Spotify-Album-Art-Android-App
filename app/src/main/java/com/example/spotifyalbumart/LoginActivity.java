package com.example.spotifyalbumart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    public static final String CLIENT_ID = "c2bd5a57ca1940b7ac100444f2ddde22";
    public static final String REDIRECT_URI = "com.example.spotifyalbumart://callback";
    public static final int REQUEST_CODE = 1337;
    public static final String SCOPES = "user-read-private,user-read-email";
    public static String TOKEN=null;

    private AuthenticationRequest mAuthenticationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authenticateSpotify();
    }

    private void authenticateSpotify() {

        AuthenticationRequest.Builder builder=
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{SCOPES});
        mAuthenticationRequest= builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, mAuthenticationRequest);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE){

            AuthenticationResponse response= AuthenticationClient.getResponse(resultCode, data);

            switch (response.getType()){

                case TOKEN:{

                    TOKEN= response.getAccessToken();
                    Log.d(TAG, "onActivityResult: Got Access Token");
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    break;

                }

                case ERROR:{

                    Log.d(TAG, "onActivityResult: Got error!");
                    break;

                }

            }

        }

    }
}
