package com.miet.eventsandbeyonds;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.miet.eventsandbeyonds.user.Banquet;

import java.util.ArrayList;

public class Utils {

    //eventsandbeyonds
    public static final String banquetSharedPrefs = "eventsandbeyondsSharedPrefs";
    public static final String TAG = "eventsandbeyonds";
    public static final String USERID = "USERID";
    public static final String USERFNAME = "FNAME";
    public static final String USERLNAME = "LNAME";
    public static final String USEREMAIL = "EMAIL";
    public static final String ROLE = "ROLE";

    //URLs
 //   public static final String registerUrl = "http://www.errajatsharma.com/2017/EventsAndBeyond/register.php";
  //  public static final String loginUrl = "http://www.errajatsharma.com/2017/EventsAndBeyond/login.php";
  //  public static final String registerUrlBanquet = "http://www.errajatsharma.com/2017/EventsAndBeyond/registerBanquet.php";
  //  public static final String registerUrlCater = "http://www.errajatsharma.com/2017/EventsAndBeyond/registerCater.php";
  //  public static final String registerUrlNGO = "http://www.errajatsharma.com/2017/EventsAndBeyond/registerNGO.php";
  //  public static String base_URL = "http://www.errajatsharma.com/2017/EventsAndBeyond";

    public static final String registerUrl = "https://amanmeenia.000webhostapp.com/Banquet/register.php";
    public static final String loginUrl = "https://amanmeenia.000webhostapp.com/Banquet/login.php";
    public static final String registerUrlBanquet = "https://amanmeenia.000webhostapp.com/Banquet/registerBanquet.php";
    public static final String registerUrlCater = "https://amanmeenia.000webhostapp.com/Banquet/registerCater.php";
    public static final String registerUrlNGO = "https://amanmeenia.000webhostapp.com/Banquet/registerNGO.php";
    public static String base_URL = "https://amanmeenia.000webhostapp.com/Banquet";


    //Methods

    //check for internet
    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }

    public static boolean logOutOfApp(Context applicationContext) {
        SharedPreferences sharedPrefs = applicationContext.getSharedPreferences(Utils.banquetSharedPrefs, Context.MODE_PRIVATE);

        SharedPreferences.Editor e = sharedPrefs.edit();
        e.putString(Utils.USERID, null);
        e.putString(Utils.USEREMAIL, null);
        e.putString(Utils.USERFNAME, null);
        e.putString(Utils.ROLE, null);
        e.commit();
        Log.i(Utils.TAG,sharedPrefs.getString(Utils.USEREMAIL,"null"));
        return true;
    }

    public static String getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Utils.banquetSharedPrefs,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERID,"");
    }
}

