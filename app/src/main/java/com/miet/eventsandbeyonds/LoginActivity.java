package com.miet.eventsandbeyonds;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.miet.eventsandbeyonds.Banquet.BanquetPanel;
import com.miet.eventsandbeyonds.Banquet.BanquetRegister;
import com.miet.eventsandbeyonds.admin.AdminPanel;
import com.miet.eventsandbeyonds.cater.CaterPanel;
import com.miet.eventsandbeyonds.cater.CaterRegister;
import com.miet.eventsandbeyonds.ngo.NGOPanel;
import com.miet.eventsandbeyonds.ngo.NGORegister;
import com.miet.eventsandbeyonds.user.Banquet;
import com.miet.eventsandbeyonds.user.UserPanel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String USER_KEY = "Email";
    private static final String PASSWORD_KEY = "Password";
    private static final String LOGIN_URL = Utils.loginUrl;
    private UserLoginTask mAuthTask = null;
    private ProgressDialog progressDialog;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button signInButton;
    private Button registerButton, caterBtn, banquetBtn, ngoBtn,registerBanquetCater;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(Utils.banquetSharedPrefs, Context.MODE_PRIVATE);

        if(sharedPreferences!=null)
        {
            if(sharedPreferences.getString(Utils.ROLE,"null").equals("admin")&&!sharedPreferences.getString(Utils.USEREMAIL,"null").equals("null"))
            {
                Intent intent = new Intent(LoginActivity.this,AdminPanel.class);
                startActivity(intent);
            }
            else if(sharedPreferences.getString(Utils.ROLE,"null").equals("user")&&!sharedPreferences.getString(Utils.USEREMAIL,"null").equals("null"))
            {
                Intent intent = new Intent(LoginActivity.this,UserPanel.class);
                startActivity(intent);
            }
            else if(sharedPreferences.getString(Utils.ROLE,"null").equals("banquet")&&!sharedPreferences.getString(Utils.USEREMAIL,"null").equals("null"))
            {
                Intent intent = new Intent(LoginActivity.this,BanquetPanel.class);
                startActivity(intent);
            }
            else if(sharedPreferences.getString(Utils.ROLE,"null").equals("ngo")&&!sharedPreferences.getString(Utils.USEREMAIL,"null").equals("null"))
            {
                Intent intent = new Intent(LoginActivity.this,NGOPanel.class);
                startActivity(intent);
            }
            else if(sharedPreferences.getString(Utils.ROLE,"null").equals("cater")&&!sharedPreferences.getString(Utils.USEREMAIL,"null").equals("null"))
            {
                Intent intent = new Intent(LoginActivity.this,CaterPanel.class);
                startActivity(intent);
            }

        }
        setContentView(R.layout.activity_login);

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        signInButton = (Button)findViewById(R.id.btn_sign_in);
        registerButton = (Button)findViewById(R.id.btn_register);
        caterBtn = (Button) findViewById(R.id.caterRegister);
        banquetBtn = (Button) findViewById(R.id.banquetHallRegister);
        ngoBtn = (Button) findViewById(R.id.ngoRegister);
      //  registerBanquetCater=(Button)findViewById(R.id.btn_registerBanquetCater);


        caterBtn.setPaintFlags(caterBtn.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        banquetBtn.setPaintFlags(banquetBtn.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        caterBtn.setOnClickListener(this);
        banquetBtn.setOnClickListener(this);
        ngoBtn.setOnClickListener(this);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            if(!Utils.isConnectedToInternet(LoginActivity.this))
            {
                Toast.makeText(LoginActivity.this, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
            }
            else
            {
                progressDialog = new ProgressDialog(LoginActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                mAuthTask = new UserLoginTask(email, password, LOGIN_URL);
                mAuthTask.execute((Void) null);
            }
        }
    }

    private boolean isEmailValid(String email) {
        if(email.contains("@")&&email.contains("."))
            return true;
        else
            return false;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banquetHallRegister:
                Intent banquetIntent = new Intent(LoginActivity.this,BanquetRegister.class);
                startActivity(banquetIntent);
                break;
            case R.id.caterRegister:
                Intent caterIntent = new Intent(LoginActivity.this,CaterRegister.class);
                startActivity(caterIntent);
                break;
            case R.id.ngoRegister:
                Intent ngoIntent = new Intent(LoginActivity.this,NGORegister.class);
                startActivity(ngoIntent);
                break;

        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, String> {

        private RequestHandler requestHandler = new RequestHandler();
        private final String mEmail;
        private final String mPassword;
        private final String URL;

        UserLoginTask(String email, String password, String URL) {
            mEmail = email;
            mPassword = password;
            this.URL = URL;
        }

        @Override
        protected String doInBackground(Void... params) {

            HashMap<String,String> data = new HashMap<>();
            data.put(USER_KEY,mEmail);
            data.put(PASSWORD_KEY,mPassword);
            String response = requestHandler.sendPostRequest(URL,data);
            Log.i(Utils.TAG,response);

            return response;
        }

        @Override
        protected void onPostExecute(final String success) {
//            Toast.makeText(LoginActivity.this, "RESULT: " + success, Toast.LENGTH_SHORT).show();
            mAuthTask = null;
            Log.i(Utils.TAG,success);
            progressDialog.dismiss();
            JSONArray array = null;
            JSONObject jsonObject = null;
            try {
                if (!success.contains("error")) {
                    array = new JSONArray(success);
                    Log.i(Utils.TAG,array.toString());
                    jsonObject = array.getJSONObject(0);
                    Log.i(Utils.TAG,jsonObject.toString());
                    Intent intent = null;
                    if(jsonObject.getString("Role").equals("user"))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Utils.USERID, jsonObject.getString("Id"));
                        editor.putString(Utils.USERFNAME, jsonObject.getString("Name"));
                        editor.putString(Utils.USEREMAIL, jsonObject.getString("Email"));
                        editor.putString(Utils.ROLE, jsonObject.getString("Role"));
                        editor.commit();

                        Log.i(Utils.TAG,"Role is User");
                        intent = new Intent(LoginActivity.this,UserPanel.class);
                        startActivity(intent);
                        finish();
                    } else if(jsonObject.getString("Role").equals("admin")&&jsonObject.getString("Valid").equals("1"))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Utils.USERID, jsonObject.getString("Id"));
                        editor.putString(Utils.USERFNAME, jsonObject.getString("Name"));
                        editor.putString(Utils.USEREMAIL, jsonObject.getString("Email"));
                        editor.putString(Utils.ROLE, jsonObject.getString("Role"));
                        editor.commit();
                        Log.i(Utils.TAG,"Role is admin");
                        intent = new Intent(LoginActivity.this,AdminPanel.class);
                        intent.putExtra("email",mEmailView.getText().toString());
                        startActivity(intent);
                        finish();
                    } else if(jsonObject.getString("Role").equals("banquet")&&jsonObject.getString("Valid").equals("1"))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Utils.USERID, jsonObject.getString("Id"));
                        editor.putString(Utils.USERFNAME, jsonObject.getString("Name"));
                        editor.putString(Utils.USEREMAIL, jsonObject.getString("Email"));
                        editor.putString(Utils.ROLE, jsonObject.getString("Role"));
                        editor.commit();
                        Log.i(Utils.TAG,"Role is Banquet");
                        intent = new Intent(LoginActivity.this,BanquetPanel.class);
                        intent.putExtra("email",mEmailView.getText().toString());
                        startActivity(intent);
                        finish();
                    }else if(jsonObject.getString("Role").equals("cater")&&jsonObject.getString("Valid").equals("1"))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Utils.USERID, jsonObject.getString("Id"));
                        editor.putString(Utils.USERFNAME, jsonObject.getString("Name"));
                        editor.putString(Utils.USEREMAIL, jsonObject.getString("Email"));
                        editor.putString(Utils.ROLE, jsonObject.getString("Role"));
                        editor.commit();
                        Log.i(Utils.TAG,"Role is Cater");
                        intent = new Intent(LoginActivity.this,CaterPanel.class);
                        intent.putExtra("email",mEmailView.getText().toString());
                        startActivity(intent);
                        finish();
                    }else if(jsonObject.getString("Role").equals("ngo")&&jsonObject.getString("Valid").equals("1"))
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Utils.USERID, jsonObject.getString("Id"));
                        editor.putString(Utils.USERFNAME, jsonObject.getString("Name"));
                        editor.putString(Utils.USEREMAIL, jsonObject.getString("Email"));
                        editor.putString(Utils.ROLE, jsonObject.getString("Role"));
                        editor.commit();
                        Log.i(Utils.TAG,"Role is NGO");
                        intent = new Intent(LoginActivity.this,NGOPanel.class);
                        intent.putExtra("email",mEmailView.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "You are not Validated yet", Toast.LENGTH_SHORT).show();
                    }
                } else if(success.contains("invalid")){
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mEmailView.setError(getString(R.string.error_invalid_email));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            progressDialog.dismiss();
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
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

}


