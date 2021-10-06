package liemnguyen.app.weather.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import liemnguyen.app.weather.R;
import liemnguyen.app.weather.listeners.TodayWeatherView;
import liemnguyen.app.weather.models.WeatherDetail;
import liemnguyen.app.weather.presenters.TodayWeatherPresenter;
import liemnguyen.app.weather.presenters.TodayWeatherPresenterImpl;

public class TodayWeatherFragment extends Fragment implements TodayWeatherView {
    private View view;
    private TextView txtName, txtDescription, txtTemp, txtHumidity, txtCloudy, txtWindy;
    private EditText edtCity;
    private ImageButton btnSearch;

    private double latitude;
    private double longitude;

    private TodayWeatherPresenter todayWeatherPresenter;

    public TodayWeatherFragment() {

    }

    public TodayWeatherFragment(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_today_weather, container, false);

        txtName = view.findViewById(R.id.main_txtName);
        txtDescription = view.findViewById(R.id.main_txtDescription);
        txtTemp = view.findViewById(R.id.main_txtTemp);
        txtHumidity = view.findViewById(R.id.main_txtHumidity);
        txtCloudy = view.findViewById(R.id.main_txtCloudy);
        txtWindy = view.findViewById(R.id.main_txtWindy);
        edtCity = view.findViewById(R.id.main_edtCity);
        btnSearch = view.findViewById(R.id.main_btnSearch);

        todayWeatherPresenter = new TodayWeatherPresenterImpl(TodayWeatherFragment.this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edtCity.getText().toString();
                //Log.d("tag", city);
                if (!city.isEmpty()) {
                    todayWeatherPresenter.loadDataByCity(city);
                }
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (latitude == 0.0 && longitude == 0.0)
                    handler.postDelayed(this, 200);
                else {
//                    Log.d("tag", "lat: " + latitude + ", lon: " + longitude);
                    todayWeatherPresenter.loadDataByLocation(latitude, longitude);
                    handler.removeCallbacks(this);
                }
            }
        }, 200);

        return view;

    }

    @Override
    public void getWeatherDetailByCurrentLocation(WeatherDetail weatherDetail) {
        setGUI(weatherDetail);
    }

    private void setGUI(WeatherDetail weatherDetail) {
        //        Log.d("tag", weatherDetail.getName());
        edtCity.setText("");
        txtName.setText(weatherDetail.getName());
        txtDescription.setText(upperFirstLetter(weatherDetail.getWeather().get(0).getMain()));
        txtTemp.setText(Math.round(weatherDetail.getMain().getTemp()) + "°C");
        txtHumidity.setText(weatherDetail.getMain().getHumidity() + "%");
        txtCloudy.setText(weatherDetail.getClouds().getAll() + "%");
        txtWindy.setText(weatherDetail.getWind().getSpeed() + "m/s");
    }

    @Override
    public void getWeatherDetailByCity(WeatherDetail weatherDetail) {
        setGUI(weatherDetail);
    }

    @Override
    public void onError(String msg) {
        Log.d("tag", msg);
    }

    private String upperFirstLetter(String s) {
        Log.d("tag", s);
        String[] strings = s.split(" ");
        String rs = "";
        for (int i = 0; i < strings.length; i++){
            String str = strings[i];
            String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
            rs += cap + "\n";
        }
        return rs;
    }
}