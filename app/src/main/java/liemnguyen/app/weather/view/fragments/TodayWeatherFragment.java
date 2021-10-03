package liemnguyen.app.weather.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import liemnguyen.app.weather.R;
import liemnguyen.app.weather.listeners.TodayWeatherView;
import liemnguyen.app.weather.models.WeatherDetail;
import liemnguyen.app.weather.presenters.TodayWeatherPresenter;
import liemnguyen.app.weather.presenters.TodayWeatherPresenterImpl;

public class TodayWeatherFragment extends Fragment implements TodayWeatherView {
    private View view;

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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (latitude == 0.0 && longitude == 0.0)
                    handler.postDelayed(this, 200);
                else {
        Log.d("tag", "lat: " + latitude + ", lon: " + longitude);
                    todayWeatherPresenter = new TodayWeatherPresenterImpl(TodayWeatherFragment.this);
                    todayWeatherPresenter.loadData(latitude, longitude);
                    handler.removeCallbacks(this);
                }
            }
        }, 200);

        return view;

    }

    @Override
    public void onComplete(WeatherDetail weatherDetail) {
        Log.d("tag", weatherDetail.getName());
    }

    @Override
    public void onError(String msg) {

    }
}