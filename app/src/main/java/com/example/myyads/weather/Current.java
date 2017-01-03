package com.example.myyads.weather;

import com.example.myyads.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Alex on 12/16/2016.
 */

public class Current
{
    private String mSummary;
    private String mTimeZone;
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

    public String getTimeZone()
    {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone)
    {
        mTimeZone = timeZone;
    }

    public void setIcon(String icon)
    {
        mIcon = icon;
    }

    public int getIconId()
    {
        int iconId = R.drawable.clear_day;

        if (mIcon.equals("clear-day")) {
            iconId =R.drawable.clear_day;
        }
        else if (mIcon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (mIcon.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (mIcon.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (mIcon.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (mIcon.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (mIcon.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (mIcon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (mIcon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (mIcon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }

        return iconId;
    }

    public long getTime()
    {
        return mTime;
    }

    public String getFormattedTime(/*String Template*/) //ex template = "h:mm a"  or "E M d, y"
    {
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM. d, y");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(getTime() * 1000);
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    public void setTime(long time)
    {
        mTime = time;
    }

    public int getTemperature()
    {
        return (int)Math.round(mTemperature);
    }

    public void setTemperature(double temperature)
    {
        mTemperature = temperature;
    }

    public double getHumidity()
    {
        return mHumidity;
    }

    public void setHumidity(double humidity){ mHumidity = humidity; }

    public int getPrecipChance()
    {
        return (int)Math.round(mPrecipChance * 100);
    }

    public void setPrecipChance(double precipChance){ mPrecipChance = precipChance; }
}