package com.marco_nagy.e_commerce.search;

import java.io.Serializable;

public class RecentlyItems implements Serializable {

    private int image;
    private int title;
    private String price;
    private double rate;

    public RecentlyItems(int image, int title, String price, double rate) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.rate = rate;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
