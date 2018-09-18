package com.miet.eventsandbeyonds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class
BanquetDetails extends AppCompatActivity  implements View.OnClickListener{
    ImageButton b11,b12,b13,b21,b22,b23,b31,b32,b33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banquet_details);
        b11=(ImageButton) findViewById(R.id.BanquetButton11);
        b12=(ImageButton) findViewById(R.id.BanquetButton12);
        b13=(ImageButton) findViewById(R.id.BanquetButton13);
        b21=(ImageButton) findViewById(R.id.BanquetButton21);
        b22=(ImageButton) findViewById(R.id.BanquetButton22);
        b23=(ImageButton) findViewById(R.id.BanquetButton23);
        b31=(ImageButton) findViewById(R.id.BanquetButton31);
        b32=(ImageButton) findViewById(R.id.BanquetButton32);
        b33=(ImageButton) findViewById(R.id.BanquetButton33);

        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        b23.setOnClickListener(this);
        b31.setOnClickListener(this);
        b32.setOnClickListener(this);
        b33.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.BanquetButton11:
                Intent intent11 = new Intent(BanquetDetails.this ,BlueMoon.class);
                startActivity(intent11);
                break;
            case R.id.BanquetButton12:
                Intent intent12 = new Intent(BanquetDetails.this ,TheGrand.class);
                startActivity(intent12);
                break;
            case R.id.BanquetButton13:
                Intent intent13 = new Intent(BanquetDetails.this ,SunCity.class);
                startActivity(intent13);
                break;
            case R.id.BanquetButton21:
                Intent intent21 = new Intent(BanquetDetails.this ,Vedas.class);
                startActivity(intent21);
                break;
            case R.id.BanquetButton22:
                Intent intent22 = new Intent(BanquetDetails.this ,RoyalEstates.class);
                startActivity(intent22);
                break;
            case R.id.BanquetButton23:
                Intent intent23 = new Intent(BanquetDetails.this ,TripleFarm.class);
                startActivity(intent23);
                break;
            case R.id.BanquetButton31:
                Intent intent31 = new Intent(BanquetDetails.this ,KcEmporia.class);
                startActivity(intent31);
                break;
            case R.id.BanquetButton32:
                Intent intent32 = new Intent(BanquetDetails.this ,KamalSheeshMahal.class);
                startActivity(intent32);
                break;
            case R.id.BanquetButton33:
                Intent intent33 = new Intent(BanquetDetails.this ,GuptaResort.class);
                startActivity(intent33);
                break;

        }
    }
}
