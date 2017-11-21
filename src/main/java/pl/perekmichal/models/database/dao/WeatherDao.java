package pl.perekmichal.models.database.dao;

import pl.perekmichal.models.WeatherModel;

import java.util.List;

public interface WeatherDao {
    void saveWeather (WeatherModel model);
    List<WeatherModel> laodWeather(String city);
    List<WeatherModel> laodWeather();
    List<WeatherModel> laodWeather(float temp);

}
// DAO DATA ACCESS OBJECT // POMAGA UTWORZYC POLIMORFIZM // TRZYMAMY TUTAJ METODY DOSTĘPOWE
//