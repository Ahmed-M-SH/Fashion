package com.example.fashion.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fashion.R;


public class PaymentActivity extends AppCompatActivity {
    private static final int IMAGE_PICK_REQUEST = 1;
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
        // Open a file chooser or camera intent to let the user select/capture an image
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                Uri imageUri = data.getData();

                // Handle the selected image URI as needed (e.g., upload to a server)
                if (imageUri != null) {
                    // Process the selected image URI
                    Glide.with(this).load(imageUri).into(imageView);
                }
            }
        }
    }
}