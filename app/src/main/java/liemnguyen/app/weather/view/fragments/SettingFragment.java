package liemnguyen.app.weather.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import liemnguyen.app.weather.R;

public class SettingFragment extends Fragment {

    public SettingFragment() {

    }

    public SettingFragment(double latitude, double longitude) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }
}