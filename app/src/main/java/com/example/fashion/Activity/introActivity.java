package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fashion.R;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class introActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    @Override
    public void onClick(View view) {
        int viewID = view.getId();
        if (viewID == R.id.btn_login){
            Intent intent = new Intent(this,loginActivity.class );
            startActivity(intent);
        } else if (viewID == R.id.btn_signup) {
            Intent intent = new Intent(this,signupActivity.class );
            startActivity(intent);
        }
    }
}