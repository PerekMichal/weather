package pl.perekmichal.models.services;

public interface WeatherObserver {
    void onWeatherUpdate(WeatherData data);
}
