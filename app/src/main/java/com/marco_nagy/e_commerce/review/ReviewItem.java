package com.marco_nagy.e_commerce.review;

public class ReviewItem {
    int Image;
    int Rate;
    String name;
    String body;
    String date;

    public ReviewItem(int image, int rate, String name, String body, String date) {
        Image = image;
        Rate = rate;
        this.name = name;
        this.body = body;
        this.date = date;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
