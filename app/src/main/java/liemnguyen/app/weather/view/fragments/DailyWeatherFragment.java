package liemnguyen.app.weather.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import liemnguyen.app.weather.R;
import liemnguyen.app.weather.adapters.RecyclerAdapterDailyWeather;
import liemnguyen.app.weather.adapters.RecyclerAdapterHourlyWeather;
import liemnguyen.app.weather.listeners.DailyWeatherView;
import liemnguyen.app.weather.models.Daily;
import liemnguyen.app.weather.models.Hourly;
import liemnguyen.app.weather.presenters.DailyWeatherPresenter;
import liemnguyen.app.weather.presenters.DailyWeatherPresenterImpl;

public class DailyWeatherFragment extends Fragment implements DailyWeatherView {
    private double latitude;
    private double longitude;

    private RecyclerView recyclerViewDaily;
    private RecyclerView recyclerViewHourly;
    private TextView txtDateNow;

    public DailyWeatherFragment() {

    }

    public DailyWeatherFragment(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_daily_weather, container, false);
        recyclerViewDaily = view.findViewById(R.id.daily_recyclerViewDaily);
        recyclerViewHourly = view.findViewById(R.id.daily_recyclerViewToday);
        txtDateNow = view.findViewById(R.id.daily_txtDateNow);

        Date dateNow = new Date();
        txtDateNow.setText(new SimpleDateFormat("MMMM dd, yyyy").format(dateNow));

        DailyWeatherPresenter dailyWeatherPresenter = new DailyWeatherPresenterImpl(this);
        dailyWeatherPresenter.getDailyWeather(latitude, longitude);

        return view;
    }

    @Override
    public void getDataDailyWeather(ArrayList<Daily> dailyArrayList) {
        RecyclerAdapterDailyWeather adapterDailyWeather = new RecyclerAdapterDailyWeather(getContext(), dailyArrayList);
        recyclerViewDaily.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDaily.setAdapter(adapterDailyWeather);
    }

    @Override
    public void getDataHourlyWeather(ArrayList<Hourly> hourlyArrayList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclerAdapterHourlyWeather adapterHourlyWeather = new RecyclerAdapterHourlyWeather(getContext(), hourlyArrayList);
        recyclerViewHourly.setLayoutManager(linearLayoutManager);
        recyclerViewHourly.setAdapter(adapterHourlyWeather);
    }
    @Override
    public void onError(String msg) {
        Log.d("tag", msg);
    }
}