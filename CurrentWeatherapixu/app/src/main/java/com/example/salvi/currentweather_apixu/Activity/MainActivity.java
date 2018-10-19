package com.example.salvi.currentweather_apixu.Activity;

/*
 * Get current City Weather from api apixu
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salvi.currentweather_apixu.R;

import data.SetPreferences;

public class MainActivity extends AppCompatActivity {

    public static TextView city ;
    public static TextView country ;
    public static TextView temperature ;
    public static TextView windSpeed ;
    public static TextView windDirection ;
    public static TextView pressure ;
    public static  TextView precipitation ;
    public static TextView humidity ;
    public static TextView cloud ;
    public static TextView lastUpdate ;
    public static ImageView icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.cityname);
        country = findViewById(R.id.countryname);
        temperature = findViewById(R.id.temperature);
        windSpeed = findViewById(R.id.windSpeed);
        windDirection = findViewById(R.id.windDirection);
        pressure= findViewById(R.id.pressure);
        precipitation = findViewById(R.id.precipitation);
        humidity = findViewById(R.id.humidity);
        cloud = findViewById(R.id.cloud);
        lastUpdate  = findViewById(R.id.lastUpdate);
        icon = findViewById(R.id.img);

        //Get the preffered city already stored by user in Settings. Default city is Paris
        SetPreferences setPreferences = new SetPreferences(this.getApplicationContext());
        renderWeather(SetPreferences.getCity());

    }

    private void renderWeather(String city) {

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city});
    }


//To Create Setting Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        if(id == R.id.settings){
            Intent settingIntent = new Intent(MainActivity.this, WeatherSettings.class);
            startActivity(settingIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
