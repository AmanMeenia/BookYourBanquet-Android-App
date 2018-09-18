package com.miet.eventsandbeyonds.user;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.RequestHandler;
import com.miet.eventsandbeyonds.Utils;

import java.util.HashMap;

public class DonateFoodActivity extends AppCompatActivity implements View.OnClickListener {

    EditText banquetName, description, address, phone;
    Button donateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_food);

        banquetName = (EditText) findViewById(R.id.banquetName);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        description = (EditText)findViewById(R.id.description);
        donateBtn = (Button)findViewById(R.id.donate_btn);

        donateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String banquet = banquetName.getText().toString();
        String ph = phone.getText().toString();
        String add = address.getText().toString();
        String des = description.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences(Utils.banquetSharedPrefs,MODE_PRIVATE);
        String userId = sharedPreferences.getString(Utils.USERID,"0");
        if(!TextUtils.isEmpty(banquet)||!TextUtils.isEmpty(ph)||!TextUtils.isEmpty(add)||!TextUtils.isEmpty(des)){
            new DonateFoodTask().execute(banquet,ph,add,des,userId);
        }
    }

    class DonateFoodTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            RequestHandler requestHandler = new RequestHandler();
            HashMap<String,String> data = new HashMap<>();
            data.put("banquetName",strings[0]);
            data.put("phone",strings[1]);
            data.put("address",strings[2]);
            data.put("description",strings[3]);
            data.put("userId",strings[4]);
            return requestHandler.sendPostRequest("https://amanmeenia.000webhostapp.com/Banquet/donateFoodRequest.php",data);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(DonateFoodActivity.this, "" + s, Toast.LENGTH_SHORT).show();

        }
    }
}
