package com.example.fashion.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.fashion.Domain.PopularDomain;
import com.example.fashion.Helper.ManagmentCart;
import com.example.fashion.R;

public class DetailActivity extends Activity {
    private Button addToCartBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,reviewTxt,scoreTxt;
    private ImageView picItem,backBtn;
    private PopularDomain object;
    private int numberOrder=1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile);


        managmentCart = new ManagmentCart(this);

        backBtn=findViewById(R.id.backArrowBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(),
                "drawable",this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picItem);

        titleTxt.setText(object.getTitle());
        descriptionTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview()+"");
        scoreTxt.setText(object.getScore()+"");
        feeTxt.setText("$"+object.getPrice());

        addToCartBtn.setOnClickListener(view -> {
            object.setNumberinCart(numberOrder);
            managmentCart.insertFood(object);
        });
    }

    private void initView() {
//        private TextView titleTxt,feeTxt,descriptionTxt,reviewTxt,scoreTxt;

        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.feeTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        reviewTxt = findViewById(R.id.reviewTxt);
        scoreTxt=findViewById(R.id.scoreTxt);
        picItem = findViewById(R.id.MakUp);

    }
}
