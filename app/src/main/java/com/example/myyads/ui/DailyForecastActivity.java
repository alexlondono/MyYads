package com.example.myyads.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.os.Bundle;

import com.example.myyads.R;
import com.example.myyads.adapters.DayAdapter;
import com.example.myyads.weather.Day;

import java.util.Arrays;

public class DailyForecastActivity extends ListActivity
{
    public String[] daysOfTheWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
    private Day[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
/*      ----------     Android Adaptor commented so I can use my new Custom adaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daysOfTheWeek);
        setListAdapter(adapter);
*/
        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(digital_msg_board.DAILY_FORECAST);
        //mDays = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);  //copy one array into the other

        DayAdapter adapter = new DayAdapter(this, mDays);
        setListAdapter(adapter);
    }
}

