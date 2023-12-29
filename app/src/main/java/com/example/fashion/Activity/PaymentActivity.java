package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fashion.R;


public class PaymentActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button downloadButton;

    private ImageView backBtn;
    private String imageUrl = "http://example.com/image.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);



        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // إنشاء Intent للانتقال إلى الصفحة التالية
                Intent intent = new Intent(PaymentActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        imageView = findViewById(R.id.imageView);
        downloadButton = findViewById(R.id.downloadButton);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDownloadDialog(imageUrl);
            }
        });

        // Load the image using a library like Picasso or Glide
        Glide.with(this).load(imageUrl).into(imageView);
    }

    private void showDownloadDialog(String imageUrl) {
        // Show download dialog and handle download action
        // ...
    }
}