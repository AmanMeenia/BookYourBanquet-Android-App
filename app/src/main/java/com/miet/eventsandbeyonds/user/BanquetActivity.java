package com.miet.eventsandbeyonds.user;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.miet.eventsandbeyonds.R;

import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.callback.Callback;

public class BanquetActivity extends AppCompatActivity {
    TextView b_nameBanquet, b_addressBanquet, b_rate, b_hallBanquet, b_squareft, b_parkingBanquet;
    ImageView b_roomImage;
    private FloatingActionButton b_favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banquet);
        supportPostponeEnterTransition();

        Bundle bundle = getIntent().getExtras();
        String houseJson = bundle.getString("Banquet");
        Log.i("Banquet data", houseJson);

        b_nameBanquet = (TextView)findViewById(R.id.nameBanquet);
        b_rate = (TextView)findViewById(R.id.rateBanquet);
        b_hallBanquet = (TextView)findViewById(R.id.hallBanquet);
        b_parkingBanquet = (TextView)findViewById(R.id.parkingBanquet);
        b_squareft = (TextView)findViewById(R.id.square_ftBanquet);
        b_addressBanquet = (TextView)findViewById(R.id.addressBanquet);
        b_roomImage = (ImageView)findViewById(R.id.imageBanquet);
        b_favourite = (FloatingActionButton) findViewById(R.id.favouriteBanquet);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(houseJson);
            String title = jsonObject.getString("title");
            String rate = jsonObject.getString("rate");
            String squareft = jsonObject.getString("squareft");
            String bathroom = jsonObject.getString("bathroom");
            String room = jsonObject.getString("room");
            String id = jsonObject.getString("id");
            String rating = jsonObject.getString("rating");
            String address = jsonObject.getString("address");
            String image = jsonObject.getString("imageUrl");

             Banquet banquet = new Banquet(title, address, room, bathroom, image, id, rating, rate, squareft);

            this.b_addressBanquet.setText(banquet.getA_address());
            this.b_rate.setText("Rs." + banquet.getRate());
            this.b_nameBanquet.setText(banquet.getA_nameBanuet());
            this.b_hallBanquet.setText(banquet.getA_hall() + " Hall");
            this.b_parkingBanquet.setText(banquet.getA_parking() + " Parking");
            this.b_squareft.setText(banquet.getSquareaFet() + " ft");

            //b_roomImage.setTransitionName(b_nameBanquet);
           // Log.i("Transition Name" , b_nameBanquet);
            //Picasso.with(this).load(house.getImageUrl()).into(roomImage);

           /* Picasso.with(this)
                    .load(banquet.getImageUrl())
                    .noFade()
                    .into(roomImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });*/

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
