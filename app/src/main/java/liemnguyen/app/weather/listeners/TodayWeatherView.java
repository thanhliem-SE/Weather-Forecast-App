package liemnguyen.app.weather.listeners;

import liemnguyen.app.weather.models.WeatherDetail;

public interface TodayWeatherView {
    public void getWeatherDetailByCurrentLocation(WeatherDetail weatherDetail);
    public void getWeatherDetailByCity(WeatherDetail weatherDetail);
    public void onError(String msg);
}
