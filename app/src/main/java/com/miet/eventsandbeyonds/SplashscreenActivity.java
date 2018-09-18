package com.miet.eventsandbeyonds;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.miet.eventsandbeyonds.user.Banquet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashscreenActivity extends AppCompatActivity {

    private View mContentView;
    private View mControlsView;
    private boolean mVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);

        mVisible = true;

        mContentView = findViewById(R.id.fullscreen_content);


        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent  = new Intent(SplashscreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
   /* class  BanquetDownloaderTask  extends AsyncTask<Void,Void,String>
    {
        public BanquetDownloaderTask() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url =new URL("http://www.errajatsharma.com/2017/getHouse.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String row;
                StringBuilder stringBuilder = new StringBuilder();

                while ((row = bufferedReader.readLine())!=null){
                    stringBuilder.append(row);
                }

                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
            }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                ArrayList<Banquet> banquetArrayList =  new ArrayList<>();
                for (int i=0; i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String rate = jsonObject.getString("rate");
                    String squareft = jsonObject.getString("squareft");
                    String bathroom = jsonObject.getString("bathroom");
                    String room = jsonObject.getString("room");
                    String id = jsonObject.getString("id");
                    String rating = jsonObject.getString("rating");
                    String address = jsonObject.getString("address");
                    String image = jsonObject.getString("image");
                    Banquet banquet = new Banquet(title,rate,squareft,bathroom,room,id,rating,address,image);
                    banquetArrayList.add(banquet);
                }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
     }
    }*/

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;
    }
}
