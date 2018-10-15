package Utils;

/*  anoyher API for weather
 * apixu site :
 * Current Weather  : "http://api.apixu.com/v1/current.json?key=5c7298da93e14b9c8d801143181110&q=Paris"
 * Forecast Weather : "http://api.apixu.com/v1/forecast.json?key=5c7298da93e14b9c8d801143181110&q=Paris"
 */


import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

    public static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL = "https://cdn.apixu.com/weather/64x64/night/113.png";

    public static JSONObject getObject(String tagname, JSONObject jsonObject) throws JSONException {
        JSONObject jObj = jsonObject.getJSONObject(tagname);
        return jObj;
    }

    public  static String getString(String tagname, JSONObject jsonObject)throws JSONException {
        return jsonObject.getString(tagname);
    }

    public  static float getFloat(String tagname, JSONObject jsonObject)throws JSONException {
        return (float) jsonObject.getDouble(tagname);
    }

    public  static double getDouble(String tagname, JSONObject jsonObject)throws JSONException {
        return (float) jsonObject.getDouble(tagname);
    }

    public  static int getInt(String tagname, JSONObject jsonObject)throws JSONException {
        return jsonObject.getInt(tagname);
    }


}
