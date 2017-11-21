package pl.perekmichal.models.services;

public class WeatherData {
    private float temp;
    private int humidity;
    private int pressure;
    private int clouds;
    private  String city;

    public WeatherData() {

    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
