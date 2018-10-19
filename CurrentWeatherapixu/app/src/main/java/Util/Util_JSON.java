package Util;

import org.json.JSONException;
import org.json.JSONObject;

public class Util_JSON {

    public  static final String BASE_URL = "http://api.apixu.com/v1/current.json?key=5c7298da93e14b9c8d801143181110&q=";

    public static JSONObject getObject(String tagname, JSONObject jsonObject) throws JSONException {
        JSONObject jobj = jsonObject.getJSONObject(tagname);
        return jobj;
    }

    public static  String getString(String tagname, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(tagname);
    }

    public static Float getFloat(String tagname, JSONObject jsonObject) throws JSONException {
        return (float) jsonObject.getDouble(tagname);
    }

    public static Double getDouble(String tagname, JSONObject jsonObject) throws JSONException {
        return  jsonObject.getDouble(tagname);
    }

    public static int getInt(String tagname, JSONObject jsonObject) throws JSONException {
        return  jsonObject.getInt(tagname);
    }
}
