package com.miet.eventsandbeyonds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class BarowserActivity extends AppCompatActivity {
    WebView ourBro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barowser);
        ourBro = (WebView)findViewById(R.id.WebBanquet);
        Intent blueMoonClick=getIntent();
        String uriBlue = blueMoonClick.getStringExtra("uriBlue");
        ourBro.loadUrl(uriBlue);

        Intent grandClick=getIntent();
        String uriGrand = blueMoonClick.getStringExtra("uriG");
        ourBro.loadUrl(uriGrand);

        Intent sunClick=getIntent();
        String urisun = blueMoonClick.getStringExtra("uriS");
        ourBro.loadUrl(urisun);

        Intent vedasClick=getIntent();
        String urivedas = blueMoonClick.getStringExtra("uriV");
        ourBro.loadUrl(urivedas);

        Intent royalClick=getIntent();
        String uriroyal = blueMoonClick.getStringExtra("uriR");
        ourBro.loadUrl(uriroyal);

        Intent tripleClick=getIntent();
        String uritriple = blueMoonClick.getStringExtra("uriT");
        ourBro.loadUrl(uritriple);

        Intent grandkc=getIntent();
        String urikc = blueMoonClick.getStringExtra("uriKC");
        ourBro.loadUrl(urikc);

        Intent grandkamla=getIntent();
        String urikamla = blueMoonClick.getStringExtra("uriK");
        ourBro.loadUrl(urikamla);

        Intent grandgupta=getIntent();
        String urigupta = blueMoonClick.getStringExtra("uriGU");
        ourBro.loadUrl(urigupta);



        // ourBro = (WebView) findViewById(R.id.WebBanquet);
        //WebSettings settings = ourBro.getSettings();
        //ourBro.setWebViewClient(new MyBrowser());
        //ourBro.getSettings().setJavaScriptEnabled(true);
        //ourBro.loadUrl("https://bluemoonbanquets.com");

        }

        @Override
        public void onBackPressed () {
            if (ourBro.canGoBack()) {
                ourBro.goBack();
            } else {
                super.onBackPressed();
            }
        }

        private class MyBrowser extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        }
    }


