package com.miet.eventsandbeyonds.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.miet.eventsandbeyonds.Item;
import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.Utils;
import com.miet.eventsandbeyonds.httpRequest.AsyncRequest;
import com.miet.eventsandbeyonds.httpRequest.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        AsyncRequest asyncRequest = new AsyncRequest("/searchBanquet.php") {
            @Override
            public void responseListener(Response response) {
                ArrayList<Item> labItems = new ArrayList<>();
                Item labItem;
                String s = response.getResponse();
                try {
                    if(s.contains("error"))
                    {
                        Toast.makeText(SearchResultActivity.this, "No Items Found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    JSONArray arr = new JSONArray(s);
                    Log.i(Utils.TAG, arr.toString() + arr.length());


                    for(int i=0;i<arr.length();i++)
                    {
                        JSONObject jsonObj = arr.getJSONObject(i);
                        {
                            labItem = new Item();
                            labItem.setName(jsonObj.getString("Name"));
                            labItem.setPrice(jsonObj.getString("Price"));
                            labItem.setAddress(jsonObj.getString("Address"));
                            labItem.setId(jsonObj.getString("Id"));
                            labItem.setType(jsonObj.getString("Role"));
                            labItems.add(labItem);
                        }
                    }
                    Log.i(Utils.TAG,labItems.toString());
                    // Set the adapter
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));

                    recyclerView.setAdapter(new StoreItemsRecyclerViewAdapter(labItems));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        HashMap<String, String> data = new HashMap<>();
        Bundle bundle = getIntent().getExtras();
        String up = bundle.getString("u");
        String down = bundle.getString("l");
        data.put("up",up);
        data.put("down",down);
        Log.i(Utils.TAG,data.toString());
        asyncRequest.execute(data);
    }
}
