package com.example.myyads;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;                                     //ButterKnife way to add a textview to a var.
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class digital_msg_board extends AppCompatActivity
{
    public static final String TAG = digital_msg_board.class.getSimpleName();
    private CurrentWeather mCurrentWeather;
    //private ImageView mIconImageView;

//-------------------------------  Butterknife Start ---------------------------------------
    private TextView mTempLabel;                                  //Old way to add a textview to a var. Before using butterKnife  <--- A.1
    @BindView(R.id.value_of_humidityLabel) TextView mHumidityValue;        //ButterKnife way to add a textview to a var.
    @BindView(R.id.value_of_popLabel) TextView mPrecipValue;               //ButterKnife way to add a textview to a var.
    @BindView(R.id.image_view_weather_icon) ImageView mIconImageView;
    @BindView(R.id.dateLabel) TextView mDateLabel;
    //@BindView(R.id.summaryLabel) TextView mSummaryLabel;
    //@BindView(R.id.iconImageView) ImageView mIconImageView;
//-------------------------------  Butterknife END ---------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)      //Not part of butterKnife
    {                                                       //Not part of butterKnife
        super.onCreate(savedInstanceState);                 //Not part of butterKnife
        setContentView(R.layout.root_layout);               //Not part of butterKnife
        ButterKnife.bind(this);                                            //ButterKnife way to add a textview to a var.

        mTempLabel = (TextView)findViewById(R.id.tempLabel);    //Old way to add a textview to a var. Before using butterKnife    <--- A.2
//----------------------------  Forecast Region START  -----------------------------
        String apiKey = "cb96a98e0afb5b016f7bb8841a06332d";
        double latitude = 43.8054259, longitude=-79.5540045;
        String forecastUrl = "https://api.darksky.net/forecast/" + apiKey + "/"+latitude + "," + longitude;

        if(isNetworkAvailable())
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(forecastUrl).build();

            Call call = client.newCall(request);
            call.enqueue(new Callback()
            {
                @Override
                public void onFailure(Call call, IOException e)
                {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    try
                    {
                        String jsonData = response.body().string();  //store all weather deta in here

                        Log.v(TAG, jsonData);
                        if(response.isSuccessful())
                        {
                            mCurrentWeather = getCurrentDetails(jsonData);
                            runOnUiThread(new Runnable()       //Important to make this code run on the main thread to be able to alter the layout
                            {
                                @Override
                                public void run()
                                {
                                    updateDisplay();
                                }
                            });
                        }
                        else
                            alertUserAboutError();
                        //Response response = call.execute(); //Used only in sychronous thread request (can go on main thread) bad!
                    }
                    catch(IOException e)
                    {
                        Log.e(TAG, "Exception caught on okHttp Call Response: ", e);
                        //e.printStackTrace();
                    }
                    catch(JSONException e)
                    {
                        Log.e(TAG, "Exception caught on JSON Error: ", e);
                    }
                }
            });
        }
        else
        {
            Toast.makeText(this, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }
        Log.d(TAG,"Main UI code is running!");
//----------------------------  Forecast Region END  --------------------------------


        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // -----------------------------------------------------------------------
        //setContentView(R.layout.root_layout);

        //web view Main Ad
        //setContentView(R.layout.root_layout);

        WebView webView = (WebView) findViewById(R.id.main_ad);
        //webView.loadUrl("https://docs.google.com/presentation/d/1QyNNURCVBme50SAuIceq3sh7Ky74LuWNeEM8B910aC4/pub?delayms=4500&loop=true&start=true&slide=id.p");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://docs.google.com/presentation/d/1QyNNURCVBme50SAuIceq3sh7Ky74LuWNeEM8B910aC4/embed?start=true&loop=true&delayms=2000");
        //webView.loadUrl("");

    }

    private void updateDisplay()
    {
        mTempLabel.setText(mCurrentWeather.getTemperature() + "");
        mHumidityValue.setText(mCurrentWeather.getHumidity() + "");
        mPrecipValue.setText(mCurrentWeather.getPrecipChance() + "%");
        mDateLabel.setText(mCurrentWeather.getFormattedTime() +"");

        Drawable drawable = ContextCompat.getDrawable(this, mCurrentWeather.getIconId());
        mIconImageView.setImageDrawable(drawable);
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException
    {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.i(TAG, "From Json: "+ timezone);

        JSONObject currently = forecast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTimeZone(timezone);

        Log.d(TAG,currentWeather.getFormattedTime());

        return currentWeather;
    }

    private boolean isNetworkAvailable()
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected())
        {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError()
    {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_dialog");
    }
}
