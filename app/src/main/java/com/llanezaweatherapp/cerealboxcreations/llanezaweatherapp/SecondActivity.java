package com.llanezaweatherapp.cerealboxcreations.llanezaweatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


// this class is focused on displaying the complete details of the weather

public class SecondActivity extends AppCompatActivity {

    String location;
    String weather;
    String weatherDescription;
    String temp;
    String windSpeed;
    String humidity;
    String weatherIcon;

    TextView cityTextView;
    TextView weatherInfoTextView;
    ImageView iconImageView;

    DownloadImage imageDownloaderTask;

    public void downloadImage() {
        Bitmap myBitmap;
        try{
            //Log.i("icon", weatherIcon);
            myBitmap = imageDownloaderTask.execute("http://openweathermap.org/img/w/" + weatherIcon + ".png").get();
            iconImageView.setImageBitmap(myBitmap);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageDownloaderTask = new DownloadImage();

        location = getIntent().getExtras().getString("Location");
        weather = getIntent().getExtras().getString("Weather");
        weatherDescription= getIntent().getExtras().getString("Description");
        temp = getIntent().getExtras().getString("Temp");
        windSpeed = getIntent().getExtras().getString("WindSpeed");
        humidity = getIntent().getExtras().getString("Humidity");
        weatherIcon = getIntent().getExtras().getString("Icon");

        cityTextView = findViewById(R.id.cityTextView);
        weatherInfoTextView = findViewById(R.id.weatherInfoTextView);
        iconImageView = findViewById(R.id.iconImageView);

        cityTextView.setText(location);
        weatherInfoTextView.setText(weather + "\r\n" + weatherDescription + "\r\n" +  "\r\n"+ "Temperature : " + temp + " C" + "\r\n"
                                    + "Wind : " + windSpeed + " km/h" + "\r\n" + "Humidity : " + humidity + "%" );

        downloadImage();

    }



    //AsyncTask class that handles the download  of image
    protected class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);

                return myBitmap;

            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
