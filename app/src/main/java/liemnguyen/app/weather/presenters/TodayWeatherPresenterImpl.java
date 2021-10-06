package liemnguyen.app.weather.presenters;

import liemnguyen.app.weather.BuildConfig;
import liemnguyen.app.weather.listeners.TodayWeatherView;
import liemnguyen.app.weather.models.WeatherDetail;
import liemnguyen.app.weather.retrofit.ApiService;
import liemnguyen.app.weather.retrofit.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodayWeatherPresenterImpl implements TodayWeatherPresenter {
    private TodayWeatherView todayWeatherView;
    private DataService dataService;

    public TodayWeatherPresenterImpl(TodayWeatherView todayWeatherView) {
        this.todayWeatherView = todayWeatherView;
        this.dataService = ApiService.getService();
    }

    @Override
    public void loadDataByLocation(double lat, double lon) {
        Call<WeatherDetail> callback = dataService.getTodayWeatherByLocation(lat, lon, BuildConfig.API_KEY, "metric", "en");
        callback.enqueue(new Callback<WeatherDetail>() {
            @Override
            public void onResponse(Call<WeatherDetail> call, Response<WeatherDetail> response) {
                if(response.body() != null){
                    todayWeatherView.getWeatherDetailByCurrentLocation(response.body());
                }else{
                    todayWeatherView.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadDataByCity(String city) {
        Call<WeatherDetail> callback = dataService.getTodayWeatherByCity(city, BuildConfig.API_KEY, "metric", "en");
        callback.enqueue(new Callback<WeatherDetail>() {
            @Override
            public void onResponse(Call<WeatherDetail> call, Response<WeatherDetail> response) {
                if(response.body() != null){
                    todayWeatherView.getWeatherDetailByCity(response.body());
                }else{
                    todayWeatherView.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
