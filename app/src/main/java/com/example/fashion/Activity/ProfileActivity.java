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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fashion.Domain.UserProfile;
import com.example.fashion.Helper.TinyDB;
import com.example.fashion.R;


public class ProfileActivity extends AppCompatActivity {
    private static final int IMAGE_PICK_REQUEST = 1;
    private ImageView logoutBtn;
    private Button cheng_pro_Btn;
    private String imageUrl = "http://example.com/image.jpg";
    private ImageView img_profile;
    private TinyDB tinyDB;
    private UserProfile profile;
    private TextView userName,userName1,txtPhone,txtBeing_prepared_ordersast,txtEmail,txtPrevious_ordersast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        UserManagment.getUserProfile();
        tinyDB  =new TinyDB(this);




        boolean isAuthent = tinyDB.getBoolean("isAuthent");
        profile =(UserProfile) tinyDB.getObject("profile",UserProfile.class);
        if (!isAuthent||profile == null) {
            Intent intent = new Intent(this, loginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
        initView();

        if(profile.getImage() != null)
        // Load the image using a library like Picasso or Glide
        Glide.with(this).load(profile.getImage()).into(img_profile);
        else{
//            int drawableResourceId=this.getResources()
//                    .getIdentifier(listIemSelected.get(position).getPicUrl(),"drawable",this.getPackageName());
            Glide.with(this)
                    .load(R.drawable.pic1)
//                    .transform(new GranularRoundedCorners(30,30,30,30))
                    .into(img_profile);
        }
    }
    }

    private void initView() {
        img_profile = findViewById(R.id.img_profile);
        cheng_pro_Btn = findViewById(R.id.cheng_pro_Btn);

        logoutBtn = findViewById(R.id.logoutBtn);
        userName = findViewById(R.id.userName);
        userName1 = findViewById(R.id.userName1);
        txtEmail =findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtBeing_prepared_ordersast = findViewById(R.id.txtBeing_prepared_ordersast);
        txtPrevious_ordersast = findViewById(R.id.txtPrevious_ordersast);

        userName.setText(""+profile.getName().toString());
        userName1.setText(""+profile.getUsername().toString());
        txtPrevious_ordersast.setText(""+profile.getPreviousOrdersast());
        txtBeing_prepared_ordersast.setText(""+profile.getBeingPreparedOrdersast());
        txtPhone.setText(""+profile.getPhoneNumber().toString());
        txtEmail.setText(""+profile.getEmail().toString());
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                tinyDB.remove("isAuthent");
                tinyDB.remove("profile");
                tinyDB.remove("userAuth");
                startActivity(new Intent(getApplicationContext(), loginActivity.class));
                finish();
            }
        });

        cheng_pro_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDownloadDialog(imageUrl);
            }
        });


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
