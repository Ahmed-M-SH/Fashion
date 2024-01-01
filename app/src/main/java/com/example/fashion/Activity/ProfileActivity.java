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


public class ProfileActivity extends AppCompatActivity {
    private static final int IMAGE_PICK_REQUEST = 1;
    private ImageView logoutBtn;
    private Button cheng_pro_Btn;
    private String imageUrl = "http://example.com/image.jpg";
    private ImageView img_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDestinationActivity();
            }
        });
        img_profile = findViewById(R.id.img_profile);
        cheng_pro_Btn = findViewById(R.id.cheng_pro_Btn);

        cheng_pro_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDownloadDialog(imageUrl);
            }
        });

        // Load the image using a library like Picasso or Glide
//        Glide.with(this).load(imageUrl).into(img_profile);
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
                    Glide.with(this).load(imageUri).into(img_profile);
                }
            }
        }
    }

    private void navigateToDestinationActivity() {
        Intent intent = new Intent(ProfileActivity.this, DetailActivity.class);
        startActivity(intent);
    }



    }
