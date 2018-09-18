package com.miet.eventsandbeyonds.ngo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miet.eventsandbeyonds.R;
import com.miet.eventsandbeyonds.RequestHandler;
import com.miet.eventsandbeyonds.Utils;
import com.miet.eventsandbeyonds.admin.AdminPanel;

import java.util.HashMap;

public class DonateRequestAdapter extends RecyclerView.Adapter<DonateRequestAdapter.ViewHolder> {

    private String[] id;
    private String[] email;
    private String[] firstName;
    private String[] lastName;

    public DonateRequestAdapter(String[] id, String[] email, String[] firstName, String[] lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout cv = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_request_list_item,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final LinearLayout cardView = holder.cardView;
        TextView nameTV = (TextView)cardView.findViewById(R.id.user_request_name_TV);
        TextView emailTV = (TextView)cardView.findViewById(R.id.user_request_email_TV);
        LinearLayout validateUserLL = (LinearLayout) cardView.findViewById(R.id.validate_user_request_LL);
        nameTV.setText(firstName[position] + " "  + lastName[position]);
        emailTV.setText(email[position]);

        validateUserLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Item Clicked : " + firstName[position], Toast.LENGTH_SHORT).show();
                new ValidateRequest(view).execute(id[position]);
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.length;
    }

    //Provide a reference to the views used in the recycler view
    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout cardView;
        public ViewHolder(LinearLayout itemView) {
            super(itemView);
            cardView = itemView;
        }
    }

    private class ValidateRequest extends AsyncTask<String,Void,String>
    {

      //  private String URL = "http://www.errajatsharma.com/2017/EventsAndBeyond/validateDonateRequest.php";
        private String URL = "https://amanmeenia.000webhostapp.com/Banquet/validateDonateRequest.php";
        private RequestHandler requestHandler = new RequestHandler();
        private View view;
        public ValidateRequest(View view) {
            this.view = view;
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> data = new HashMap<>();
            data.put("Id",strings[0]);
            String response = requestHandler.sendPostRequest(URL,data);
            Log.v(Utils.TAG,response);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.contains("updated"))
            {
                Intent intent = new Intent(view.getContext(),RequestActivity.class);
                view.getContext().startActivity(intent);
            }
            Toast.makeText(view.getContext(), s, Toast.LENGTH_SHORT).show();
        }
    }
}
