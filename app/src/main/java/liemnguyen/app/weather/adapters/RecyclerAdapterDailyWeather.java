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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import liemnguyen.app.weather.R;
import liemnguyen.app.weather.models.Daily;
import liemnguyen.app.weather.models.Weather;
import liemnguyen.app.weather.models.WeatherListDetail;

public class RecyclerAdapterDailyWeather extends RecyclerView.Adapter<RecyclerAdapterDailyWeather.ViewHolder> {
    private Context context;
    private ArrayList<Daily> dailyArrayList;

    public RecyclerAdapterDailyWeather(Context context,ArrayList<Daily> dailyArrayList) {
        this.context = context;
        this.dailyArrayList = dailyArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Daily daily = dailyArrayList.get(position);
        Date date = new Date(daily.getDt() * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE \n MMMM, dd");
        holder.txtDaily.setText(simpleDateFormat.format(date));

        holder.txtTemp.setText(daily.getTemp().getDay() + "°C");

        String imgUrl = "https://openweathermap.org/img/w/" + daily.getWeather().get(0).getIcon() + ".png";
        Picasso.get().load(imgUrl).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return dailyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDaily, txtTemp;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDaily = itemView.findViewById(R.id.itemDaily_txtDateNow);
            txtTemp = itemView.findViewById(R.id.itemDaily_temp);
            img = itemView.findViewById(R.id.itemDaily_img);
        }
    }
}
