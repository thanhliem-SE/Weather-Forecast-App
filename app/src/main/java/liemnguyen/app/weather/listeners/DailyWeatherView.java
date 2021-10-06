package liemnguyen.app.weather.listeners;

import java.util.ArrayList;

import liemnguyen.app.weather.models.Daily;
import liemnguyen.app.weather.models.Hourly;

public interface DailyWeatherView {
    public void getDataDailyWeather(ArrayList<Daily> dailyArrayList);
    public void getDataHourlyWeather(ArrayList<Hourly> hourlyArrayList);
    public void onError(String msg);
}
