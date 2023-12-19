package com.example.fashion.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {
    private String title;
    private String description;
    private String picUrl;
    private int review;
    private int score;
    private String numberinCart;

    public PopularDomain(String title, String description, String picUrl, int review, int score, String numberinCart) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.review = review;
        this.score = score;
        this.numberinCart = numberinCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNumberinCart() {
        return numberinCart;
    }

    public void setNumberinCart(String numberinCart) {
        this.numberinCart = numberinCart;
    }
}
