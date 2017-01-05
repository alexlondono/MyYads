package com.example.myyads.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myyads.R;
import com.example.myyads.weather.Day;

public class DayAdapter extends BaseAdapter
{
    private Context mContext;
    private Day[] mDays;

    public DayAdapter(Context c, Day[] d)
    {
        mContext = c;
        mDays = d;
    }

    @Override
    public int getCount()
    {
        return mDays.length;
    }

    @Override
    public Object getItem(int position)
    {
        return mDays[position];
    }

    @Override
    public long getItemId(int position)
    {
        return 0;  //We aren't going to use this.
    }

    private static class ViewHolder
    {
        ImageView iconImageView;
        TextView temperatureLabel;
        TextView dayLabel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.temperatureLabel);
            holder.dayLabel = (TextView) convertView.findViewById(R.id.dayNameLabel);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Day day = mDays[position];

        holder.iconImageView.setImageResource(day.getIconId());
        holder.temperatureLabel.setText(day.getTemperatureMax() + "");
        if(position == 0)
        {
            holder.dayLabel.setText("Today");
        }
        else
        {
            holder.dayLabel.setText(day.getDayOfTheWeek());
        }

        return convertView;
    }
}
