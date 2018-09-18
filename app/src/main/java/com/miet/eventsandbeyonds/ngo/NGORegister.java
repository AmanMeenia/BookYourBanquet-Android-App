package com.miet.eventsandbeyonds.ngo;

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

public class NGORegister extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private static final String EMAIL_KEY = "Email";
    private static final String NAME_KEY = "Name";
    private static final String PASSWORD_KEY = "Password";
    private static final String ROLE_KEY = "Role";
    private static final String USER_VALID = "Valid";
    private UserRegisterTask mAuthTask = null;
    private static final String REGISTER_URL = Utils.registerUrlNGO;
    // UI references.
    private EditText mEmailView, mPasswordView, mNGOName, mPhone, mAddress;
    private ProgressDialog progressDialog;
    private String role = "ngo";
    private String valid = "0";
    private String email, password, name, phone, address;

    private GoogleMap mMap;
    private LatLng mLatLng;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoregister);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapNGO);
        mapFragment.getMapAsync(this);

        mEmailView = (EditText)findViewById(R.id.emailNGO);
        mNGOName = (EditText)findViewById(R.id.nameNGO);
        mPasswordView = (EditText)findViewById(R.id.passwordNGO);
        mPhone = (EditText)findViewById(R.id.phoneNGO);
        mAddress = (EditText)findViewById(R.id.addressNGO);
        registerBtn = (Button)findViewById(R.id.registerBtnNGO);

        registerBtn.setOnClickListener(this);
    }

    public void getEditTextValues() {
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        phone = mPhone.getText().toString();
        address =mAddress.getText().toString();
        name = mNGOName.getText().toString();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerBtnNGO:
                getEditTextValues();
                progressDialog = new ProgressDialog(NGORegister.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Registering...");
                progressDialog.show();

                mAuthTask = new UserRegisterTask(email, password,name, phone, address, String.valueOf(mLatLng.latitude), String.valueOf(mLatLng.longitude), REGISTER_URL, role, valid);
                mAuthTask.execute((Void) null);
                break;
        }
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {
        private RequestHandler requestHandler = new RequestHandler();
        private final String mEmail;
        private final String mPassword;
        private final String name, mURL, role, valid, phone, address, lat, lng;

        UserRegisterTask(String email, String password, String name,String phone,String address,String lat,String lng, String url, String role, String valid) {
            mEmail = email;
            mPassword = password;
            this.name = name;
            mURL = url;
            this.role = role;
            this.valid = valid;
            this.phone = phone;
            this.address = address;
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
            data.put("Phone",phone);
            data.put("Address",address);
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
                Toast.makeText(NGORegister.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            progressDialog.dismiss();

            Toast.makeText(NGORegister.this, "Registration Canceled", Toast.LENGTH_SHORT).show();
        }
    }
}
