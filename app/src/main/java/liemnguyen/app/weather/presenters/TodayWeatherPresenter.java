package liemnguyen.app.weather.presenters;

public interface TodayWeatherPresenter {
    public void loadDataByLocation(double lat, double lon);
    public void loadDataByCity(String city);
}
