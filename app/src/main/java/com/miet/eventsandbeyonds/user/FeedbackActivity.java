package com.miet.eventsandbeyonds.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.Utils;
import com.miet.eventsandbeyonds.httpRequest.AsyncRequest;
import com.miet.eventsandbeyonds.httpRequest.Response;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity {

    private EditText desc;
    private EditText title;
    private Button submitFeedback;
    private RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        title = (EditText)findViewById(R.id.title);
        desc = (EditText)findViewById(R.id.description);
        rating = (RatingBar)findViewById(R.id.ratingBar);
        submitFeedback = (Button)findViewById(R.id.submit_feedback);

        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncRequest asyncRequest = new AsyncRequest("/feedback.php") {
                    @Override
                    public void responseListener(Response response) {
                        Toast.makeText(FeedbackActivity.this, response.getResponse(), Toast.LENGTH_SHORT).show();
                    }
                };

                HashMap<String,String> data =  new HashMap<String, String>();
                data.put("title",title.getText().toString());
                data.put("description",desc.getText().toString());
                data.put("rating",String.valueOf(rating.getRating()));
                data.put("userId", Utils.getUserId(FeedbackActivity.this));
                data.put("t", getIntent().getStringExtra("t"));
                asyncRequest.execute(data);
            }
        });
    }
}
