package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Utils.Utils;
import model.Place;
import model.Weather;

public class JSONWeatherParser {

    public static Weather getWeather(String data){
        Weather weather = new Weather();

        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place= new Place();
            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", coordObj));
            place.setLon(Utils.getFloat("lon", coordObj));

            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));
            place.setCountry(Utils.getString("country", sysObj));
            place.setCity(Utils.getString("name",jsonObject));
            place.setLastupdate(Utils.getInt("dt",jsonObject));
            weather.place = place;

            //Get weather info
            JSONArray  jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherID(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            //Get CurrentCondition info
            JSONObject mainObj = Utils.getObject("main", jsonObject);
            weather.currentCondition.setPressure(Utils.getFloat("pressure", mainObj));
            weather.currentCondition.setHumidity(Utils.getFloat("humidity", mainObj));
            //Get Temperature info
            weather.temperature.setTemp(Utils.getFloat("temp", mainObj));
            weather.temperature.setMintemp(Utils.getFloat("temp_min", mainObj));
            weather.temperature.setMaxtemp(Utils.getFloat("temp_max", mainObj));

            //Get Wind Info
            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setDeg(Utils.getFloat("deg", windObj));
            weather.wind.setSpeed(Utils.getFloat("speed",windObj));

            //Get Cloud Info
            JSONObject cloudObj= Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all", cloudObj));

            return  weather;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
