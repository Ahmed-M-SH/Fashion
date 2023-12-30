package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.fashion.R;

public class FovoriteActivity extends AppCompatActivity {
private ImageView   back_btn_fovorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fovorite);

        back_btn_fovorite = findViewById(R.id.back_btn_fovorite);
        back_btn_fovorite.setOnClickListener(view -> finish());

        }



}