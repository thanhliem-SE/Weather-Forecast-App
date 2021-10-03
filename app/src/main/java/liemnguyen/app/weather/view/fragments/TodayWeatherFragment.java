package liemnguyen.app.weather.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import liemnguyen.app.weather.R;

public class TodayWeatherFragment extends Fragment {

    public TodayWeatherFragment() {

    }

    public TodayWeatherFragment(double latitude, double longitude) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today_weather, container, false);
    }
}