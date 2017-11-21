package pl.perekmichal.models.services;


import org.json.JSONObject;
import pl.perekmichal.models.utils.Config;
import pl.perekmichal.models.utils.HttpUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();

    public static WeatherService getService() {
        return ourInstance;
    }

    private List<WeatherObserver> observers;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private WeatherService() {
        observers = new ArrayList<>();
    }

    public void registerObserver(WeatherObserver observer){
        observers.add(observer);
    }

    private void notifyObservers(WeatherData data) {
        for (WeatherObserver observer : observers) {
            observer.onWeatherUpdate(data);
        }
    }

    public void makeCall (String city) {
        executorService.execute(()-> {
            parseJsonData(HttpUtils.makeHttpRequest(Config.APP_URL + city + "&appid=" + Config.APP_ID));//makeHttpRequest - zwraca tekst html
        });
    }

    private void parseJsonData(String text){

        JSONObject root = new JSONObject(text);
        JSONObject main = root.getJSONObject("main");
//        int temp = main.getInt("temp");
//        System.out.println("temperatura to: " + temp);

        int temp = main.getInt("temp");
        String name = root.getString("name");

        WeatherData data = new WeatherData();
        data.setTemp(temp);
        data.setCity(name);

        notifyObservers(data);

        //zachmurzenie
        // wyswietlic wszystkie pogody bez wzgledu na miasto trzeba bedzie dorobic metodoe w weather dao

    }
}
