package com.example.salvi.myweather;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import Utils.Utils;
import data.CityPreference;
import data.JSONWeatherParser;
import data.WeatherHTTPClient;
import model.Weather;

public class MainActivity extends AppCompatActivity {

        private TextView city;
        private TextView temp;
        private TextView wind;
        private TextView cloud;
        private TextView pressure;
        private TextView humidity;
        private TextView sunrise;
        private TextView sunset;
        private TextView update ;
        private ImageView img;

        Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.cityName);
        temp = findViewById(R.id.temp);
        wind  = findViewById(R.id.windText);
        cloud = findViewById(R.id.cloudText);
        pressure = findViewById(R.id.pressureText);
        humidity = findViewById(R.id.humidityText);
        sunrise = findViewById(R.id.sunriseText);
        sunset = findViewById(R.id.sunsetText);
        update = findViewById(R.id.updateText);
        img  = findViewById(R.id.img);

        CityPreference cityPreference = new CityPreference(MainActivity.this);

        renderWeatherData(cityPreference.getCity());
        //renderWeatherData("Portland,US");

    }

    public void renderWeatherData(String city){

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&appid=b6907d289e10d714a6e88b30761fae22"});
    }

    private class  AsyncImageDownloadTask extends AsyncTask<String, Void, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImage(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            img.setImageBitmap(bitmap);
            Log.v("DL Post Ex : ", " I am here");
        }

        private Bitmap downloadImage(String code)  {
            InputStream  bitStream = null;

            try {
                URL imgurl = new URL(Utils.ICON_URL ); //+ code + ".png");
                HttpURLConnection httpURLConnection = (HttpURLConnection) imgurl.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

                     bitStream = httpURLConnection.getInputStream();
                }
                else {
                    Log.e("Download Image : ", "Error : " + httpURLConnection.getResponseCode());
                    Log.v("In DL Error : ", "In error");
                }
                final  Bitmap bitmap = BitmapFactory.decodeStream(bitStream);
                bitStream.close();
                return  bitmap;

            } catch (IOException e) {
                e.printStackTrace();
            }

                return null;
        }
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather>{

        @Override
        protected Weather doInBackground(String... params) {

            String data = new WeatherHTTPClient().getWeatherdata(params[0]);
            weather = JSONWeatherParser.getWeather(data);

            Log.v("Data : ", weather.place.getCountry());

            weather.iconData = weather.currentCondition.getIcon();
            new AsyncImageDownloadTask().execute(weather.iconData);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            DateFormat df = DateFormat.getTimeInstance();
            String sunriseTime = df.format(new Date(weather.place.getSunrise()));
            String sunsetTime = df.format(new Date((long) weather.place.getSunset()));
            String updateTime =  df.format(new Date(weather.place.getLastupdate()));

            DecimalFormat decF = new DecimalFormat("#.#");
            String tempF = decF.format(weather.currentCondition.getTemperature());


            city.setText(weather.place.getCity() + " , " + weather.place.getCountry());
            temp.setText("" + tempF + " °C");
            wind.setText("Wind : " + weather.wind.getSpeed() + " mps");
            pressure.setText("Pressure : " + weather.currentCondition.getPressure() + " hPa");
            humidity.setText("Humidity : " + weather.currentCondition.getHumidity() + " %");
            sunrise.setText("Sunrise : " + sunriseTime);
            sunset.setText("Sunset : " + sunsetTime);
            cloud.setText("Condition : " +weather.currentCondition.getCondition() +
                            "(" + weather.currentCondition.getDescription() + ")");
            update.setText("Last update on : " + updateTime);

        }
    }

    private void showInputDialog(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Select City");

        final EditText cityInput  = new EditText(MainActivity.this);
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("Portland,US");
        alertDialog.setView(cityInput);
        alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CityPreference cityPreference = new CityPreference(MainActivity.this);
                cityPreference.setCity(cityInput.getText().toString());

                String newCity = cityPreference.getCity();

                renderWeatherData(newCity);
            }
        });
        Log.v("In AlertDialog", "I am in ");
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();

        if(id == R.id.change_cityID){
            showInputDialog();
        }
        return super.onOptionsItemSelected(item);
    }
}
