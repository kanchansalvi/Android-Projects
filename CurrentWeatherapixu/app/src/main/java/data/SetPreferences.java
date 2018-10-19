package data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SetPreferences {

    static SharedPreferences prefs;


        public SetPreferences(Context context) {
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
   }

    public static String getCity(){
        return prefs.getString("city", "Paris");
    }
    public static void setCity(String city){
            if(city != null) {
                prefs.edit().putString("city", city).apply();
            }
    }

    public static String getTempPref(){
        return prefs.getString("tempUnit", "° C");
    }
    public static void setTempPref(String tempUnit){
            if(tempUnit != null) {
                prefs.edit().putString("tempUnit", tempUnit).apply();
            }
    }

    public static String getSpeedPref(){
        return prefs.getString("speedUnit", "kph");
    }
    public static void setSpeedPref(String speedUnit){
            if(speedUnit != null) {
                prefs.edit().putString("speedUnit", speedUnit).apply();
            }
    }

    public static String getPressurePref(){
        return prefs.getString("pressureUnit", "mb");
    }
    public static void setPressurePref(String pressureUnit){
            if(pressureUnit != null) {
                prefs.edit().putString("pressureUnit", pressureUnit).apply();
            }
    }

    public static String getPrecPref(){
        return prefs.getString("precUnit", "in");
    }
    public static  void setPrecPref(String precUnit){
            if(precUnit != null) {
                prefs.edit().putString("precUnit", precUnit).apply();
            }
    }
}
