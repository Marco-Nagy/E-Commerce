package com.example.e_commerce.home;

import java.io.Serializable;

public class ProductItems implements Serializable {

    private int image;
    private int title;
    private double price;
    private double rate;

    public ProductItems(int image, int title, double price, double rate) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
