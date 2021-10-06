package liemnguyen.app.weather.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import liemnguyen.app.weather.R;
import liemnguyen.app.weather.view.fragments.DailyWeatherFragment;
import liemnguyen.app.weather.view.fragments.SettingFragment;
import liemnguyen.app.weather.view.fragments.TodayWeatherFragment;

public class MainActivity extends AppCompatActivity implements LocationListener{

    protected LocationManager locationManager;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.mn_today);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mn_today:
                        replaceFragment(new TodayWeatherFragment(latitude, longitude));
                        return true;
                    case R.id.mn_more_day:
                        replaceFragment(new DailyWeatherFragment(latitude, longitude));
                        return true;
                    case R.id.mn_setting:
                        replaceFragment(new SettingFragment(latitude, longitude));
                        return true;
                }
                return false;
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(latitude == 0.0 && longitude == 0.0)
                    handler.postDelayed(this, 500);
                else{
                    addFragment();
                    handler.removeCallbacks(this);
                }


            }
        }, 500);
    }


    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, new TodayWeatherFragment(latitude, longitude));
        fragmentTransaction.commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(location.getLatitude() != 0.0 && location.getLongitude() != 0.0){
        latitude = location.getLatitude();
        longitude = location.getLongitude();}
        else{
            // set Ha Noi is default Location
            latitude = 21.027763;
            longitude = 105.834160;
        }
//        Log.d("tag", "lat: " + latitude + ", lon: " + longitude);
        locationManager.removeUpdates(this);
    }
}