package com.example.myyads;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class digital_msg_board extends AppCompatActivity
{
    public static final String TAG = digital_msg_board.class.getSimpleName();
    private CurrentWeather mCurrentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//----------------------------  Forecast Region START  -----------------------------
        String apiKey = "cb96a98e0afb5b016f7bb8841a06332d";
        double latitude = 37.8267, longitude=-122.4233;
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

                        Log.v(TAG, response.body().string());
                        if(response.isSuccessful())
                        {
                            mCurrentWeather = getCurrentDetails(jsonData);
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
        setContentView(R.layout.root_layout);

        WebView webView = (WebView) findViewById(R.id.main_ad);
        //webView.loadUrl("https://docs.google.com/presentation/d/1QyNNURCVBme50SAuIceq3sh7Ky74LuWNeEM8B910aC4/embed?start=true&loop=true&delayms=3000");
        webView.loadUrl("https://docs.google.com/presentation/d/1QyNNURCVBme50SAuIceq3sh7Ky74LuWNeEM8B910aC4/pub?delayms=4500&loop=true&start=true&slide=id.p");
        //webView.loadUrl("");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException
    {
            JSONObject forecast = new JSONObject(jsonData);
String timezone = forecast.getString("timezone");
        Log.i(TAG, "From Json: "+ timezone);

        return null;
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
