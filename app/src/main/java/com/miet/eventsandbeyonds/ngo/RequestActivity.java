package com.miet.eventsandbeyonds.ngo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.RequestHandler;
import com.miet.eventsandbeyonds.Utils;
import com.miet.eventsandbeyonds.admin.UserListFragment;
import com.miet.eventsandbeyonds.admin.UserRequestAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RequestActivity extends AppCompatActivity {

    private RecyclerView donateRequestRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        donateRequestRecycler = (RecyclerView)findViewById(R.id.donate_request_recycler);

        new GetData().execute();
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        donateRequestRecycler.setLayoutManager(layoutManager);

    }

    private class GetData extends AsyncTask<String,Void,String> {

        ProgressDialog loading;
        RequestHandler rh = new RequestHandler();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(RequestActivity.this, "Downloading Data", "Please wait...", true, true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(Utils.TAG,"Response: " + s);
            try {
                if(s.contains("error try again"))
                {
                    loading.dismiss();
                    return;
                }else if(s.contains("error no data found"))
                {
                    Toast.makeText(RequestActivity.this, "No Request Found", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                    return;
                }
                JSONArray arr = new JSONArray(s);
                Log.v(Utils.TAG, arr.toString() + arr.length());
                String[] id = new String[arr.length()];
                String[] email = new String[arr.length()];
                String[] firstName = new String[arr.length()];
                String[] lastName = new String[arr.length()];

                for(int i=0;i<arr.length();i++)
                {
                    JSONObject jsonObj = arr.getJSONObject(i);
                    id[i] = jsonObj.getString("id");
                    email[i] = jsonObj.getString("address");
                    firstName[i] = jsonObj.getString("banquetName");
                    lastName[i] = jsonObj.getString("phone");
                }
                DonateRequestAdapter donateRequestAdapter=new DonateRequestAdapter(id,email,firstName,lastName);
                donateRequestRecycler.setAdapter(donateRequestAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            loading.dismiss();
            Log.v(Utils.TAG, s);
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> data = new HashMap<>();
            Log.v(Utils.TAG, data.toString());
          //  String result = rh.sendPostRequest("http://www.errajatsharma.com/2017/EventsAndBeyond/getDonationRequests.php", data);
            String result = rh.sendPostRequest("https://amanmeenia.000webhostapp.com/Banquet/getDonationRequests.php", data);
            return result;
        }
    }
}
