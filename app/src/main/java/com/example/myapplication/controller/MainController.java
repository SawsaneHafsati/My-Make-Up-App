package com.example.myapplication.controller;

import android.content.SharedPreferences;

import com.example.myapplication.Constants;
import com.example.myapplication.Singletons;
import com.example.myapplication.model.Makeup;
import com.example.myapplication.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Controleur principal gérant les interactions de l'utilisateur
 */
public class MainController {
    SharedPreferences sharedPreferences;
    Gson gson;
    private MainActivity view;

    /**
     * Constructeur du controleur
     * @param mainActivity Vue à laquelle le controleur est lié
     * @param gson Objet gson à utiliser pour gérer les conversions en JSON
     * @param sharedPreferences Objet SharedPreference à utiliser pour stocker et lire le cache
     */
    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * Méthode gérant le lancement de l'application
     */
    public void onStart() {
        List<Makeup> makeupList = getDataFromCache();
        if(makeupList != null) {
            view.showList(makeupList);
        } else {
            makeAPICall();
        }
    }

    /**
     * Méthode sauvegardant la liste des Makeup en cache
     * @param makeupList
     */
    private void saveList(List<Makeup> makeupList) {
        String jsonString = gson.toJson(makeupList);
        sharedPreferences
                .edit()
                .putString(Constants.JSON_KEY, jsonString)
                .apply();
    }

    /**
     * Méthode gérant les appels au serveur
     */
    public void makeAPICall() {
        Call<List<Makeup>> call = Singletons.getMakeupAPI().getMakeup();

        call.enqueue(new Callback<List<Makeup>>() {
            @Override
            public void onResponse(Call<List<Makeup>> call, Response<List<Makeup>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Makeup> makeupList = response.body();
                    saveList(makeupList);
                    view.showList(makeupList);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<List<Makeup>> call, Throwable t) {
                view.showError();
            }
        });
    }

    /**
     * Méthode retournant le contenu du cache
     * @return liste de Makeup
     */
    private List<Makeup> getDataFromCache() {
        String jsonMakeup = sharedPreferences.getString(Constants.JSON_KEY, null);

        if(jsonMakeup == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Makeup>>(){}.getType();
            return gson.fromJson(jsonMakeup, listType);
        }
    }
}
