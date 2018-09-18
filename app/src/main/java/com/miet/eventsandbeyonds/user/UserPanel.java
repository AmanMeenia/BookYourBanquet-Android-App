package com.miet.eventsandbeyonds.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.miet.eventsandbeyonds.BanquetDetails;
import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.ShowBanquetList;
import com.miet.eventsandbeyonds.Utils;

public class UserPanel extends AppCompatActivity implements View.OnClickListener {

    Button findBanquetBtn, reviewBanquetBtn, findCaterBtn, reviewCaterBtn, donateFoodBtn, searchFoodBtn,banquetList,banquetdetails,banquetBook1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);

        findBanquetBtn = (Button)findViewById(R.id.searchBanquetUsrPnl);
        findCaterBtn = (Button)findViewById(R.id.searchCaterUsrPnl);
        searchFoodBtn = (Button)findViewById(R.id.searchUsrPnl);
        donateFoodBtn = (Button)findViewById(R.id.donateFoodUsrPnl);
        banquetList = (Button)findViewById(R.id.banquet_list);
        banquetdetails =(Button)findViewById(R.id.banquet_detail);
        banquetBook1 =(Button)findViewById(R.id.banquet_book);

        findBanquetBtn.setOnClickListener(this);
        findCaterBtn.setOnClickListener(this);
        donateFoodBtn.setOnClickListener(this);
        searchFoodBtn.setOnClickListener(this);
        banquetList.setOnClickListener(this);;
        banquetdetails.setOnClickListener(this);
        banquetBook1.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                if(Utils.logOutOfApp(getApplicationContext()))
                {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchBanquetUsrPnl:
                Intent intent = new Intent(UserPanel.this,FindBanquetActivity.class);
                startActivity(intent);
                break;
            case R.id.searchCaterUsrPnl:
                Intent intent2 = new Intent(UserPanel.this,FindCatersActivity.class);
                startActivity(intent2);
                break;
            case R.id.donateFoodUsrPnl:
                Intent intent3 = new Intent(UserPanel.this,DonateFoodActivity.class);
                startActivity(intent3);
                break;
            case R.id.searchUsrPnl:
                Intent intent4 = new Intent(UserPanel.this,SearchByPriceActivity.class);
                startActivity(intent4);
                break;
            case R.id.banquet_list:
                Intent intent5 = new Intent(UserPanel.this,ShowBanquetList.class);
                startActivity(intent5);
                break;
            case R.id.banquet_detail:
                Intent intent6 = new Intent(UserPanel.this,BanquetDetails.class);
                startActivity(intent6);
                break;
            case R.id.banquet_book:
                Intent intent7 = new Intent(UserPanel.this,BookBanquetNow.class);
                startActivity(intent7);
                break;
        }
    }
}
