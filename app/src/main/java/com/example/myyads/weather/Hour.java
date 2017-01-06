package com.example.myyads.weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 1/3/2017.
 */

public class Hour implements Parcelable
{
    private long mTime;
    private String mSummary;
    private double mTemperature;
    private String mIcon;
    private String mTimezone;
    private double mHumidity;
    private double mPrecipChance;

    public Hour(){}

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

    public double getTemperature()
    {
        return mTemperature;
    }

    public void setTemperature(double temperature)
    {
        mTemperature = temperature;
    }

    public int getIconId()
    {
        return Forecast.getIconId(mIcon);
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

    public String getHour()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        Date date = new Date(mTime * 1000);

        return formatter.format(date);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }  //I'm not using this part of parcelable

    //Parcel to go out
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeLong(mTime);
        dest.writeString(mSummary);
        dest.writeDouble(mTemperature);
        dest.writeString(mIcon);
        dest.writeString(mTimezone);
        dest.writeDouble(mHumidity);
        dest.writeDouble(mPrecipChance);
    }

    //Parcel to go in
    private Hour(Parcel in)
    {
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperature = in.readDouble();
        mIcon = in.readString();
        mTimezone = in.readString();
        mHumidity = in.readDouble();
        mPrecipChance = in.readDouble();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>()
    {
        @Override
        public Hour createFromParcel(Parcel source)
        {
            return new Hour(source);
        }

        @Override
        public Hour[] newArray(int size)
        {
            return new Hour[size];
        }
    };
}
