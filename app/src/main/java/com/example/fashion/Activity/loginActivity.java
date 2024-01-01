package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fashion.Domain.UserAuthentication;
import com.example.fashion.Helper.RetrofitClient;
import com.example.fashion.Helper.TinyDB;
import com.example.fashion.Models.SharedPreferencesHelper;
import com.example.fashion.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    SharedPreferencesHelper prefsHelper ;
    private TinyDB tinyDB;

//    private final String UserEmail = "a@gmail.com";
//    private final String UserPassword = "12345";
//    private final String fullName= "Ahmed-M-Hamzah";

    private ImageView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        btnLogin = findViewById(R.id.button_sginup);


        tinyDB = new TinyDB(this);

        prefsHelper = new SharedPreferencesHelper(this);

        String UserEmail = prefsHelper.getEmail();
        String UserPassword = prefsHelper.getPassword();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

//                if (email.equals(UserEmail) && password.equals(UserPassword)) {
                    UserAuthentication userAuth = new UserAuthentication(email, password);
                    Call<UserAuthentication> call = RetrofitClient.getInstance().getServerDetail().getUserAuthentication(userAuth);
                    call.enqueue(new Callback<UserAuthentication>() {
                        @Override
                        public void onResponse(Call<UserAuthentication> call, Response<UserAuthentication> response) {

                            UserAuthentication userResult = response.body();
                            if (response.isSuccessful()){
                                tinyDB.putObject("userAuthentication", userResult);
                                Toast.makeText(getApplicationContext(),"Login Done And Token is "+userResult.getToken(), Toast.LENGTH_LONG).show();
                                sendRequestDetails();

                            } else if (response.code() == 400) {
                                Toast.makeText(loginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<UserAuthentication> call, Throwable t) {
                          Toast.makeText(loginActivity.this, "Network Error Check your connection", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
//                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//
//                }
//            }
        });
    }

    private void sendRequestDetails() {


    }

}