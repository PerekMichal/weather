package pl.perekmichal.models.database.dao.impl;    // DZIEDZICZY Z INTERFACE WEATHERDAO
import pl.perekmichal.models.WeatherModel;
import pl.perekmichal.models.database.DatabaseConnector;
import pl.perekmichal.models.database.dao.WeatherDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WeatherDaoImp implements WeatherDao {

    private DatabaseConnector databaseConnector = DatabaseConnector.getIstance(); //TWORZY POLACZENIE Z BAZĄ

    @Override
    public void saveWeather(WeatherModel model) {
        PreparedStatement statement = databaseConnector.createStatment("INSERT INTO weather (city, temp) VALUES(?, ?);");
        try {
            statement.setString(1, model.getCity());
            statement.setFloat(2, model.getTemp());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
        public List<WeatherModel> laodWeather(String city) {
        List<WeatherModel> weatherModels = new ArrayList<WeatherModel>();

        PreparedStatement statement = databaseConnector.createStatment("SELECT * FROM weather " +
                "WHERE cityname = ?");
        WeatherModel model;
        try {
            statement.setString(1, city);        //execute - nic nie zwraca a executeQuery - zwraca result seta
            ResultSet set = statement.executeQuery();       //ResultSet - sprawdzić w dokumentacji.
            while (set.next()) {
                model = new WeatherModel(set.getInt("id"), set.getString("city"), set.getFloat("temp"));
                weatherModels.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }

    @Override
    public List<WeatherModel> laodWeather() {
        List<WeatherModel> weatherModels = new ArrayList<WeatherModel>();

        PreparedStatement statement = databaseConnector.createStatment("SELECT * FROM weather ");
        WeatherModel model;
        try {
            ResultSet set = statement.executeQuery();       //ResultSet - sprawdzić w dokumentacji.// początkowo zaczyna od -1 na indexie//
            while (set.next()) {
                model = new WeatherModel(set.getInt("id"), set.getString("city"), set.getFloat("temp"));
                weatherModels.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weatherModels;
    }

    @Override
        public List<WeatherModel> laodWeather(float temp) {
        List<WeatherModel> weatherModels = new ArrayList<WeatherModel>();

        PreparedStatement statement = databaseConnector.createStatment("SELECT * FROM weather " +
                "WHERE cityname = ?");
        WeatherModel model;
        try {
            statement.setFloat(1, temp);        //execute - nic nie zwraca a executeQuery - zwraca result seta
            ResultSet set = statement.executeQuery();       //ResultSet - sprawdzić w dokumentacji.
            while (set.next()) {
                model = new WeatherModel(set.getInt("id"), set.getString("city"), set.getFloat("temp"));
                weatherModels.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}


    // ZROBIC LOADWEATHER
    // ZAPYTANIA TYPU SELECT
    // ZAPROJEKTUJ WIDOK: NA SAMEJ GORZE LEJBELKA "WEATHR CONTROLER' GDZIE USER WPISUJE MIASTO I POD TYM PRZYCISK

    //przygotowac metode usuwajacą wszstkie wiersz danego miasta