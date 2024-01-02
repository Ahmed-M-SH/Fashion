package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fashion.Domain.UserAuthentication;
import com.example.fashion.Domain.UserProfile;
import com.example.fashion.Helper.RetrofitClient;
import com.example.fashion.Helper.TinyDB;
import com.example.fashion.Helper.UserManagement;
import com.example.fashion.Models.SharedPreferencesHelper;
import com.example.fashion.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView txtCreateAccount;
    SharedPreferencesHelper prefsHelper ;
    private TinyDB tinyDB;
    private UserAuthentication userResult;

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
        txtCreateAccount = findViewById(R.id.txtCreateAccount);

        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),signupActivity.class);
                startActivity(intent);
            }
        });

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

                             userResult = response.body();
                            if (response.isSuccessful()){
                                tinyDB.putObject("userAuth", userResult);
                                tinyDB.putBoolean("isAuthent",true);
                                  UserManagement.sendRequestProfile(userResult);
//                                UserProfile profile = UserManagment.getUserProfile();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            } else if (response.code() == 400) {
                                Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
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
        UserAuthentication auth= tinyDB.getObject("userAuth", UserAuthentication.class);
        Call<List<UserProfile>> callProfile = RetrofitClient.getInstance().getServerDetail().getUserProfile(userResult.getToken());
        callProfile.enqueue(new Callback<List<UserProfile>>() {
            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                List<UserProfile> user = response.body();

                if (response.isSuccessful()) {
                    tinyDB.putObject("profile", user.get(0));
                }
                else
                    Toast.makeText(getApplicationContext(),"Error Code:"+response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error sending request To Get Profile and error code:"+t.getMessage(), Toast.LENGTH_LONG).show();

            }

        });
    }

}