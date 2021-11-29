package com.marco_nagy.e_commerce.ui.home.banner;

public class LatestBanner {
    private int image;
    private int title;

    public LatestBanner(int image, int title) {

        this.image = image;
        this.title = title;
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
}
