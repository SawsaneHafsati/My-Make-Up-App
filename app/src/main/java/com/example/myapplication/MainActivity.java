package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList();
        makeAPICall();
    }

    public void showList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }

        // define an adapter
        mAdapter = new ListAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }

    private static final String BASE_URL = "https://raw.githubusercontent.com/SawsaneHafsati/MyApp/master/app/src/main/java/com/example/myapplication/";
    public void makeAPICall() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MakeupAPI makeupAPI = retrofit.create(MakeupAPI.class);

        Call<List<Makeup>> call = makeupAPI.getMakeup();

        call.enqueue(new Callback<List<Makeup>>() {
            @Override
            public void onResponse(Call<List<Makeup>> call, Response<List<Makeup>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Makeup> makeupList = response.body();
                    System.out.println(makeupList);
                    Toast.makeText(getApplicationContext(), "API success", Toast.LENGTH_SHORT).show();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Makeup>> call, Throwable t) {
                showError();
            }
        });
    }

    private void showError() {
        Toast.makeText(this, "API error", Toast.LENGTH_SHORT).show();
    }
}
