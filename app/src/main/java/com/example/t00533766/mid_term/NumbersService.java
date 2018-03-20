package com.example.t00533766.mid_term;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by T00533766 on 3/19/2018.
 */

public class NumbersService extends IntentService {
    public NumbersService() {
        super("NumbersService");
    }
    public NumbersService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        URL url = (URL) intent.getSerializableExtra("URL");

        Log.d(url+"", "onHandleIntent: ");

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (httpURLConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = null;
                StringBuilder JSON_RESPONSE = new StringBuilder();
                while ((line = bufferedReader.readLine())!=null){
                    JSON_RESPONSE.append(line);
                }

                Log.d("===============", JSON_RESPONSE.toString());
                parse(JSON_RESPONSE.toString());
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parse(String JSON){

    }

}
