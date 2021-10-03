package liemnguyen.app.weather.retrofit;


import java.util.List;

import liemnguyen.app.weather.BuildConfig;
import liemnguyen.app.weather.models.WeatherDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {

//    @Query("true")
//    @GET("weather?lat={lat}&lon={lon}&appid=" + BuildConfig.API_KEY + "&units=metric&lang=en")
//    Call<WeatherDetail> getTodayWeather(@Path("lat") double lat, @Path("lon") double lon);

    @GET("weather")
    Call<WeatherDetail> getTodayWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appid, @Query("units") String units, @Query("lang")String lang);
}
