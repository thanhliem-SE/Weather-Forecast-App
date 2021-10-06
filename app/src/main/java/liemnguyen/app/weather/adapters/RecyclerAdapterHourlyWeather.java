package liemnguyen.app.weather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import liemnguyen.app.weather.R;
import liemnguyen.app.weather.models.Hourly;

public class RecyclerAdapterHourlyWeather extends RecyclerView.Adapter<RecyclerAdapterHourlyWeather.ViewHolder> {
    private Context context;
    private ArrayList<Hourly> hourlyArrayList;

    public RecyclerAdapterHourlyWeather(Context context, ArrayList<Hourly> hourlyArrayList) {
        this.context = context;
        this.hourlyArrayList = hourlyArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_today_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hourly hourly = hourlyArrayList.get(position);
        Date date = new Date(hourly.getDt() * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        holder.txtTime.setText(simpleDateFormat.format(date));
        holder.txtTemp.setText(Math.round(hourly.getTemp()) + "°C");
        String imgUrl = "https://openweathermap.org/img/w/" + hourly.getWeather().get(0).getIcon() + ".png";
        Picasso.get().load(imgUrl).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return hourlyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTime, txtTemp;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTime = itemView.findViewById(R.id.toDayWeather_time);
            txtTemp = itemView.findViewById(R.id.toDayWeather_temp);
            img = itemView.findViewById(R.id.toDayWeather_imgView);
        }
    }
}
