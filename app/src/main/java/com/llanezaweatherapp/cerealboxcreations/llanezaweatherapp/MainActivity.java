package com.llanezaweatherapp.cerealboxcreations.llanezaweatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements BottomFragment.BottomFragmentListener{

    String[] cityList = {"London", "Prague", "San Francisco"};
    ArrayList<WeatherInfo> weatherInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherInfos = new ArrayList<>();

        getWeatherInfo();


    }

    //After the execution of the DownloadTask Task, THis method will collect the data and add it to a weatherInfo arrayList that will populate the ListView
    //in the top fragment
    protected void addWeatherInfo(String location, String weather, String weatherDescription, String temp, String windSpeed, String humidity, String weatherIcon){
        weatherInfos.add(new WeatherInfo(location, weather, weatherDescription, temp, windSpeed, humidity, weatherIcon));
        for (int j = 0; j < weatherInfos.size(); j++) {
            TopFragment topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

            topFragment.populateListView(this, weatherInfos);


        }

    }

    //This method is responsible for instantiating the DownloadTask Class that inherits from AsyncTask that handles another thread to get data
    //from the internet and the OpenWeatherMap api
    protected void getWeatherInfo(){
        for(int i = 0; i < cityList.length; i++){
            DownloadTask task = new DownloadTask();
            task.execute("https://openweathermap.org/data/2.5/weather?q=" + cityList[i] + "&appid=b6907d289e10d714a6e88b30761fae22");
        }
    }
    // interface method
    // this gets called by the bottom fragment
    @Override
    public void refreshWeatherList() {
        weatherInfos.clear();
        getWeatherInfo();
    }



    //AsyncTask
    //class that creates the thread that gets the weather info in the background
    public class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            String result = " ";
            URL url;
            HttpURLConnection urlConnection =null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject jsonObject = new JSONObject(s);
                String locationName = jsonObject.getString("name");
                String weatherInfo = jsonObject.getString("weather");
                JSONObject weatherMain = jsonObject.getJSONObject("main");
                JSONObject wind = jsonObject.getJSONObject("wind");
                String weather = " ";
                String weatherDescription = " ";
                String temp = weatherMain.getString("temp");
                String windSpeed = wind.getString("speed");
                String humidity= weatherMain.getString("humidity");
                String weatherIcon= " ";


                JSONArray weatherInfoArray = new JSONArray(weatherInfo);
                for(int i = 0; i< weatherInfoArray.length(); i++){
                    JSONObject jsonPart = weatherInfoArray.getJSONObject(i);
                    weather = jsonPart.getString("main");
                    weatherDescription = jsonPart.getString("description");
                    weatherIcon = jsonPart.getString("icon");
                }

                /*Log.i("location", locationName);
                Log.i("weather", weather);
                Log.i("Description", weatherDescription);
                Log.i("Temp", temp);
                Log.i("Speed", windSpeed);
                Log.i("Humidity", humidity);
                Log.i("Icon", weatherIcon);*/

                addWeatherInfo(locationName, weather, weatherDescription, temp, windSpeed, humidity, weatherIcon);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
