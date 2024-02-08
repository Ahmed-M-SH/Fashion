package com.example.fashion.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.fashion.Domain.PopularDomain;
import com.example.fashion.Helper.ChangeNumberItemsListener;
import com.example.fashion.Helper.ManagmentCart;
import com.example.fashion.R;

import java.util.ArrayList;

public class CartListAdaper extends RecyclerView.Adapter<CartListAdaper.viewHolder> {
    ArrayList<PopularDomain> listIemSelected;
    private ManagmentCart managmentCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdaper(ArrayList<PopularDomain> listIemSelected,Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listIemSelected = listIemSelected;
        managmentCart=new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartListAdaper.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new viewHolder(inflate);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull CartListAdaper.viewHolder holder, int position) {
        holder.title.setText(listIemSelected.get(position).getTitle());
        holder.feeEachItem.setText("$"+listIemSelected.get(position).getPrice());
        holder.totalEachItem.setText("$"+ Math.round(listIemSelected.get(position).getNumberinCart()*listIemSelected.get(position).getPrice()));
        holder.num.setText(String.valueOf(listIemSelected.get(position).getNumberinCart()));

int drawableResourceId=holder.itemView.getContext().getResources()
        .getIdentifier(listIemSelected.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);
        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            }
        });
        holder.minusItem.setOnClickListener(view -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        });

    }

    @Override
    public int getItemCount() {
        return listIemSelected.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
TextView title,feeEachItem, plusItem, minusItem;
ImageView pic;
TextView totalEachItem, num;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
           title=itemView.findViewById(R.id.titleTxt);
            pic=itemView.findViewById(R.id.pic);
            feeEachItem=itemView.findViewById(R.id.feeEachitem);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            plusItem =itemView.findViewById(R.id.pludCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
            num=itemView.findViewById(R.id.numberItemTxt);

        }
    }
}
