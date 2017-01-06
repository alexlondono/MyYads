package com.example.myyads.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Alex on 1/3/2017.
 */

public class Day implements Parcelable
{
    private long mTime;
    private String mSummary;
    private double mTemperatureMax;
    private double mHumidity;
    private double mPrecipChance;
    private String mIcon;
    private String mTimezone;

    public Day(){}

    public long getTime()
    {
        return mTime;
    }

    public void setTime(long time)
    {
        mTime = time;
    }

    public String getSummary()
    {
        return mSummary;
    }

    public void setSummary(String summary)
    {
        mSummary = summary;
    }

    public int getTemperatureMax()
    {
        return (int)Math.round(mTemperatureMax);
    }

    public void setTemperatureMax(double temperatureMax)
    {
        mTemperatureMax = temperatureMax;
    }

    public String getIcon()
    {
        return mIcon;
    }

    public void setIcon(String icon)
    {
        mIcon = icon;
    }

    public String getTimezone()
    {
        return mTimezone;
    }

    public void setTimezone(String timezone)
    {
        mTimezone = timezone;
    }

    public double getHumidity()
    {
        return mHumidity;
    }

    public void setHumidity(double humidity)
    {
        mHumidity = humidity;
    }

    public double getPrecipChance()
    {
        return mPrecipChance;
    }

    public void setPrecipChance(double precipChance)
    {
        mPrecipChance = precipChance;
    }

    public int getIconId()
    {
        return Forecast.getIconId(mIcon);
    }

    public String getDayOfTheWeek()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        Date dateTime = new Date(mTime * 1000);

        return formatter.format(dateTime);
    }

    //Parcel to go out
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperatureMax);
        dest.writeString(mIcon);
        dest.writeString(mTimezone);
        dest.writeDouble(mHumidity);
        dest.writeDouble(mPrecipChance);
    }

    //Parcel to go in
    private Day(Parcel in)
    {
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperatureMax = in.readDouble();
        mIcon = in.readString();
        mTimezone = in.readString();
        mHumidity = in.readDouble();
        mPrecipChance = in.readDouble();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>()
    {
        @Override
        public Day createFromParcel(Parcel source)
        {
            return new Day(source);
        }

        @Override
        public Day[] newArray(int size)
        {
            return new Day[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }
}
