package pl.perekmichal.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.perekmichal.models.WeatherModel;
import pl.perekmichal.models.database.DatabaseConnector;
import pl.perekmichal.models.database.dao.WeatherDao;
import pl.perekmichal.models.database.dao.impl.WeatherDaoImp;
import pl.perekmichal.models.services.WeatherData;
import pl.perekmichal.models.services.WeatherObserver;
import pl.perekmichal.models.services.WeatherService;


import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, WeatherObserver{

    @FXML
    Label labelWeather;

    @FXML
    TextField textField;

    @FXML // inversion of control, odwrócenie sterowania tzn ze nie trzeba tworzyc new MainControll
    Button buttonWeather;

    private WeatherDao weatherDao = new WeatherDaoImp();
    private WeatherService weatherService = WeatherService.getService();


    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        buttonWeather.setOnMouseClicked(e -> showWeather());

    }

    private void showWeather() {
        weatherService.makeCall(textField.getText());
    }

    @Override
    public void onWeatherUpdate(WeatherData data) {
        Platform.runLater(() -> labelWeather.setText("Temperatura: " + data.getTemp())); //Platform przenosi do wątku głównego bo nie moze byc watkiem pobocznym

//        labelWeather.setText("Temperatura: " + data.getTemp()); nie moze byc ocsługiwany wątek interfejsu innym wątkiem dlatego wyłączmy wyjątek powyższą metodą Platfor...

        WeatherModel model = new WeatherModel(0, data.getCity(), data.getTemp());
        weatherDao.saveWeather(model);
    }
}
