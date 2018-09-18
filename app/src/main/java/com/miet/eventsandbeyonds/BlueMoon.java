package com.miet.eventsandbeyonds;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miet.eventsandbeyonds.user.BookBanquetNow;

public class BlueMoon extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_moon);
        Button b1 ,b2;
        b1=(Button)findViewById(R.id.BlueMoonButton);
        b2=(Button)findViewById(R.id.BlueMoonButtonPay);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(BlueMoon.this ,BookBanquetNow.class);
                startActivity(intent11);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bluemoonIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bluemoonbanquets.com"));
                startActivity(bluemoonIntent);
                Intent instC = new Intent(BlueMoon.this, BarowserActivity.class);
                instC.putExtra("uriBlue", "https://bluemoonbanquets.com");
                startActivity(instC);

            }
        });

    }


}
