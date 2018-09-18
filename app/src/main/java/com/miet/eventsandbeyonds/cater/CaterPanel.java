package com.miet.eventsandbeyonds.cater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.miet.eventsandbeyonds.Banquet.BanquetPanel;
import com.miet.eventsandbeyonds.Banquet.BanquetRegister;
import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.RequestHandler;
import com.miet.eventsandbeyonds.Utils;

import java.util.HashMap;

public class CaterPanel extends AppCompatActivity implements View.OnClickListener {

    private ImageView addCaterBtn;
    private ImageView removeCaterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cater_panel);

        addCaterBtn = (ImageView)findViewById(R.id.addCaterBtn);
        removeCaterBtn = (ImageView)findViewById(R.id.removeCaterBtn);

        addCaterBtn.setOnClickListener(this);
        removeCaterBtn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                if(Utils.logOutOfApp(getApplicationContext()))
                {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id)
        {
            case R.id.addCaterBtn:
                Intent intent = new Intent(CaterPanel.this,CaterRegister.class);
                startActivity(intent);
                break;
            case R.id.removeCaterBtn:
                SharedPreferences sharedPreferences = getSharedPreferences(Utils.banquetSharedPrefs,MODE_PRIVATE);

                new RemoveUser().execute(sharedPreferences.getString(Utils.USERID,"0"));
                break;
        }
    }

    private class RemoveUser extends AsyncTask<String,Void,String>
    {

     //   private String URL = "http://www.errajatsharma.com/2017/EventsAndBeyond/remove.php";
        private String URL = "https://amanmeenia.000webhostapp.com/Banquet/remove.php";
        private RequestHandler requestHandler = new RequestHandler();

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> data = new HashMap<>();
            data.put("UserId",strings[0]);
            String response = requestHandler.sendPostRequest(URL,data);
            Log.v(Utils.TAG,response);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(Utils.TAG,"Response"  + s);
            if(s.contains("removed"))
            {
                Toast.makeText(CaterPanel.this, "Cater Data is removed", Toast.LENGTH_SHORT).show();
                if(Utils.logOutOfApp(getApplicationContext()))
                {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    finish();
                }
            }else{

                Toast.makeText(CaterPanel.this, "No Cater Data exists", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
