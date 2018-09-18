package com.miet.eventsandbeyonds.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.miet.eventsandbeyonds.R;

public class SearchByPriceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_price);

        final EditText upp = (EditText)findViewById(R.id.upperPrice);
        final EditText lowe = (EditText)findViewById(R.id.lowerPrice);

        Button searchBtn = (Button)findViewById(R.id.search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchByPriceActivity.this,SearchResultActivity.class);
                intent.putExtra("l",lowe.getText().toString());
                intent.putExtra("u",upp.getText().toString());
                startActivity(intent);
            }
        });
    }
}
