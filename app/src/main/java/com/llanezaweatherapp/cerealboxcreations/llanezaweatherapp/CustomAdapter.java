package com.llanezaweatherapp.cerealboxcreations.llanezaweatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<WeatherInfo>{

    //This is the custom adapter class that sets what data to display on the ListView

    public CustomAdapter(ArrayList<WeatherInfo> data, Context context) {
        super(context, R.layout.row_item, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.row_item, parent, false);

        WeatherInfo weatherInfo = getItem(position);
        TextView locationTextView = customView.findViewById(R.id.locationTextView);
        TextView weatherTextView = customView.findViewById(R.id.weatherTextView);
        TextView tempTextView = customView.findViewById(R.id.tempTextView);

        locationTextView.setText(weatherInfo.getLocation());
        weatherTextView.setText(weatherInfo.getWeather());
        tempTextView.setText(weatherInfo.getTemp() + "°C");

        return customView;

    }
}

