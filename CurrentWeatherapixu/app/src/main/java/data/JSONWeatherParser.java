package data;

import org.json.JSONException;
import org.json.JSONObject;

import Model.Weather;
import Util.Util_JSON;

public class JSONWeatherParser {

    public static Weather getWeather(String data) {

        Weather weather = new Weather();
        try {
            JSONObject jsonObject = new JSONObject(data);

        JSONObject  location = Util_JSON.getObject("location", jsonObject);
        weather.setCityName(Util_JSON.getString("name", location));
        weather.setRegion(Util_JSON.getString("region", location));
        weather.setCountry(Util_JSON.getString("country", location));
        weather.setLat(Util_JSON.getFloat("lat", location));
        weather.setLon(Util_JSON.getFloat("lon", location));
        weather.setLocalTime_epoch(Util_JSON.getInt("localtime_epoch", location));
        weather.setLocalTime(Util_JSON.getString("localtime", location));

        JSONObject current = Util_JSON.getObject("current", jsonObject);
        weather.setLastUpdate_epoch(Util_JSON.getInt("last_updated_epoch", current));
        weather.setLastUpdte(Util_JSON.getString("last_updated", current));
        weather.setTemp_C(Util_JSON.getFloat("temp_c", current));
        weather.setTemp_F(Util_JSON.getFloat("temp_f", current));

            JSONObject condition = Util_JSON.getObject("condition", current);
            weather.setCondition(Util_JSON.getString("text", condition));
            weather.setIcon(Util_JSON.getString("icon", condition));

        weather.setWind_kph(Util_JSON.getFloat("wind_kph", current));
        weather.setWind_mph(Util_JSON.getFloat("wind_mph", current));
        weather.setWindDegree(Util_JSON.getInt("wind_degree", current));
        weather.setWindDirection(Util_JSON.getString("wind_dir", current));
        weather.setPressure_mb(Util_JSON.getFloat("pressure_mb", current));
        weather.setPressure_in(Util_JSON.getFloat("pressure_in", current));
        weather.setPrecipitation_mm(Util_JSON.getFloat("precip_mm", current));
        weather.setPrecipitation_in(Util_JSON.getFloat("precip_in", current));
        weather.setHumidity(Util_JSON.getInt("humidity", current));
        weather.setCloud(Util_JSON.getInt("cloud", current));

        return weather;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
