package com.example.fashion.Activity;

import static com.example.fashion.R.id.fovortieBtn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.fashion.Adapter.ReviewAdapter;
import com.example.fashion.Domain.PopularDomain;
import com.example.fashion.Domain.ProductDetail;
import com.example.fashion.Domain.ProductResult;
import com.example.fashion.Domain.ReviewDomain;
import com.example.fashion.Helper.ManagmentCart;
import com.example.fashion.Helper.ServerDetail;
import com.example.fashion.R;
import com.google.gson.Gson;
import com.like.LikeButton;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private String endpoint = ServerDetail.endpoint + "/api/products/";

    private Button addToCartBtn;
    private RecyclerView.Adapter adapterReview;
    private RecyclerView recyclerReview;
    private TextView titleTxt, feeTxt, descriptionTxt, reviewTxt, scoreTxt, readMoreTxt;
    private ImageView picFood, backBtn,shareButton;
    private ReviewDomain reviewObject;
    private LikeButton fovortieBtn;

    private int numberOrder = 1;
    private ManagmentCart managmentCart;

    private StringRequest productStringRequest;
    private RequestQueue requestProductQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaile);

        initView();
        getBundle();
        setReadMoreLink();
        backBtn.setOnClickListener(view -> finish());

        sendRequest();



        }

    private void sendRequest() {




    }

    private void initShare() {

//from chatgpt
//        <activity android:name=".MyLinkActivity">
//    <intent-filter>
//        <action android:name="android.intent.action.VIEW" />
//        <category android:name="android.intent.category.DEFAULT" />
//        <data android:scheme="https" android:host="example.com" android:pathPrefix="/product" />
//    </intent-filter>
//</activity>
//
//        public class MyLinkActivity extends AppCompatActivity {
//            @Override
//            protected void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//                setContentView(R.layout.activity_my_link);
//
//                Intent intent = getIntent();
//                Uri data = intent.getData(); // الحصول على البيانات المرتبطة بالرابط المشترك
//
//                if (data != null) {
//                    // قم بمعالجة البيانات والقيام بالإجراءات المناسبة
//                    String productId = data.getQueryParameter("id");
//                    // قم بتنفيذ الإجراءات المطلوبة باستخدام معرف المنتج
//                }
//            }
//        }

        shareButton.setOnClickListener(new View.OnClickListener() {
            private String productName = "اسم المنتج";

            @Override
            public void onClick(View v) {
                String productId = "titleTxt"; // معرف المنتج
                String baseUrl = "https://example.com/product"; // الجزء الثابت من الرابط

                Uri.Builder builder = Uri.parse(baseUrl).buildUpon();
                builder.appendQueryParameter("id", productId);
                builder.appendQueryParameter("source", "myapp"); // معلمة استعلام لتحديد التطبيق المصدر

                Uri shareUri = builder.build();
                String url = shareUri.toString();

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(Intent.createChooser(shareIntent, "شارك عبر"));
            }
        });

    }

    private void setReadMoreLink() {
        String desText = descriptionTxt.getText().toString();
        String moreText = "Read More";
        SpannableString spannableString = new SpannableString(desText + moreText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                expendTextView();
            }
        };

        spannableString.setSpan(clickableSpan, desText.length(), desText.length() + moreText.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        descriptionTxt.setText(spannableString);
        descriptionTxt.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void expendTextView() {
        descriptionTxt.setMaxLines(Integer.MAX_VALUE);
        readMoreTxt.setVisibility(View.GONE);
    }

    private void getBundle() {
        int productId = getIntent().getIntExtra("product_id", 0);

        requestProductQueue = Volley.newRequestQueue(this);
        productStringRequest =new StringRequest(Request.Method.GET, endpoint+productId+'/', response -> {
            Gson gson = new Gson();
            ProductDetail item = gson.fromJson(response, ProductDetail.class);
            Glide.with(this)
                    .load(item.getImage())
                    .into(picFood);

            titleTxt.setText("" + item.getName());
            descriptionTxt.setText(item.getDescription());
            reviewTxt.setText("" + item.getReviewCount() + "");
            scoreTxt.setText(item.getRate() + "");
            feeTxt.setText("$" + item.getPrice());
            if(item.getInFavorite())
                fovortieBtn.setLiked(true);
            recyclerReview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//
        adapterReview = new ReviewAdapter(item);
            recyclerReview.setAdapter(adapterReview);


        }, error -> {
            Log.i("RESPONSE", "OnErrorResponse: " + error.toString());


        });
        requestProductQueue.add(productStringRequest);
        addToCartBtn.setOnClickListener(view -> {
//            .setNumberinCart(numberOrder);
//            managmentCart.insertFood(item);
        });

        readMoreTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expendTextView();
            }
        });
    }

    private void initView() {
//        private TextView titleTxt,feeTxt,descriptionTxt,reviewTxt,scoreTxt;


        recyclerReview = findViewById(R.id.recylerReview);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.feeTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        reviewTxt = findViewById(R.id.reviewTxt);
        scoreTxt = findViewById(R.id.rateTxt);
        picFood = findViewById(R.id.MakUp);
        readMoreTxt = findViewById(R.id.readMoreTxt);
        fovortieBtn =findViewById(R.id.fovortieBtn);
        shareButton = findViewById(R.id.shareButton);
        managmentCart = new ManagmentCart(this);
        backBtn = findViewById(R.id.backArrowBtn);

        }






//        private void toggleImageViewColor() {
//            ColorDrawable currentColor = (ColorDrawable) fovortieBtn.getBackground();
//            int currentColorRes = currentColor.getColor();
//
//            int newColorRes = (currentColorRes == getResources().getColor(R.color.black))
//                    ? getResources().getColor(R.color.red)
//                    : getResources().getColor(R.color.black);
//
//            fovortieBtn.setBackgroundColor(newColorRes);
//        }
    }

