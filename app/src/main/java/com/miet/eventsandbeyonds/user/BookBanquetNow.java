package com.miet.eventsandbeyonds.user;

import android.app.DatePickerDialog;
import android.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import  android.widget.AdapterView.OnItemSelectedListener;
import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.httpRequest.AsyncRequest;
import com.miet.eventsandbeyonds.httpRequest.Response;

import java.util.HashMap;
import java.util.List;

public class BookBanquetNow extends AppCompatActivity implements OnItemSelectedListener {
     EditText banquetName,banquetDate,banquetTime,banquetCater,banquetPay,banquetMobile;
     Button btnBook,btnDate,btnTime;
     Spinner sp;
       Calendar c;
       Calendar cd;
     DatePickerDialog dpd;
     TimePickerDialog tpd;
   public   String item;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // On selecting a spinner item
         item = adapterView.getItemAtPosition(i).toString();

        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_banquet_now);
       // banquetName=(EditText)findViewById(R.id.bookBanquetName);
        banquetDate=(EditText)findViewById(R.id.BookBanquetDate);
        banquetTime=(EditText)findViewById(R.id.BookBanquetTime);
        banquetCater=(EditText)findViewById(R.id.BookBanquetCater);
        banquetPay=(EditText)findViewById(R.id.BookBanquetPayment);
        banquetMobile=(EditText)findViewById(R.id.BookBanquetMobile);
        btnBook=(Button)findViewById(R.id.BookBanquetBtn);
        btnDate=(Button)findViewById(R.id.BookBanquetDateButton);
        btnTime=(Button)findViewById(R.id.BookBanquetTimeButton);
        sp=(Spinner)findViewById(R.id.spinner2);
        sp.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("BLUE MOON-2,00,000");
        categories.add("THE GRAND-1,25,000");
        categories.add("SUNCITY FARM-2,00,000");
        categories.add("VEDAS FARM-2,00,000");
        categories.add("ROYAL ESTATE-3,00,000");
        categories.add("TRIPLE FARM-2,00,000");
        categories.add("KC EMPORIA-2,00,000");
        categories.add("KAMLA MAHAL-2,00,000");
        categories.add("GUPTA RESORT-1,75,000");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp.setAdapter(dataAdapter);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c= Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
           dpd  = new DatePickerDialog(BookBanquetNow.this, new DatePickerDialog.OnDateSetListener() {
               @Override
               public void onDateSet(DatePicker datePicker, int mYear, int mMonth ,int mDate) {
                   banquetDate.setText(mDate + "-" + (mMonth+1)+"-"+mYear);
               }
           },day,month,year);
           dpd.show();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cd = Calendar.getInstance();
                int hour = cd.get(Calendar.HOUR_OF_DAY);
                int min = cd.get(Calendar.MINUTE);
                tpd = new TimePickerDialog(BookBanquetNow.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int mHour, int mMin) {
                        banquetTime.setText(mHour+ ":"+ mMin);
                    }
                },hour,min,false);
                tpd.show();;
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncRequest asyncRequest = new AsyncRequest("/bookBanquet.php") {
                    @Override
                    public void responseListener(Response response) {
                        Toast.makeText(BookBanquetNow.this,response.getResponse(),Toast.LENGTH_SHORT).show();
                    }
                };
                HashMap<String,String> banquethashmap = new HashMap<>();
                banquethashmap.put("BanquetName",item);
                banquethashmap.put("BanquetDate",banquetDate.getText().toString());
                banquethashmap.put("BanquetTime",banquetTime.getText().toString());
                banquethashmap.put("BanquetCater",banquetCater.getText().toString());
                banquethashmap.put("BanquetPay",banquetPay.getText().toString());
                banquethashmap.put("BanquetMobileNo",banquetMobile.getText().toString());
                asyncRequest.execute(banquethashmap);
            }
        });


    }
}
