package com.example.myyads.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myyads.R;
import com.example.myyads.weather.Hour;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 1/5/2017.
 */

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder>
{
	//private Context mContext;
	private Hour[] mHours;

	@BindView(R.id.timeLabel_Hourly_li) TextView mTimeLabel;
	@BindView(R.id.summaryLabel_Hourly_li) TextView mSummaryLabel;
	@BindView(R.id.temperatureLabel_Hourly_li) TextView mTemperatureLabel;
	@BindView(R.id.iconImageView_Hourly_li) ImageView mIconImageView;
	@BindView(R.id.popLabelValue_Hourly_li) TextView mPopLabel;
	@BindView(R.id.humidityLabelValue_Hourly_li) TextView mHumidLabel;

	public HourAdapter(Hour[] h) //Constructor
	{
		mHours = h;
	}

	public class HourViewHolder extends RecyclerView.ViewHolder
	{
		/*  If I don't use butterknife I need to do it this old way :(
		public TextView mTimeLabel;
		public TextView mSummaryLabel;
		public TextView mTemperatureLabel;
		public ImageView mIconImageView;
		public TextView mPopLabel;
		public TextView mHumidLabel;
		*/
		public HourViewHolder(View itemView)
		{
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void bindHour(Hour hour)
		{
			mTimeLabel.setText(hour.getHour());
			mSummaryLabel.setText(hour.getSummary());
			mTemperatureLabel.setText(hour.getTemperature() + "");
			mIconImageView.setImageResource(hour.getIconId());
			mPopLabel.setText(hour.getPrecipChance() * 10 + "%");
			mHumidLabel.setText(hour.getHumidity() + "");
		}
	}

	@Override
	public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item,
																	 parent, false);
		HourViewHolder viewHolder = new HourViewHolder(view);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(HourViewHolder holder, int position)
	{
		holder.bindHour(mHours[position]);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public int getItemCount()
	{
		return mHours.length;
	}

/*
	private static class ViewHolder
    {
        ImageView iconImageView;
        TextView temperatureLabel;
        TextView timeLabel;
        TextView popLabel;
        TextView humLabel;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder;

            if(convertView == null)
            {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.hourly_list_item,
                null);
                holder = new ViewHolder();
                holder.iconImageView = (ImageView) convertView.findViewById(R.id
                .iconImageView_Hourly_li);
                holder.temperatureLabel = (TextView) convertView.findViewById(R.id
                .temperatureLabel_Hourly_li);
                holder.timeLabel = (TextView) convertView.findViewById(R.id.timeLabel_Hourly_li);
                holder.popLabel = (TextView) convertView.findViewById(R.id.popLabelValue_Hourly_li);
                holder.humLabel = (TextView) convertView.findViewById(R.id
                .humidityLabelValue_Hourly_li);

                convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            Hour hour = mHours[position];

            holder.iconImageView.setImageResource(hour.getIconId());
            holder.temperatureLabel.setText(hour.getTemperature() + "");
            if(position == 0)
            {
                holder.timeLabel.setText("Today");
            }
            else
            {
                holder.timeLabel.setText(hour.getTime() + "");
            }

            return convertView;
        }
        */
}
