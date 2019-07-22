package com.example.week4_weatherapi;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class GetMethod {


    public static String connectAPi(String targetUrl)
    {
        URL url;
        InputStream inputStream;
        HttpURLConnection httpConnection = null;
        StringBuffer response = new StringBuffer();

        try {
            //Create connection
            url = new URL(targetUrl);
            httpConnection = (HttpURLConnection)url.openConnection();

            /* connection.setRequestProperty("content-type", "application/json;  charset=utf-8");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(false);*/


            int status = httpConnection.getResponseCode();


            if (status != HttpURLConnection.HTTP_OK){

                inputStream = httpConnection.getErrorStream();
            }

            else{

                inputStream = httpConnection.getInputStream();
            }

            BufferedReader stream = new BufferedReader(new InputStreamReader(inputStream));
            String line = stream .readLine();

            while(( stream.readLine()) != null) {

                response.append(line);
                response.append('\r');
            }
            stream.close();


        }
        catch (Exception e) {
            Log.d("ThisError", "LOOK!");
            Log.e("Tag", "Find the error");
        }
        return response.toString();
    }

    public static String setWeatherIcon(int primaryID, long sunrise, long sunset){

        int ID = primaryID / 100;
        String weatherIcon = "";

        if(primaryID == 800){

            long currentTime = new Date().getTime();

            if(currentTime >= sunrise && currentTime < sunset){

                weatherIcon = "&#xf00d;";
            }
            else{

                weatherIcon = "&#xf02e";
            }
        }
        else{

            switch (ID){

                case 2: weatherIcon = "&#xf01e;";
                    break;
                case 3 : weatherIcon = "&#xf01c;";
                    break;
                case 7 : weatherIcon = "&#xf014;";
                    break;
                case 8 : weatherIcon = "&#xf013;";
                    break;
                case 6 : weatherIcon = "&#xf01b;";
                    break;
                case 5 : weatherIcon = "&#xf019;";
                    break;
            }
        }
        return weatherIcon;
    }

}
