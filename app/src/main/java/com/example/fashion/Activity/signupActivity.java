package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fashion.Domain.ErrorResponse;
import com.example.fashion.Domain.UserProfile;
import com.example.fashion.Helper.RetrofitClient;
import com.example.fashion.Helper.TinyDB;
import com.example.fashion.Helper.UserManagement;
import com.example.fashion.R;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signupActivity extends AppCompatActivity {
    EditText txt_email,txt_password,txt_password1, txt_fullName,txt_phoneNumber;
    ImageView btnSigunup;
    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txt_email = (EditText) findViewById(R.id.edit_text_email);
        txt_password = (EditText) findViewById(R.id.edit_text_password);
        txt_password1 =  (EditText) findViewById(R.id.edit_text_password2);
        txt_fullName =  (EditText) findViewById(R.id.edit_text_fullName);
        btnSigunup = (ImageView) findViewById(R.id.button_sginup);
        txt_phoneNumber = (EditText) findViewById(R.id.edit_text_phoneNumber);
        tinyDB = new TinyDB(this);
        btnSigunup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                String password1 = txt_password1.getText().toString();
                String fullName = txt_fullName.getText().toString();
                String phoneNumber = txt_phoneNumber.getText().toString();
                if (password.equals(password1) && password1.equals(password)) {
                    UserProfile profile =new UserProfile();
                    profile.setEmail(email);
                    profile.setPassword(password);
                    profile.setPhoneNumber(phoneNumber);
                    profile.setName(fullName);
                    Call<UserProfile> user = RetrofitClient.getInstance().getServerDetail().getUserRegistration(profile);
                    user.enqueue(new Callback<UserProfile>() {
                        @Override
                        public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                            UserProfile userProfile = response.body();
                            if (userProfile != null){
//                                Toast.makeText(getApplicationContext(), "User profile Created", Toast.LENGTH_SHORT).show();
//                                tinyDB.putObject("profile", userProfile);
                                tinyDB.putBoolean("isAuthent",true);
                                tinyDB.putObject("userAuth",userProfile.getUserAuth());

                                UserManagement.sendRequestProfile(userProfile.getUserAuth());

                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (response.errorBody() !=null) {
                                try {
                                    String errorBody = response.errorBody().string();
                                    // Parse the errorBody using Gson and your ErrorResponse class
                                    ErrorResponse errorResponse = new Gson().fromJson(errorBody, ErrorResponse.class);

                                    if (errorResponse != null && errorResponse.getEmailErrors() != null && !errorResponse.getEmailErrors().isEmpty()) {
                                        // Handle email-related errors
                                        String errorMessage = errorResponse.getEmailErrors().toString();
                                        txt_email.setError(errorMessage);

//                                        Toast.makeText(getApplicationContext(), "Email error: " + errorMessage, Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Handle other types of errors
                                        Toast.makeText(getApplicationContext(), "Unknown error occurred", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                            if(response.isSuccessful()) {
                            }
//                            Toast.makeText(getApplication(),"error:"+response.toString(), Toast.LENGTH_SHORT).show();
                            Log.i("ErrorResponse",response.toString());
                        }

                        @Override
                        public void onFailure(Call<UserProfile> call, Throwable t) {

                        }
                    });
                } else
                    Toast.makeText(signupActivity.this, "password no match", Toast.LENGTH_SHORT).show();
            }
        });
    }
}