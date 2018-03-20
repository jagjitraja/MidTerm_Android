package com.example.t00533766.mid_term;

import android.app.IntentService;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private String TAG = "===============================";
    private EditText minInput;
    private EditText maxInput;
    private Button mathButton;
    private Button triviaButton;
    String min;
    String max;

    private NumbersViewModel numbersViewModel;
    private ArrayList<NumbersData> numbersDataArrayList;

    private final String BASE_URL = "http://numbersapi.com/";
    private final String MATH = "math";
    private final String TRIVIA = "trivia";
    private final String JSON = "json";

    private NumbersAdapter numbersAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minInput = findViewById(R.id.min_field);
        maxInput = findViewById(R.id.max_field);

        numbersDataArrayList = new ArrayList<>();
        numbersAdapter = new NumbersAdapter(numbersDataArrayList,getApplicationContext());

        numbersViewModel = ViewModelProviders.of(this).get(NumbersViewModel.class);
        numbersViewModel.createReporsitory(getApplicationContext());




        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(numbersAdapter);

        Observer<List<NumbersData>> arrayListObserver = new Observer<List<NumbersData>>() {
            @Override
            public void onChanged(@Nullable List<NumbersData> numbersData) {
                if(numbersData!=null){
                    numbersDataArrayList = (ArrayList<NumbersData>) numbersData;
                    numbersAdapter.setArrayList((ArrayList) numbersData);
                   // textView.setText(numbersData.toString());
                }
                numbersAdapter.notifyDataSetChanged();

                Log.d(TAG, "onChanged: "+numbersDataArrayList.toString());
            }
        };
        numbersViewModel.getNumbersInfoLiveData().observe(this,arrayListObserver);

    }

    public void triviaClickListener(View view) {
        Uri uri = checkInput(TRIVIA);
        if(uri!=null)
            startNumbersService(uri);
        Log.d(TAG, "taaadfadfadfadf: "+ uri);
    }
    public void mathClickListener(View view) {
        Uri uri = checkInput(MATH);
        if(uri!=null)
            startNumbersService(uri);
        Log.d(TAG, "mathClickListener: "+ uri);
    }


    private void startNumbersService(Uri uri){
        try {
            URL url = new URL(uri.toString());
            startService(buildIntent(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    private Intent buildIntent(URL url){
        Intent intent = new Intent(getApplicationContext(),NumbersService.class);
        intent.putExtra("URL",url);
        return intent;
    }

    private Uri checkInput(String end){

        String min = minInput.getText().toString();
        String max = maxInput.getText().toString();

        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();

        if (min.length()<1){
            Toast.makeText(getApplicationContext(),"Please enter atleast the min field",Toast.LENGTH_LONG).show();
            return null;
        }


        if(min.length()>0&&max.length()>0){
            Log.d(TAG, "********************************************************* ");
            return builder.appendPath(min+".."+max)
                    .appendPath(end)
                    .query(JSON).build();
        }else if (max.length()<1){
            Log.d(TAG, "checkInput:----------------------------------------------------- ");
            return builder.appendPath(min)
                    .appendPath(end)
                    .query(JSON).build();
        }else{
            return null;
        }

    }
}
