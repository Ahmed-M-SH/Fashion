package com.example.fashion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.fashion.Activity.DetailActivity;
import com.example.fashion.Domain.PopularDomain;
import com.example.fashion.Domain.ProductResult;
import com.example.fashion.Helper.ServerDetail;
import com.example.fashion.R;

import java.util.ArrayList;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.Viewholder> {
    ProductResult items;
    Context context;

    public PopularListAdapter(ProductResult items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pop_list,parent, false);
        context = parent.getContext();
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularListAdapter.Viewholder holder, int position) {
            holder.titleTxt.setText(items.getResults().get(position).getName());
            holder.feeTxt.setText("$"+items.getResults().get(position).getPrice());
            holder.ScoreTxt.setText(""+items.getResults().get(position).getRate());
        String image = "";

        if (items.getResults().get(position).getImage().contains("http"))
                 image = items.getResults().get(position).getImage();
        else
            image = ServerDetail.endpoint+items.getResults().get(position).getImage();


        int drawableResourceId = holder.itemView.getResources().getIdentifier(image,
                    "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(holder.pic);
        holder.itemView.setOnClickListener(view -> {
            Intent  intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("product_id", items.getResults().get(position).getId());
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.getResults().size();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,feeTxt,ScoreTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            feeTxt=itemView.findViewById(R.id.feeTxt);
            ScoreTxt=itemView.findViewById(R.id.scoreTxt);
            pic=itemView.findViewById(R.id.pic);

        }
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,feeTxt,ScoreTxt;
        ImageView pic;

        public  Viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            feeTxt=itemView.findViewById(R.id.feeTxt);
            ScoreTxt=itemView.findViewById(R.id.scoreTxt);
            pic=itemView.findViewById(R.id.pic);

        }


    }
}

