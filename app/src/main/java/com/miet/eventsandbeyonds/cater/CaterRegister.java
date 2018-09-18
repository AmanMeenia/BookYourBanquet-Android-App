package com.miet.eventsandbeyonds.cater;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.RequestHandler;
import com.miet.eventsandbeyonds.Utils;

import java.util.HashMap;

public class CaterRegister extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private static final String EMAIL_KEY = "Email";
    private static final String NAME_KEY = "Name";
    private static final String PASSWORD_KEY = "Password";
    private static final String ROLE_KEY = "Role";
    private static final String USER_VALID = "Valid";
    private UserRegisterTask mAuthTask = null;
    private static final String REGISTER_URL = Utils.registerUrlBanquet;
    // UI references.
    private EditText mEmailView, mPasswordView, mHallName, mOpeningHours, mPhone, mAddress, mPrice;
    private ProgressDialog progressDialog;
    private String role = "cater";
    private String valid = "0";
    private String email, password, name, openingHours, phone, address, price;

    private GoogleMap mMap;
    private LatLng mLatLng;
    Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cater_register);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapCater);
        mapFragment.getMapAsync(this);

        mEmailView = (EditText)findViewById(R.id.emailCater);
        mPasswordView = (EditText)findViewById(R.id.passwordCater);
        mHallName = (EditText)findViewById(R.id.nameCater);
        mOpeningHours = (EditText)findViewById(R.id.openingHrsCater);
        mPhone = (EditText)findViewById(R.id.phoneCater);
        mAddress = (EditText)findViewById(R.id.addressCater);
        mPrice = (EditText)findViewById(R.id.priceCater);

        registerBtn = (Button)findViewById(R.id.nextBtnCater);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nextBtnCater:
                getEditTextValues();
                progressDialog = new ProgressDialog(CaterRegister.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Registering...");
                progressDialog.show();

                mAuthTask = new UserRegisterTask(email, password,name, openingHours, phone, address, price, String.valueOf(mLatLng.latitude), String.valueOf(mLatLng.longitude), REGISTER_URL, role, valid);
                mAuthTask.execute((Void) null);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //set listener for marker
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mLatLng = latLng;
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });

        LatLng root = new LatLng(32.7052191, 74.869716);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(root));
    }

    public void getEditTextValues() {
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        name = mHallName.getText().toString();
        openingHours = mOpeningHours.getText().toString();
        phone = mPhone.getText().toString();
        address =mAddress.getText().toString();
        price = mPrice.getText().toString();
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {
        private RequestHandler requestHandler = new RequestHandler();
        private final String mEmail;
        private final String mPassword;
        private final String name, mURL, role, valid, openingHours, phone, address, price, lat, lng;

        UserRegisterTask(String email, String password, String name,String openingHours,String phone,String address,String price,String lat,String lng, String url, String role, String valid) {
            mEmail = email;
            mPassword = password;
            this.name = name;
            mURL = url;
            this.role = role;
            this.valid = valid;
            this.openingHours = openingHours;
            this.phone = phone;
            this.address = address;
            this.price = price;
            this.lat = lat;
            this.lng = lng;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            HashMap<String,String> data = new HashMap<>();
            data.put(EMAIL_KEY,mEmail);
            data.put(PASSWORD_KEY,mPassword);
            data.put(NAME_KEY,name);
            data.put(USER_VALID,valid);
            data.put(ROLE_KEY,role);
            data.put("OpeningHours", openingHours);
            data.put("Phone",phone);
            data.put("Address",address);
            data.put("Price",price);

            data.put("Latitude",lat);
            data.put("Longitude",lng);

            String result = requestHandler.sendPostRequest(mURL,data);
            Log.i(Utils.TAG,result);
            if(result.contains("success"))
            {
                return true;
            }
            else{
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            progressDialog.dismiss();

            if (success) {
                finish();
            } else {
                Toast.makeText(CaterRegister.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            progressDialog.dismiss();

            Toast.makeText(CaterRegister.this, "Registration Canceled", Toast.LENGTH_SHORT).show();
        }
    }
}
