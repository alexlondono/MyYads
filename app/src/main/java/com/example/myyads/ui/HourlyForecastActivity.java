package com.example.myyads.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myyads.R;
import com.example.myyads.adapters.HourAdapter;
import com.example.myyads.weather.Hour;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyForecastActivity extends AppCompatActivity
{
    private Hour[] mHours;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        ButterKnife.bind(this);  //Super important line :)

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(digital_msg_board.DAILY_FORECAST);
        //mDays = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mHours = Arrays.copyOf(parcelables, parcelables.length, Hour[].class);  //copy one array into the other

        HourAdapter adapter = new HourAdapter(mHours);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
    }
}
