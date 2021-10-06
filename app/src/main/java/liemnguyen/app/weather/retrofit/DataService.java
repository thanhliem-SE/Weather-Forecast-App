package liemnguyen.app.weather.retrofit;


import liemnguyen.app.weather.models.WeatherDetail;
import liemnguyen.app.weather.models.WeatherListDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {

    @GET("weather")
    Call<WeatherDetail> getTodayWeatherByLocation(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appid, @Query("units") String units, @Query("lang")String lang);

    @GET("weather")
    Call<WeatherDetail> getTodayWeatherByCity(@Query("q") String city, @Query("appid") String appid, @Query("units") String units, @Query("lang")String lang);

    @GET("onecall")
    Call<WeatherListDetail> getDailyWeatherDetail(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appid, @Query("units") String units, @Query("lang")String lang);
}
