package com.example.salvi.currentweather_apixu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.salvi.currentweather_apixu.R;

import data.SetPreferences;

public class WeatherSettings extends AppCompatActivity {

    private EditText city;
    private RadioGroup tempPref;
    private RadioGroup speedPref;
    private RadioGroup pressurePref;
    private RadioGroup precipitationPref;
    private RadioButton tempC;
    private RadioButton tempF;
    private RadioButton kph;
    private RadioButton mph;
    private RadioButton mb ;
    private RadioButton in ;
    private RadioButton mm ;
    private RadioButton precin ;
    private Button set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_settings);

        city = findViewById(R.id.cityPref);

        tempPref = findViewById(R.id.tempPref);
        tempC = findViewById(R.id.degree);
        tempF = findViewById(R.id.farenheit);
        if(SetPreferences.getTempPref().equals(tempC.getText().toString()))
            tempPref.check(tempC.getId());
        if(SetPreferences.getTempPref().equals(tempF.getText().toString()))
            tempPref.check(tempF.getId());

        speedPref = findViewById(R.id.speedPref);
        kph = findViewById(R.id.kph);
        mph = findViewById(R.id.mph);
        if(SetPreferences.getSpeedPref().equals(kph.getText().toString()))
            speedPref.check(kph.getId());
        if(SetPreferences.getSpeedPref().equals(mph.getText().toString()))
            speedPref.check(mph.getId());


        pressurePref = findViewById(R.id.pressurePref);
        mb = findViewById(R.id.mb);
        in = findViewById(R.id.in);
        if(SetPreferences.getPressurePref().equals(mb.getText().toString()))
            pressurePref.check(mb.getId());
        if(SetPreferences.getPressurePref().equals(in.getText().toString()))
            pressurePref.check(in.getId());


        precipitationPref = findViewById(R.id.precipitationPref);
        mm = findViewById(R.id.mm);
        precin=findViewById(R.id.precin);
        if(SetPreferences.getPrecPref().equals(mm.getText().toString()))
            precipitationPref.check(mm.getId());
        if(SetPreferences.getPrecPref().equals(in.getText().toString()))
            precipitationPref.check(in.getId());



        city.setText(SetPreferences.getCity());

        set = findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SetPreferences setPreferences = new SetPreferences(getApplicationContext());

                String newcity = city.getText().toString();
                setPreferences.setCity(newcity);

                SetPreferences.setTempPref(getSelectedPref(tempPref));
                SetPreferences.setSpeedPref((getSelectedPref(speedPref)));
                SetPreferences.setPressurePref(getSelectedPref(pressurePref));
                SetPreferences.setPrecPref(getSelectedPref(precipitationPref));

                Intent main = new Intent(WeatherSettings.this, MainActivity.class);
                startActivity(main);

            }
        });

    }

    private String getSelectedPref(RadioGroup rdg) {

        if(rdg.getCheckedRadioButtonId() != -1) {
            int radioButtonID = rdg.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(radioButtonID);
            String selectedPref = (String) radioButton.getText();
            return  selectedPref;
        }
        return null;
    }


}
