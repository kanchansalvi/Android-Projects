package Model;

import android.util.Log;

public class Weather {

    private String cityName;
    private String region;
    private String country;
    private float lat;
    private float lon;
    private String timeZone;
    private String localTime;
    private int localTime_epoch;

    private int lastUpdate_epoch;
    private String lastUpdte;
    private float temp_C;
    private float temp_F;

    private String condition;
    public String icon;

    private float wind_kph;
    private float wind_mph;

    private int windDegree;
    private String windDirection;

    private float pressure_mb;
    private float pressure_in;

    private float precipitation_mm;
    private float precipitation_in;
    private  int humidity;
    private int cloud;

    public String getCityName() { return cityName;    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public int getLocalTime_epoch() {
        return localTime_epoch;
    }

    public void setLocalTime_epoch(int localTime_epoch) {
        this.localTime_epoch = localTime_epoch;
    }

    public int getLastUpdate_epoch() {
        return lastUpdate_epoch;
    }

    public void setLastUpdate_epoch(int lastUpdate_epoch) {
        this.lastUpdate_epoch = lastUpdate_epoch;
    }

    public String getLastUpdte() {
        return lastUpdte;
    }

    public void setLastUpdte(String lastUpdte) {
        this.lastUpdte = lastUpdte;
    }

    public float getTemp_C() {
        return temp_C;
    }
    public float getTemp_F() {
        return temp_F;
    }

    public void setTemp_C(float temp_C) {
        this.temp_C = temp_C;
    }
    public void setTemp_F(float temp_F) {
        this.temp_F = temp_F;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getWind_kph() {
        return wind_kph;
    }
    public float getWind_mph() {
        return wind_mph;
    }

    public void setWind_kph(float wind_kph) {
        this.wind_kph = wind_kph;
    }
    public void setWind_mph(float wind_mph) {
        this.wind_mph = wind_mph;
    }

    public int getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(int windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public float getPressure_mb() { return pressure_mb; }
    public float getPressure_in() {
        return pressure_in;
    }

    public void setPressure_mb(float pressure_mb) {
        this.pressure_mb = pressure_mb;
    }
    public void setPressure_in(float pressure_in) {
        this.pressure_in = pressure_in;
    }

    public float getPrecipitation_mm() {
        return precipitation_mm;
    }
    public float getPrecipitation_in() {
        return precipitation_in;
    }

    public void setPrecipitation_mm(float precipitation_mm) {
        this.precipitation_mm = precipitation_mm;
    }
    public void setPrecipitation_in(float precipitation_in) {
        this.precipitation_in = precipitation_in;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }
}
