package com.miet.eventsandbeyonds;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

// import com.miet.eventsandbeyonds.ItemFragment.OnListFragmentInteractionListener;
import com.miet.eventsandbeyonds.user.Banquet;
import com.miet.eventsandbeyonds.user.BanquetClickInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

   private ArrayList<Banquet> banquetArrayList;
   private Context context;
    public MyItemRecyclerViewAdapter(ArrayList<Banquet> banquetArrayList, Context context) {
        this.banquetArrayList=banquetArrayList;
        this.context=context;
        Log.i("con",context.toString());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final  Banquet banquet = banquetArrayList.get(position);
        if (banquetArrayList.get(position).isFavorite()){
            holder.c_favourite.setImageResource(R.mipmap.ic_fav);
        }else{
            holder.c_favourite.setImageResource(R.mipmap.ic_unselected_fav);
        }

        holder.c_addressBanquet.setText(banquet.getA_address());
        holder.c_rate.setText("Rs." + banquet.getRate());
        holder.c_nmaeBanquet.setText(banquet.getA_nameBanuet());
        holder.c_hallBanquet.setText("Hall " + banquet.getA_hall());
        holder.c_parkingBanquet.setText("Parking" + banquet.getA_parking());
        holder.c_squareFt.setText("Square Ft " + banquet.getSquareaFet());
        Picasso.with(context).load(banquet.getImageUrl()).into(holder.c_image);
       Log.i("image",banquet.getImageUrl());

        ViewCompat.setTransitionName(holder.c_image, banquet.getA_nameBanuet());
//
  //     holder.cardView.setOnClickListener(new View.OnClickListener() {
  //        @Override
     //   public void onClick(View view) {
    //           ((BanquetClickInterface)context).banquetClicked(banquet, holder.c_image);
   //        }
//     });

        holder.c_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (banquetArrayList.get(position).isFavorite()){
                    banquetArrayList.get(position).setFavorite(false);
                    holder.c_favourite.setImageResource(R.mipmap.ic_unselected_fav);
                }else{
                    banquetArrayList.get(position).setFavorite(true);
                    holder.c_favourite.setImageResource(R.mipmap.ic_fav);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return banquetArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView c_nmaeBanquet,c_rate, c_hallBanquet,c_parkingBanquet, c_squareFt, c_addressBanquet;
        ImageView c_image;
        FloatingActionButton c_favourite;
        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.house_card_view);
            c_nmaeBanquet = (TextView)view.findViewById(R.id.nameBanquet);
            c_rate = (TextView)view.findViewById(R.id.rateBanquet);
            c_parkingBanquet = (TextView)view.findViewById(R.id.parkingBanquet);
            c_hallBanquet= (TextView)view.findViewById(R.id.hallBanquet);
            c_squareFt = (TextView)view.findViewById(R.id.square_ftBanquet);
            c_addressBanquet = (TextView)view.findViewById(R.id.addressBanquet);
            c_image= (ImageView)view.findViewById(R.id.imageBanquetList);
            c_favourite = (FloatingActionButton) view.findViewById(R.id.favouriteBanquet);
        }

    }
}
