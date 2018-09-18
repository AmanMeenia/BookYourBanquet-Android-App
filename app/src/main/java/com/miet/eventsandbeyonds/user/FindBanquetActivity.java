package com.miet.eventsandbeyonds.user;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.miet.eventsandbeyonds.PermissionUtils;
import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.RequestHandler;
import com.miet.eventsandbeyonds.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FindBanquetActivity extends AppCompatActivity implements OnMapReadyCallback {

    private int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private String URL = "https://amanmeenia.000webhostapp.com/Banquet/getLocationData.php";

    private boolean mPermissionDenied = false;
    HashMap<String,String> nameIdHashMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_banquet);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.find_banquet_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(FindBanquetActivity.this,FeedbackActivity.class);

                intent.putExtra("t",nameIdHashMap.get(marker.getTitle()));
                startActivity(intent);
            }
        });
        enableMyLocation();
        search();
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    private void search() {
        new GetData().execute();
    }

    private class GetData extends AsyncTask<Void,Void,String> {

        ProgressDialog loading;
        RequestHandler rh = new RequestHandler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(FindBanquetActivity.this, "Downloading Data", "Please wait...", true, true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(Utils.TAG,"Response" + s);
            try {
                if(s.contains("error try again"))
                {
                    loading.dismiss();
                    return;
                }else if(s.contains("error no data found"))
                {
                    Toast.makeText(FindBanquetActivity.this, "No Banquets Found", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    return;
                }
                JSONArray arr = new JSONArray(s);
                Log.i(Utils.TAG, arr.toString() + arr.length());


                for(int i=0;i<arr.length();i++)
                {
                    JSONObject jsonObj = arr.getJSONObject(i);
                    {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(new LatLng(Double.valueOf(jsonObj.getString("Latitude")),Double.valueOf(jsonObj.getString("Longitude"))));
                        markerOptions.title(jsonObj.getString("Name"));
                        markerOptions.snippet("Open: " + jsonObj.getString("OpeningHours") + "hrs; Address:" + jsonObj.getString("Address"));
                        mMap.addMarker(markerOptions);
                        nameIdHashMap.put(jsonObj.getString("Name"),jsonObj.getString("Id"));
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            loading.dismiss();
            Log.i(Utils.TAG, s);
        }

        @Override
        protected String doInBackground(Void... params) {
            HashMap<String, String> data = new HashMap<>();
            data.put("role","banquet");
            Log.i(Utils.TAG, data.toString());
            String result = rh.sendPostRequest(URL, data);
            return result;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            loading.dismiss();
            Toast.makeText(FindBanquetActivity.this, "Canceled", Toast.LENGTH_LONG).show();
        }
    }
}
