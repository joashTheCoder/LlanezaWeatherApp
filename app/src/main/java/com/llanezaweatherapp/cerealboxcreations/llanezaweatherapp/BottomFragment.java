package com.llanezaweatherapp.cerealboxcreations.llanezaweatherapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BottomFragment extends android.app.Fragment {

    //this is the fragment that holds the button that refreshes the data to make sure the weather is up to date

    BottomFragmentListener bottomFragmentListener;

    public interface BottomFragmentListener {
        void refreshWeatherList();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            bottomFragmentListener = (BottomFragmentListener) activity;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_section_fragment, container, false);

        final Button button = view.findViewById(R.id.refreshButton);

        button.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );

        return view;
    }

    public void buttonClicked(View view){
        bottomFragmentListener.refreshWeatherList();
    }
}
