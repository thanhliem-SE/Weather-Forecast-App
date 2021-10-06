package liemnguyen.app.weather.presenters;

import android.util.Log;

import java.util.ArrayList;

import liemnguyen.app.weather.BuildConfig;
import liemnguyen.app.weather.listeners.DailyWeatherView;
import liemnguyen.app.weather.models.Daily;
import liemnguyen.app.weather.models.Hourly;
import liemnguyen.app.weather.models.WeatherListDetail;
import liemnguyen.app.weather.retrofit.ApiService;
import liemnguyen.app.weather.retrofit.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyWeatherPresenterImpl implements DailyWeatherPresenter {
    private DailyWeatherView dailyWeatherView;
    private DataService dataService;

    public DailyWeatherPresenterImpl(DailyWeatherView dailyWeatherView) {
        this.dailyWeatherView = dailyWeatherView;
        this.dataService = ApiService.getService();
    }

    @Override
    public void getDailyWeather(double lat, double lon) {
        final String[] exclude = new String[4];
        exclude[0] = "current";
        exclude[1] = "minutely";
        exclude[2] = "hourly";
        exclude[3] = "alerts";

        Call<WeatherListDetail> callback = dataService.getDailyWeatherDetail(lat, lon, BuildConfig.API_KEY, "metric", "en");
        callback.enqueue(new Callback<WeatherListDetail>() {
            @Override
            public void onResponse(Call<WeatherListDetail> call, Response<WeatherListDetail> response) {
                dailyWeatherView.getDataDailyWeather((ArrayList<Daily>) response.body().getDaily());
                dailyWeatherView.getDataHourlyWeather(new ArrayList<Hourly>(response.body().getHourly().subList(0,12)));
                Log.d("tag", "Success");
            }

            @Override
            public void onFailure(Call<WeatherListDetail> call, Throwable t) {
                t.printStackTrace();
                dailyWeatherView.onError(t.getMessage());
            }
        });
    }
}
