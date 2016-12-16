package com.example.myyads;

/**
 * Created by Alex on 12/16/2016.
 */

public class CurrentWeather
{
    private String mSummary;
    private String mIcon;
    private long mTime;
    private double mTemperature, mHumidity, mPrecipChance;

    public String getSummary()
    {
        return mSummary;
    }

    public void setSummary(String summary)
    {
        mSummary = summary;
    }

    public String getIcon()
    {
        return mIcon;
    }

    public void setIcon(String icon)
    {
        mIcon = icon;
    }

    public long getTime()
    {
        return mTime;
    }

    public void setTime(long time)
    {
        mTime = time;
    }

    public double getTemperature()
    {
        return mTemperature;
    }

    public void setTemperature(double temperature)
    {
        mTemperature = temperature;
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
