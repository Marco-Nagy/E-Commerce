package com.marco_nagy.e_commerce.ui.home.category;

public class CategoryItems {
private int image;
private int title;

    public CategoryItems(int image, int title) {
        this.image = image;
        this.title = title;
    }

    public CategoryItems() {
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
