package com.miet.eventsandbeyonds;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miet.eventsandbeyonds.user.BookBanquetNow;

public class TheGrand extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_grand);
        Button button,b2;
        button = (Button) findViewById(R.id.thegrandButton);
        b2=(Button)findViewById(R.id.thegrandButtonPay);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(TheGrand.this ,BookBanquetNow.class);
                startActivity(intent11);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.thegrandjammu.com"));
                Intent instC = new Intent(TheGrand.this, BarowserActivity.class);
                instC.putExtra("uriG", "http://www.thegrandjammu.com");
                startActivity(instC);

            }
        });

    }
}
