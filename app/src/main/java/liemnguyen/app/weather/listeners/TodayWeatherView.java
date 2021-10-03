package liemnguyen.app.weather.listeners;

import liemnguyen.app.weather.models.WeatherDetail;

public interface TodayWeatherView {
    public void onComplete(WeatherDetail weatherDetail);
    public void onError(String msg);
}
