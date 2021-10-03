package liemnguyen.app.weather.retrofit;

public class ApiService {
    private static final String  BASE_URL = "https://api.openweathermap.org/data/2.5/";

    public static DataService getService(){
        return ApiClient.getClient(BASE_URL).create(DataService.class);
    }
}
