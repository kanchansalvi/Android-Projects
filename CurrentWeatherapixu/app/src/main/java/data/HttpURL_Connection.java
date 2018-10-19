package data;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Util_JSON;

public class HttpURL_Connection {

    public String getWeatherData(String place)  {


        try {
            HttpURLConnection connection = null;
            InputStream inputStream = null;

            connection = (HttpURLConnection) (new URL(Util_JSON.BASE_URL + place)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();


            StringBuffer stringBuffer=new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line=null;

            while( (line = bufferedReader.readLine() )!= null){
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            bufferedReader.close();
            connection.disconnect();

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
