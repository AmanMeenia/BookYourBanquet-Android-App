package com.miet.eventsandbeyonds;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ItemFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list2, container, false);
       ArrayList<Banquet> banquetArrayList = new ArrayList<>();


        banquetArrayList.add(new Banquet("BLUE MOON","Jakh ,National Highway","4","1","https://i0.wp.com/bluemoonbanquets.com/wp-content/uploads/revslider/notgeneric2/slider-2-blue-moon-banquets.jpg?fit=1280%2C720&ssl=1","1","5","2,00,000","80,000sqft"));
       banquetArrayList.add(new Banquet("THE GRAND","Sainik Colony","3","2","http://www.thegrandjammu.com/wp-content/uploads/2016/05/contactpic.jpg","2","5","1,25,000","90,000sqft"));
       banquetArrayList.add(new Banquet("Kamla Sheesh Mahal","MHAC Road, Nagbani, Jammu","2","3","http://hariniwaspalace.in/4.jpg","3","3","2,00,000","1,25,000"));
        banquetArrayList.add(new Banquet("GUPTA  RESORT","Narwal Bye Pass Chowk, Near Toyota Showroom, Jammu","1","50,000sqft","http://kamlasheeshmahal.com/wp-content/uploads/2017/12/IMG_0586-1020x680-1300x770.jpg","4","4","1,75,000","60000sqft"));
        banquetArrayList.add(new Banquet("KC Emporia","Near BSF Headquarters, Akhnoor Road, Jammu","3","40,000sqft","http://kcemporia.com/wp-content/uploads/2015/04/gal3-1.jpg","5","5","2,00,000","50000sqft"));
        banquetArrayList.add(new Banquet("ROYAL ESTATE","Toph Bridge, Akhnoor Road, Jammu","2","50,000sqft","http://royalestatebanquets.com/img/gallery/3.jpg","6","5","2,00,000","60000sqft"));
        banquetArrayList.add(new Banquet("SUN CITY FARM","Miran Sahib Bazar, Jammu","3","60,000sqft","http://www.suncityfarms.com/AdminPanel/uploads/88844-11183462_430945443746485_122923320831331860_n.jpg","7","5","2,00,000","50000sqft"));
        banquetArrayList.add(new Banquet("TRIPLE  FARM","2nd Milestone, Akhnoor Road, Jammu","2","50,000sqft","http://www.triplesfarms.in/images/gallery/1.jpg","8","5","2,00,000","80000sqft"));
        banquetArrayList.add(new Banquet("VEDAS FARM JAMMU","Adjoining Best Price, Toph Sherkhanian, Akhnoor Ro...Adjoining Best Price, Toph Sherkhanian, Akhnoor Ro...","3","40,000sqft","http://www.vedasjammu.com/images/4.jpg","9","5","2,00,000","60000sqft"));

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new  MyItemRecyclerViewAdapter(banquetArrayList  ,getContext()));
        }
        return view;
    }
  /*  class  BanquetDownloaderTask  extends AsyncTask<Void,Void,String>
    {
        public BanquetDownloaderTask() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {

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




}

