package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.api.MakeupAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Méthode définissant tous les singletons de l'application (principe d'injection de dépendances)
 */
public class Singletons {
    private static Gson gsonInstance;
    private static SharedPreferences spInstance;
    private static MakeupAPI makeupAPIInstance;


    public static Gson getGson() {
        if(gsonInstance == null)
            gsonInstance = new GsonBuilder().setLenient().create();

        return gsonInstance;
    }

    public static MakeupAPI getMakeupAPI() {
        if(makeupAPIInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            makeupAPIInstance = retrofit.create(MakeupAPI.class);
        }

        return makeupAPIInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if(spInstance == null) {
            spInstance = context.getSharedPreferences("application_makeup", Context.MODE_PRIVATE);
        }

        return spInstance;
    }
}
