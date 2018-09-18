package com.miet.eventsandbeyonds.httpRequest;

import android.os.AsyncTask;

import com.miet.eventsandbeyonds.Utils;

import java.util.HashMap;

public abstract class AsyncRequest extends AsyncTask<HashMap<String,String>,Void,Response> {

    String url;

    //Implement this method to handle response in UI thread
    public abstract void responseListener(Response response);

    public AsyncRequest     (String relativeUrl){
        this.url = Utils.base_URL + relativeUrl;
    }

    @Override
    protected Response doInBackground(HashMap<String, String>... postDataHashMap) {
        RequestHandler requestHandler = new RequestHandler();
        return requestHandler.sendPostRequest(url,postDataHashMap[0]);
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        responseListener(response);
    }
}
