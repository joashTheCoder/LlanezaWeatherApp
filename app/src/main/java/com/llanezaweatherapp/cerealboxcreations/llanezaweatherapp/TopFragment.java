package com.llanezaweatherapp.cerealboxcreations.llanezaweatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TopFragment extends Fragment {

    //This is the fragment that holds the Listview which displays the weather

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_section_fragment, container, false);

        listView = view.findViewById(R.id.listViewLayout);

        return view;
    }

    public void populateListView(final Context context, final ArrayList<WeatherInfo> weatherInfos){
        //<WeatherInfo> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, weatherInfos);

        ListAdapter adapter = new CustomAdapter(weatherInfos, context);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("Location", weatherInfos.get(i).getLocation());
                intent.putExtra("Weather", weatherInfos.get(i).getWeather());
                intent.putExtra("Description", weatherInfos.get(i).getWeatherDescription());
                intent.putExtra("Temp", weatherInfos.get(i).getTemp());
                intent.putExtra("WindSpeed", weatherInfos.get(i).getWindSpeed());
                intent.putExtra("Humidity", weatherInfos.get(i).getHumidity());
                intent.putExtra("Icon", weatherInfos.get(i).getWeatherIcon());
                startActivity(intent);
            }
        });
    }
}
