package liemnguyen.app.weather.presenters;

import java.util.ArrayList;

import liemnguyen.app.weather.models.Daily;

public interface DailyWeatherPresenter {
    public void getDailyWeather(double lat, double lon);

}
