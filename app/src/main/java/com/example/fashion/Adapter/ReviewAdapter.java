package com.example.fashion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fashion.Domain.ReviewDomain;
import com.example.fashion.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Viewholder>{
    ArrayList<ReviewDomain> items;
    Context context;


    public ReviewAdapter(ArrayList<ReviewDomain> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public ReviewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_review,parent, false);
        context = parent.getContext();
        return new ReviewAdapter.Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.Viewholder holder, int position) {
        holder.reviewTxt.setText(items.get(position).getReviewText());
        holder.userReview.setText(items.get(position).getReviewUser());
        holder.dataReview.setText(items.get(position).getReviewDate());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView userReview,dataReview,reviewTxt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            userReview=itemView.findViewById(R.id.userReview);
            dataReview=itemView.findViewById(R.id.dataReview);
            reviewTxt=itemView.findViewById(R.id.reviewTxt);

        }
    }
}
