package data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CityPreference {

    SharedPreferences prefs ;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public String getCity(){
        return prefs.getString("city", "Spokane,US");
    }

    public  void setCity(String city){
        prefs.edit().putString("city", city).apply();

    }
}
