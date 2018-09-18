package com.miet.eventsandbeyonds;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {


    private static final String EMAIL_KEY = "Email";
    private static final String NAME_KEY = "Name";
    private static final String PASSWORD_KEY = "Password";
    private static final String ROLE_KEY = "Role";
    private static final String USER_VALID = "Valid";
    private UserRegisterTask mAuthTask = null;
    private static final String REGISTER_URL = Utils.registerUrl;
    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView, mConfirmPasswordView;
    private EditText mFirstName, mLastName;
    private ProgressDialog progressDialog;
    private String role = "user";
    private String valid = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupActionBar();
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.register_email);
        mPasswordView = (EditText) findViewById(R.id.register_password);
        mConfirmPasswordView = (EditText) findViewById(R.id.register_confirm_password);
        mFirstName = (EditText) findViewById(R.id.register_fName);
        mLastName = (EditText) findViewById(R.id.register_lName);

        Button mEmailSignInButton = (Button) findViewById(R.id.register_btn_register);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing eventsandbeyonds, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mLastName.setError(null);
        mFirstName.setError(null);
        mPasswordView.setError(null);
        mConfirmPasswordView.setError(null);
        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();
        String fName = mFirstName.getText().toString();
        String lName = mLastName.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }else if(fName.isEmpty()){
            mFirstName.setError(getString(R.string.error_field_required));
            focusView = mFirstName;
            cancel = true;
        }else if(lName.isEmpty() && mLastName.getVisibility() == View.VISIBLE ){
            mLastName.setError(getString(R.string.error_field_required));
            focusView = mLastName;
            cancel = true;
        }else if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error));
            focusView = mPasswordView;
            cancel = true;
        }else if (TextUtils.isEmpty(confirmPassword)) {
            mConfirmPasswordView.setError(getString(R.string.error));
            focusView = mConfirmPasswordView;
            cancel = true;
        }else if(!password.equals(confirmPassword))
        {
            Log.i(Utils.TAG,"Password : " + password + " confirmPassword : "  +confirmPassword);
            mPasswordView.setError(getString(R.string.error_invalid_password));
            mConfirmPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            progressDialog = new ProgressDialog(RegisterActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Registering...");
            progressDialog.show();

            mAuthTask = new UserRegisterTask(email, password,fName, lName, REGISTER_URL, role, valid);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    /**
     * Represents an asynchronous login/registration task used to register
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {
        private RequestHandler requestHandler = new RequestHandler();
        private final String mEmail;
        private final String mPassword;
        private final String fName, lName, mURL, role, valid;

        UserRegisterTask(String email, String password, String fName, String lName, String url, String role, String valid) {
            mEmail = email;
            mPassword = password;
            this.fName = fName;
            this.lName =lName;
            mURL = url;
            this.role = role;
            this.valid = valid;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            HashMap<String,String> data = new HashMap<>();
            data.put(EMAIL_KEY,mEmail);
            data.put(PASSWORD_KEY,mPassword);
            data.put(NAME_KEY,fName);
            data.put(USER_VALID,valid);
            data.put(ROLE_KEY,role);
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
                Toast.makeText(RegisterActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            progressDialog.dismiss();

            Toast.makeText(RegisterActivity.this, "Registration Canceled", Toast.LENGTH_SHORT).show();
        }
    }
}


