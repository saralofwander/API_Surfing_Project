package com.services;

import com.entities.Weather;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    public Weather getWeather(String forecastLocation) {

        Weather weather = new Weather();

        try {
            JSONObject forecast = retrieveWeatherForecastAsJson(forecastLocation);

            JSONObject location=forecast.getJSONObject("location");
            System.out.printf("Weather forecast for: %s%n", location.getString("address"));
            JSONArray values=location.getJSONArray("values");

            System.out.printf("Date\tMaxTemp\tMinTemp\tChangeofPrecip%n");

            JSONObject forecastValue = values.getJSONObject(0);
            String datetimeString=forecastValue.getString("datetimeStr");

            ZonedDateTime datetime=ZonedDateTime.parse( datetimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            double maxtemp=forecastValue.getDouble("maxt");
            double mintemp=forecastValue.getDouble("mint");
            double pop=forecastValue.getDouble("pop");

            weather.setTemperature(maxtemp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

    public static JSONObject retrieveWeatherForecastAsJson(String forecastLocation) throws Exception {

        URIBuilder builder = new URIBuilder("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/weatherdata/forecast?aggregateHours=24&combinationMethod=aggregate&contentType=csv&unitGroup=metric&locationMode=single&key=2BW59NNAU4YMSUEKMFEPDSJ3D&dataElements=default&locations=Gothenburg");

        builder.setParameter("aggregateHours", "24")
                .setParameter("contentType", "json")
                .setParameter("unitGroup", "metric")
                .setParameter("locationMode", "single")
                .setParameter("key", "1PYNQ6AWUDJE9AFERDCHJHSXK")
                .setParameter("locations", forecastLocation);


        HttpGet get = new HttpGet(builder.build());

        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse response = httpclient.execute(get);
        JSONObject jsonWeatherForecast = null;
        try {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
                return jsonWeatherForecast;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String rawResult=EntityUtils.toString(entity, Charset.forName("utf-8"));

                jsonWeatherForecast = new JSONObject(rawResult);

            }


        } finally {
            response.close();
        }

        if (jsonWeatherForecast==null) {
            System.out.printf("No weather forecast data returned%n");
            return jsonWeatherForecast;
        }
        JSONObject location=jsonWeatherForecast.getJSONObject("location");
        System.out.printf("Weather forecast for: %s%n", location.getString("address"));
        JSONArray values=location.getJSONArray("values");

        System.out.printf("Date\tMaxTemp\tMinTemp\tChangeofPrecip%n");
        for (int i = 0; i < values.length(); i++) {
            JSONObject forecastValue = values.getJSONObject(i);
            String datetimeString=forecastValue.getString("datetimeStr");

            ZonedDateTime datetime=ZonedDateTime.parse( datetimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

            double maxtemp=forecastValue.getDouble("maxt");
            double mintemp=forecastValue.getDouble("mint");
            double pop=forecastValue.getDouble("pop");
            System.out.printf("%s\t%.1f\t%.1f\t%.0f%n", datetime.format(DateTimeFormatter.ISO_LOCAL_DATE), maxtemp, mintemp, pop);
        }

        return jsonWeatherForecast;

    }


}
















