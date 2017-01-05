package com.example.myyads.weather;

/**
 * Created by Alex on 1/3/2017.
 */

public class Hour
{
    private long mTime;
    private String mSummary;
    private double mTemperature;
    private String mIcon;
    private String mTimezone;
    private double mHumidity;
    private double mPrecipChance;

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
}
