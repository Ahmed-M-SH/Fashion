package com.example.fashion.Helper;

import com.example.fashion.Domain.ProductDetail;
import com.example.fashion.Domain.ProductResult;
import com.example.fashion.Domain.UserAuthentication;
import com.example.fashion.Domain.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public  interface ServerDetail {
    static String port = "8000";
    public static String endpoint = "http://192.168.1.2:" + port;

    @GET("/api/products/")
    Call <ProductResult> getProduct();

    @GET("/api/products/{productId}/")
    Call<ProductDetail> getProductDetail(@Path("productId") int productId);
    @GET("/api/user/profile/")
    Call<List< UserProfile>> getUserProfile(@Header("Authorization") String authorization);
    @POST("/api/auth/login/")
    Call<UserAuthentication> getUserAuthentication(@Body UserAuthentication userAuthentication);

    @POST("/api/auth/sginup/")
    Call<UserProfile> getUserRegistration(@Body UserProfile userProfile);

}
