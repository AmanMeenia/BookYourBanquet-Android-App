package com.miet.eventsandbeyonds;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miet.eventsandbeyonds.user.BookBanquetNow;

public class Vedas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedas);
        Button b1,b2;
        b1=(Button)findViewById(R.id.vedasButton);
        b2=(Button)findViewById(R.id.vedasButtonPay) ;
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(Vedas.this ,BookBanquetNow.class);
                startActivity(intent11);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent V = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vedasjammu.com"));
                startActivity(V);
                Intent instC = new Intent(Vedas.this, BarowserActivity.class);
                instC.putExtra("uriV", "http://www.vedasjammu.com");
                startActivity(instC);

            }
        });
    }
}
