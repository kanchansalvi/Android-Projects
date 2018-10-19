package com.example.salvi.currentweather_apixu.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Model.Weather;
import data.HttpURL_Connection;
import data.JSONWeatherParser;
import data.SetPreferences;

public class WeatherTask extends AsyncTask<String, Void, Weather> {

      private Weather weather = new Weather();
     MainActivity main =  new MainActivity();

    @Override
    protected Weather doInBackground(String... params) {

        //Get the downloaded weather data from HTTP Url
        String data = new HttpURL_Connection().getWeatherData(params[0]);
        //Tell the parser to parse the dowloaded data to be used
        weather = JSONWeatherParser.getWeather(data);

        //Get the url for weather Icon. It is mentione tn the parserd data .
        // Ask the Asynchronous Task to download and display the image in proper format(Bitmap image)
        weather.icon = weather.getIcon();
        new AsyncImageDownloadTask().execute(weather.icon);

        //Return the weather data.
        return weather;
    }


    //Once the weather data is available, display in the Layout File.
    //Display data as per the Preferences stored by the user.
    // Get the Preferences form the Preference File.
    @Override
    protected void onPostExecute(Weather weather) {
       // super.onPostExecute(weather);
        String tPref;

        main.city.setText(weather.getCityName() + ", " + weather.getRegion());
        main.country.setText(weather.getCountry());

         tPref = SetPreferences.getTempPref();
       if(tPref.equals("° C")) {
           main.temperature.setText(weather.getTemp_C() + tPref +" (" + weather.getCondition() + ")");
       }

        if(tPref.equals("° F")) {
            main.temperature.setText(weather.getTemp_F() + tPref +" (" + weather.getCondition() + ")");
        }

        tPref = SetPreferences.getSpeedPref();
        if(tPref.equals("kph"))
            main.windSpeed.setText("Wind Speed : " + weather.getWind_kph() + " " + tPref);
        if(tPref.equals("mph"))
            main.windSpeed.setText("Wind Speed : " + weather.getWind_mph() + " " + tPref);


        main.windDirection.setText("Wind Direction : " + weather.getWindDegree() + "°  " + weather.getWindDirection());

        tPref = SetPreferences.getPressurePref();
        Log.v("InWeather Prefs: 3" , tPref + " " + weather.getPressure_in());
        if(tPref.equals("in"))
             main.pressure.setText("Pressure : " + weather.getPressure_in() + " " + tPref);
        if(tPref == "mb")
            main.pressure.setText("Pressure : " + weather.getPressure_mb() + " " + tPref);

        tPref = SetPreferences.getPrecPref();
        Log.v("InWeather Prefs: 4" , tPref + " " + weather.getPrecipitation_in());
        if(tPref.equals("in"))
            main.precipitation.setText("Precipitation : " + weather.getPrecipitation_in() + " " + tPref);
        if(tPref.equals("mm"))
            main.precipitation.setText("Precipitation : " + weather.getPrecipitation_mm() + " " + tPref);

        main.humidity.setText("Humidity : " + weather.getHumidity() + "%");
        main.cloud.setText("Cloud Cover : " + weather.getCloud() + "%");
        main.lastUpdate.setText(weather.getLastUpdte());
    }


/*
Download the image and display it
 */
    protected   class AsyncImageDownloadTask extends AsyncTask <String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImage(params[0]);
        }

        protected Bitmap downloadImage(String imgUrl) {

            try {
                InputStream bitStream = null;
                HttpURLConnection connection = (HttpURLConnection)  (new URL("http://" + imgUrl)).openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    bitStream = connection.getInputStream();
                }

                final  Bitmap bitmap = BitmapFactory.decodeStream(bitStream);
                bitStream.close();
                return bitmap;


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            main.icon.setImageBitmap(bitmap);
        }
    }
}
