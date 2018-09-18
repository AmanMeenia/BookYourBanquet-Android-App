package com.miet.eventsandbeyonds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miet.eventsandbeyonds.Banquet.BanquetRegister;
import com.miet.eventsandbeyonds.cater.CaterRegister;
import com.miet.eventsandbeyonds.ngo.NGORegister;

public class RegisterBanquetCater extends AppCompatActivity {
    private Button  caterBtn, banquetBtn, ngoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_banquet_cater);

    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banquetHallRegister:
                Intent banquetIntent = new Intent(RegisterBanquetCater.this,BanquetRegister.class);
                startActivity(banquetIntent);
                break;
            case R.id.caterRegister:
                Intent caterIntent = new Intent(RegisterBanquetCater.this,CaterRegister.class);
                startActivity(caterIntent);
                break;
            case R.id.ngoRegister:
                Intent ngoIntent = new Intent(RegisterBanquetCater.this,NGORegister.class);
                startActivity(ngoIntent);
                break;

        }
    }
    }

