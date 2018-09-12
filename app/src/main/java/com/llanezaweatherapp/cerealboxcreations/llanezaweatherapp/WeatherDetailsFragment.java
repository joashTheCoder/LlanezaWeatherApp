package com.llanezaweatherapp.cerealboxcreations.llanezaweatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WeatherDetailsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_details_fragment, container, false);

        return view;
    }

    public void showWeatherDetails(ArrayList<WeatherInfo> weatherInfos){

    }


}
