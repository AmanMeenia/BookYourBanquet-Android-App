package com.miet.eventsandbeyonds.httpRequest;

import android.util.Log;

import com.miet.eventsandbeyonds.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    public Response sendPostRequest(String requestURL,
                                    HashMap<String,String> postData) {

        Response response = new Response();
        URL url;
        StringBuilder sb = new StringBuilder();
        String responseStr="";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(25000);
            conn.setConnectTimeout(25000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept","*/*");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postData));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            Log.i(Utils.TAG,"Response Code: " + responseCode);

            InputStream inputStream;
            //409 conflict error
            //400 Bad request error
            if(responseCode == 400 || responseCode == 409){
                inputStream = conn.getErrorStream();
            }else{
                inputStream = conn.getInputStream();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            sb = new StringBuilder();

            while ((responseStr = br.readLine()) != null){
                sb.append(responseStr);
            }
            Log.i(Utils.TAG, "Response code " + responseCode +  " Response String " + sb);
            response.setResponseCode(responseCode);
            response.setResponse(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        Log.i(Utils.TAG,"Post Data String: " + result.toString());
        return result.toString();
    }
}