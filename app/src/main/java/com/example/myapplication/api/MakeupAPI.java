package com.example.myapplication.api;

import com.example.myapplication.model.Makeup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MakeupAPI {
    @GET("makeupapi.json")
    Call<List<Makeup>> getMakeup();
}
