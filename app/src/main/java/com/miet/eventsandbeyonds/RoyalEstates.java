package com.miet.eventsandbeyonds;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miet.eventsandbeyonds.user.BookBanquetNow;

public class RoyalEstates extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_royal_estates);
        Button b1,b2;
        b1=(Button)findViewById(R.id.RoyleEstateButton);
        b2=(Button)findViewById(R.id.RoyleEstateButtonPay);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(RoyalEstates.this ,BookBanquetNow.class);
                startActivity(intent11);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bluemoonIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://royalestatebanquets.com"));
                startActivity(bluemoonIntent);
                Intent instC = new Intent(RoyalEstates.this, BarowserActivity.class);
                instC.putExtra("uriR", "http://royalestatebanquets.com");
                startActivity(instC);

            }
        });
    }
}
