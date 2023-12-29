package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fashion.Adapter.CartListAdaper;
import com.example.fashion.Helper.ChangeNumberItemsListener;
import com.example.fashion.Helper.ManagmentCart;
import com.example.fashion.R;

public class CartActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerView;
private ManagmentCart managmentCart;

private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt;
private double tax;
private ScrollView scrollView;
private ImageView backBtn;
private ImageView address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        managmentCart=new ManagmentCart(this);

        initView();
        setVariable();
        initList();
        calculateCart();
    }


    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaper(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });
        recyclerView.setAdapter(adapter);
        if (managmentCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);



        }
    }

    private void calculateCart() {
        double total = Math.round(managmentCart.getTotalFee()*100) /100;
        double itemTotal = Math.round(managmentCart.getTotalFee()*100)/100;
        totalFeeTxt.setText("$"+itemTotal);
        totalTxt.setText("$"+itemTotal);

    }


    private void setVariable() {
        backBtn.setOnClickListener(view -> finish());
    }

    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerView=findViewById(R.id.view1);
        scrollView=findViewById(R.id.scrollView4);
        backBtn=findViewById(R.id.backArrowBtn);
        emptyTxt=findViewById(R.id.emptyTxt);
        address=findViewById(R.id.address);

    }
}